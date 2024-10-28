package com.aluracurso.desafio.main;

import com.aluracurso.desafio.model.Anime;
import com.aluracurso.desafio.model.DataAnime;
import com.aluracurso.desafio.model.DataEpisodes;
import com.aluracurso.desafio.model.Episode;
import com.aluracurso.desafio.service.ApiConsume;
import com.aluracurso.desafio.service.DataConvert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner scan = new Scanner(System.in);
    private ApiConsume apiConsume = new ApiConsume();
    private final String URL_BASE = "https://api.jikan.moe/v4/anime?q=";
    private DataConvert convert = new DataConvert();

    public void Menu() throws UnsupportedEncodingException {
        String menu = """
                
                ******** Menú *********
                1.Anime Search
                2.Top 10 Animes de MyAnimeList
                3.Animes de la Temporada en curso
                4.Salir
                
                """;

        int option = 0;

        System.out.println(" ****** BIENVENIDO A ANIME SEARCH ****** \n");
        do {
            System.out.println(menu);
            System.out.println("\nEscribe la opción deseada:");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    animeSearch();
                    break;
                case 2:
                    showTopTen();
                    break;
                case 3:
                    showAnimeTemporade();
                    break;
                case 4:
                    System.out.println("saliendo...");
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        } while (option != 4);


    }

    private void showTopTen() {
        //MUESTRA EL TOP 10 ANIMES ACTUALMENTE
        System.out.println(" **** Top 10 animes ****");
        var jsonTop = apiConsume.getData("https://api.jikan.moe/v4/top/anime?sfw");
        DataAnime dataTop = convert.convert(jsonTop, DataAnime.class);

        int count = 1;
        for (var a : dataTop.animeList().stream().limit(10).toList()) {
            System.out.println(count + ". " + a.title() + " (" + a.type() + ") | Score: " + a.score());
            count++;
        }
    }

    private void showAnimeTemporade() {
        //MUESTRA TODOS LOS ANIMES DE LA TEMPORADA ACTUAL
        System.out.println("\n **** Animes de esta Temporada ****");
        var jsonTemp = apiConsume.getData("https://api.jikan.moe/v4/seasons/now?sfw");

        int count = 1;
        DataAnime dataTemp = convert.convert(jsonTemp, DataAnime.class);
        for (var a : dataTemp.animeList().stream().toList()) {
            System.out.println(count + ". " + a.title());
            count++;
        }
    }

    private void animeSearch() throws UnsupportedEncodingException {

        // BUSQUEDA DE ANIME POR TITULO TIPO TV
        String animeTitle = "";
        scan.nextLine();
        do {
            System.out.println("\nEscribe el anime que deseas buscar o salir para volver al menú.");

            animeTitle = scan.nextLine().trim();

            if (animeTitle.toUpperCase().equals("SALIR")) {
                System.out.println("Volviendo al menú...");
                break;
            }

            String encodeTitle = URLEncoder.encode(animeTitle, "UTF-8");

            var json = apiConsume.getData(URL_BASE + encodeTitle);

            //System.out.println(json);
            DataAnime dataAnime = convert.convert(json, DataAnime.class);
            Optional<Anime> animeBuscado = dataAnime.animeList().stream()
                    .filter(e -> e.type().contains("TV"))
                    .findFirst();

            if (animeBuscado.isPresent()) {
                System.out.println("""
                        Anime encontrado:
                        Titulo: %s
                        Score: %s
                        Año: %s
                        Episodios: %s
                        Tipo: %s
                        Sinopsis: %s    """.formatted(animeBuscado.get().title(), animeBuscado.get().score(), animeBuscado.get().year(), animeBuscado.get().episodes(), animeBuscado.get().type(), animeBuscado.get().synopsis()));


                //MUESTRA EL EPISODIO MEJOR CALIFICADO

                var jsonEpisodesAnime = apiConsume.getData("https://api.jikan.moe/v4/anime/" + animeBuscado.get().id() + "/episodes");
                DataEpisodes dataEpisodes = convert.convert(jsonEpisodesAnime, DataEpisodes.class);

                System.out.println("\n ** Capitulo mejor calificado ** ");

                Optional<Episode> bestEpisode = dataEpisodes.episodeList().stream()
                        .filter(e -> e.score() != null)
                        .filter(e -> e.score() > 0.0)
                        .max(Comparator.comparing(Episode::score));

                if (bestEpisode.isEmpty()) {
                    System.out.println("Episodio no encontrado");
                } else {
                    String dateOnly = "No encontrado";

                    if (bestEpisode.get().aired() != null) {
                        OffsetDateTime dateTime = OffsetDateTime.parse(bestEpisode.get().aired());
                        dateOnly = dateTime.toLocalDate().toString();
                    }


                    System.out.println(String.format("""
                            Numero de episodio: %d
                            Titulo: %s
                            Score: %s
                            Fecha de emision: %s
                            """, bestEpisode.get().numberEpisode(), bestEpisode.get().title(), bestEpisode.get().score(), dateOnly));

                }

            } else {
                System.out.println("Anime no encontrado");
            }

        } while (true);
    }
}

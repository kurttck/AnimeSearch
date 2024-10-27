package com.aluracurso.desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Episode(
        @JsonAlias("mal_id") int numberEpisode,
        @JsonAlias("title") String title,
        @JsonAlias("score") Double score,
        @JsonAlias("aired") String aired
) {
}

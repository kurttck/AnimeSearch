package com.aluracurso.desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Anime(
        @JsonAlias("mal_id") int id,
        @JsonAlias("title") String title,
        @JsonAlias("synopsis") String synopsis,
        @JsonAlias("score") String score,
        @JsonAlias("year") String year,
        @JsonAlias("episodes") Integer episodes,
        @JsonAlias("type") String type
) {
}

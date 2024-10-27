package com.aluracurso.desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisodes(
        @JsonAlias("data") List<Episode> episodeList
) {

}

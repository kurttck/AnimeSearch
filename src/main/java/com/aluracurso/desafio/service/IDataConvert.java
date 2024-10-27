package com.aluracurso.desafio.service;

public interface IDataConvert {

    <T> T convert(String json, Class<T> clazz);
}

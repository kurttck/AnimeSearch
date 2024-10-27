package com.aluracurso.desafio.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsume {

public String getData(String url){
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

    HttpResponse<String> response = null;

    try{
response = client.send(request, HttpResponse.BodyHandlers.ofString());

    }catch(IOException e){
        throw new RuntimeException(e);
    } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
    }
    String json = response.body();
    return json;
}
}

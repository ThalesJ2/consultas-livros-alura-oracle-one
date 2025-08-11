package com.one.consulta.consultalivrosone.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Service
public class APIService {



    public String callAPI(String address){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();


        try {
             HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200)
                return  response.body();

            throw new IOException();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

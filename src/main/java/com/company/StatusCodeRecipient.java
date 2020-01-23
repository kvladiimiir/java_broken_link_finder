package com.company;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

public class StatusCodeRecipient implements Callable<Integer> {
    private final String url;

    public StatusCodeRecipient(String url) {
        this.url = url;
    }

    @Override
    public Integer call() {
        HttpClient client = HttpClient.newBuilder().build();

        URI uri;
        try {
            uri = URI.create(url);
        } catch (IllegalArgumentException ex) {
            return -1;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            return response.statusCode();
        } else {
            return -1;
        }
    }
}

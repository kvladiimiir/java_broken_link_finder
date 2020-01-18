package com.company;

import org.apache.http.impl.EnglishReasonPhraseCatalog;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BrokenLinkChecker {
    public int getLinkStatusCode(String url) {
        HttpClient client = HttpClient.newBuilder().build();

        URI uri;
        try {
            uri = URI.create(url);
        } catch (IllegalArgumentException ex) {
            return -1;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri) //тут падает IllegalArgumentException при плохой ссылкой
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

    public Map<String, Integer> getBrokenLinksMap(ArrayList<String> links) {
        Map<String, Integer> brokenLinksMap = new HashMap<>();

        for (String link: links) {
            var statusCode = getLinkStatusCode(link);

            if (statusCode >= 300 || statusCode == -1) {
                brokenLinksMap.put(link, statusCode);
            }
        }

        return brokenLinksMap;
    }

    public String GetStatusCodeText(Integer statusCode)
    {
        try {
            return EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode, null);
        } catch (IllegalArgumentException ex) {
            return "Invalid Status Code";
        }

    }
}

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
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert response != null;
        return response.statusCode();
    }

    public Map<String, Integer> getBrokenLinksMap(ArrayList<String> links) {
        Map<String, Integer> brokenLinksMap = new HashMap<>();

        for (String link: links) {
            var statusCode = getLinkStatusCode(link);

            if (statusCode >= 300) {
                brokenLinksMap.put(link, statusCode);
            }
        }

        return brokenLinksMap;
    }

    public String getStatusCodeText(Integer statusCode)
    {
        return EnglishReasonPhraseCatalog.INSTANCE.getReason(statusCode, null);
    }
}

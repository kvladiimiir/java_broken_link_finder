package com.company.statusCodeRecipient;

import com.company.linkResponseInfo.LinkResponseInfo;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.Callable;

public class StatusCodeRecipient implements Callable<LinkResponseInfo> {
    private final String url;

    public StatusCodeRecipient(String url) {
        this.url = url;
    }

    @Override
    public LinkResponseInfo call() {
        URL uri = null;

        try {
            uri = new URL(url);
        } catch (Exception ex) {
            return new LinkResponseInfo(url, 400);
        }

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
            urlConnection.setReadTimeout(5000);

            return new LinkResponseInfo(url, urlConnection.getResponseCode());
        } catch (IOException ex) {
            return new LinkResponseInfo(url, 500);
        }
    }
}
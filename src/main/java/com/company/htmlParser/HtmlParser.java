package com.company.htmlParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HtmlParser {
    String[] attributes = {"href", "src"};

    public ArrayList<String> getUrlsFromPage(String url) {
        ArrayList<String> urls = new ArrayList<>();

        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid url");
        }

        if (doc == null) {
            throw new NullPointerException("Cannot get data from " + url);
        }

        Elements newsHeadlines = new Elements();

        for (String attr: attributes) {
            newsHeadlines.addAll(doc.getElementsByAttribute(attr));
        }

        for (Element headline : newsHeadlines) {
            for (String attr: attributes) {
                String absUrl = headline.absUrl(attr);

                if (isValidUrl(absUrl, urls)) {
                    urls.add(absUrl);
                }
            }
        }

        return urls;
    }

    boolean isValidUrl(String url, ArrayList<String> urls) {
        boolean isValid = false;
        if (!urls.contains(url)
                && !url.equals("")
                && url.startsWith("http")) {
            isValid = true;
        }

        return isValid;
    }
}
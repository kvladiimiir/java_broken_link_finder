package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HtmlParser {
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

        Elements newsHeadlines = doc.getElementsByAttribute("href");
        newsHeadlines.addAll(doc.getElementsByAttribute("src"));

        for (Element headline : newsHeadlines) {
            String absUrlHref = headline.absUrl("href");

            if (!urls.contains(absUrlHref)
                    && !absUrlHref.equals("")
                    && absUrlHref.startsWith("http")) {
                urls.add(absUrlHref);
            }

            String absUrlSrc = headline.absUrl("src");

            if (!urls.contains(absUrlSrc)
                    && !absUrlSrc.equals("")
                    && absUrlSrc.startsWith("http")) {
                urls.add(absUrlSrc);
            }
        }

        return urls;
    }
}
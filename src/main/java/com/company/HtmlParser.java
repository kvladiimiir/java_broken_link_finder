package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class HtmlParser {
    public ArrayList<String> getUrlsFromPage(String url) {
        ArrayList<String> urls = new ArrayList<String>();

        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements newsHeadlines = doc.select("a");

        for (Element headline : newsHeadlines) {
            if (!urls.contains(headline.absUrl("href"))
                    && headline.absUrl("href").startsWith("http")
                    && !headline.absUrl("href").equals("")) {
                urls.add(headline.absUrl("href"));
            }

            if (!urls.contains(headline.absUrl("src"))
                    && headline.absUrl("href").startsWith("http")
                    && !headline.absUrl("src").equals("")) {
                urls.add(headline.absUrl("src"));
            }
        }

        return urls;
    }
}
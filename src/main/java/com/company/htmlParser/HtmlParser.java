package com.company.htmlParser;

import com.company.enums.InputDataType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class HtmlParser {
    String[] attributes = {"href", "src"};

    public ArrayList<String> getUrlsFromPage(InputDataType dataType, String url) throws FileNotFoundException {
        ArrayList<String> urls = new ArrayList<>();

        Document doc = null;

        try {
            if (dataType == InputDataType.LINKS) {
                doc = Jsoup.connect(url).get();
            } else {
                File input = new File(url);
                doc = Jsoup.parse(input, "UTF-8", "");
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Invalid file");
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
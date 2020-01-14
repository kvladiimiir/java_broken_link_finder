package com.company;

import java.util.ArrayList;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        HtmlParser htmlParser = new HtmlParser();
        ArrayList<String> links =
                htmlParser.getUrlsFromPage("http://52.136.215.164/broken-links/");

        BrokenLinkChecker brokenLinkChecker = new BrokenLinkChecker();

        Map<String, Integer> brokenLinksMap = brokenLinkChecker.getBrokenLinksMap(links);

        for (var item: brokenLinksMap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue() + " " + brokenLinkChecker.getStatusCodeText(item.getValue()));
        }
    }
}

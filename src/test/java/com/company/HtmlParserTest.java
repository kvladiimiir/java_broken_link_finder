package com.company;

import com.company.enums.InputDataType;
import com.company.htmlParser.HtmlParser;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

//88% по строкам
public class HtmlParserTest {

    @Test
    public void GetUrlsFromPage_Url_ListOfUrl() throws FileNotFoundException {
        //arrange
        HtmlParser htmlParser = new HtmlParser();
        String url = "http://google.com";

        String[] expectedArray = new String[] {
                "https://www.google.com/",
                "https://mail.google.com/mail/?tab=wm&ogbl",
                "https://www.google.ru/imghp?hl=ru&tab=wi&ogbl",
                "https://www.google.ru/intl/ru/about/products?tab=wh",
                "https://accounts.google.com/ServiceLogin?hl=ru&passive=true&continue=https://www.google.com/%3Fgws_rd%3Dssl",
                "https://www.google.com/?gws_rd=ssl#",
                "https://policies.google.com/privacy?fg=1",
                "https://policies.google.com/terms?fg=1",
                "https://www.google.com/preferences?hl=ru",
                "https://www.google.com/preferences?hl=ru&fg=1",
                "https://www.google.com/advanced_search?hl=ru&fg=1",
                "https://www.google.com/history/privacyadvisor/search/unauth?utm_source=googlemenu&fg=1",
                "https://www.google.com/history/optout?hl=ru&fg=1",
                "https://support.google.com/websearch/?p=ws_results_help&hl=ru&fg=1",
                "https://www.google.com/intl/ru_ru/ads/?subid=ww-ww-et-g-awa-a-g_hpafoot1_1!o2&utm_source=google.com&utm_medium=referral&utm_campaign=google_hpafooter&fg=1",
                "https://www.google.com/services/?subid=ww-ww-et-g-awa-a-g_hpbfoot1_1!o2&utm_source=google.com&utm_medium=referral&utm_campaign=google_hpbfooter&fg=1",
                "https://about.google/?utm_source=google-RU&utm_medium=referral&utm_campaign=hp-footer&fg=1",
                "https://google.com/search/howsearchworks/?fg=1",
                "https://www.google.com/tia/tia.png",
                "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
        };

        ArrayList<String> expectedArrayList = new ArrayList<>(Arrays.asList(expectedArray));
        //act
        ArrayList<String> links = htmlParser.getUrlsFromPage(InputDataType.LINKS, url);
        //assert
        assertEquals(expectedArrayList, links);
    }

    @Test(expected = IllegalArgumentException.class)
    public void GetUrlsFromPage_InvalidUrl_ThrowsIllegalArgumentException() throws FileNotFoundException {
        //arrange
        HtmlParser htmlParser = new HtmlParser();
        String url = "bad url";
        //act
        htmlParser.getUrlsFromPage(InputDataType.LINKS, url);
    }

    @Test(expected = FileNotFoundException.class)
    public void GetUrlsFromPage_InvalidFile_ThrowsIllegalArgumentException() throws FileNotFoundException {
        //arrange
        HtmlParser htmlParser = new HtmlParser();
        String url = "bad url";
        //act
        htmlParser.getUrlsFromPage(InputDataType.FILES, url);
    }
}

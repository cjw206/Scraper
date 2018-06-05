package com.carrick.devstuff;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {

    private static final String titleClass = "AHFaub";
    private static final String descClass = "DWPxHB";
    private static final String priceClass = "LkLjZd ScJHi HPiPcc IfEcue  ";

    public AppInfo scrapeItems (String url) {
        AppInfo app = new AppInfo();
        try {
            Document doc = Jsoup.connect(url).get();

            app = buildInformation(doc);

        }
        catch (IOException ex) {

        }

        return app;
    }

    private static AppInfo buildInformation(Document doc) {
        AppInfo app = new AppInfo();

        app.Title = getTitle(doc);
        app.Description = getDescription(doc);
        app.Price = getPrice(doc);

        return app;
    }

    private static String getTitle(Document doc) {
        String title = "";
        Elements children = doc.getElementsByClass(titleClass);

        for ( Element child : children) {
            title = child.child(0).html();
        }

        return title;
    }

    private static String getDescription(Document doc) {
        String desc = "";
        Elements children = doc.getElementsByClass(descClass);

        for (Element child : children) {
            String descArray[] = child.firstElementSibling().text().split("<br>");
            desc = descArray[0];
        }

        return desc;
    }

    private static String getPrice(Document doc) {

        String elementData = doc.getElementsByClass(priceClass).first().html();

        if (!elementData.contains("$")) {
            return "free";
        }

        String price = elementData.substring(elementData.indexOf("$") + 1, elementData.indexOf("\"></span>"));

        return price;
    }
}

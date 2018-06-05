package com.carrick.devstuff;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

public class App
{
    public static void main( String[] args ) {
        String url = args[0];

        if (url.startsWith("https://play.google.com/store/apps/details")) {
            Scraper scrape = new Scraper();

            AppInfo app = scrape.scrapeItems(url);

            System.out.println("Title: " + app.Title);

            System.out.println("Description: " + app.Description);

            System.out.println("Price: " + app.Price);

        }
        else {
            System.out.println("Not play store url");
        }



    }
}

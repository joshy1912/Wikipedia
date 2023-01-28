package de.joshy1912;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class WikipediaScraper {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a search query: ");
        String query = scanner.nextLine();

        // Replace spaces in the query with underscores, as that's how Wikipedia encodes spaces in its URLs
        query = query.replace(" ", "_");

        String url = "https://en.wikipedia.org/w/index.php?search=" + query;
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href^='/wiki/']");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            if (linkText.toLowerCase().contains(query.toLowerCase())) {
                System.out.println("https://en.wikipedia.org" + linkHref);
                break;
            }
        }
    }
}
}

package fr.univrennes1.istic.wikipediamatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikipediaHTMLExtractor {
    public static void main( String[] args ) {
        String html = "https://en.wikipedia.org/wiki/Comparison_of_digital_SLRs ";
        try {
            Document doc = Jsoup.connect(html).get();
            Elements trs = doc.select("table.wikitable tr");

            //remove header row
            trs.remove(0);
            List<String[]> dataLines= new ArrayList<>();
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                Element td = tds.first();
                Elements tdbis= Objects.requireNonNull(tds.first()).nextElementSiblings();

                assert td != null;
                String[] dataLine= new String[]{td.text()};
                //dataLine.add(td.text());
                for (Element tp : tdbis){
                    dataLine += new String[]{tp.text()};
                }


                dataLines.add(dataLine);
                String[] test =  new String[]{td.text()};
                System.out.println(Arrays.toString(test));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }



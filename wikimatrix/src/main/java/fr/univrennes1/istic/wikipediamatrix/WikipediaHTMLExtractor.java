package fr.univrennes1.istic.wikipediamatrix;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.opencsv.CSVWriter;
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
                List<String> dataLine= new ArrayList<>();
                //dataLine.add(td.text());
                for (Element tp : tdbis){
                    dataLine.add(tp.text());
                }
                String[] simpleArray = new String[ dataLine.size() ];
                dataLine.toArray( simpleArray );

                dataLines.add(simpleArray);
                CSVWriter writer = new CSVWriter(new FileWriter("c:\\test\\monitor.csv"));
                writer.writeAll(dataLines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }



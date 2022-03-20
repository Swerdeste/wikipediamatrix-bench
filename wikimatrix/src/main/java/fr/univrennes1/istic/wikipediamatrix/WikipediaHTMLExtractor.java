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

    public static void main( String[] args ) throws IOException {
        List<String[]> dataLines = createCsvData();

        for (String[] line : dataLines) {
            CSVWriter writer = new CSVWriter(new FileWriter("test.csv",true));
            writer.writeNext(line);
            writer.flush();
            writer.close();
        }
        
    }
    private static List<String[]> createCsvData() throws IOException {

        String html = "https://en.wikipedia.org/wiki/Comparison_of_Afrikaans_and_Dutch-1";
        List<String[]> dataLines = new ArrayList<>();
        try{


            Document doc = Jsoup.connect(html).get();
            Elements trs = doc.select("table.wikitable tr");

            //remove header row
            trs.remove(0);
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                Element td = tds.first();
                Elements tdbis = tds.first().nextElementSiblings();
                if (td != null) {
                    List<String> dataLine = new ArrayList<>();
                    dataLine.add(td.text());
                    for (Element tp : tdbis) {
                        if (tp != null) {
                            dataLine.add(tp.text());
                        } else {
                            dataLine.add(" ");
                        }
                    }
                    String[] simpleArray = new String[dataLine.size()];
                    dataLine.toArray(simpleArray);
                    dataLines.add(simpleArray);
                }
            }

            }catch (NullPointerException npe){}


        return dataLines;
    }

    }



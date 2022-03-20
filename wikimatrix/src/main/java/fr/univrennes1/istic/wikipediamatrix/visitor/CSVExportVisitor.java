package fr.univrennes1.istic.wikipediamatrix.visitor;



import fr.univrennes1.istic.wikipediamatrix.link.Html;
import fr.univrennes1.istic.wikipediamatrix.link.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVExportVisitor implements Visitor {

    public List<String[]> export(Link link) {
        return link.accept(this);
    }

    @Override
    public List<String[]> visitHtml(Html h) {
        String html = h.getUrl();
        List<String[]> dataLines = new ArrayList<>();
        try {
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
        }
        catch (NullPointerException npe) {} catch (IOException e) {
            e.printStackTrace();
        }

        return dataLines;
    }


}

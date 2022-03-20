package fr.univrennes1.istic.wikipediamatrix;

import com.opencsv.CSVWriter;
import fr.univrennes1.istic.wikipediamatrix.link.Html;
import fr.univrennes1.istic.wikipediamatrix.visitor.CSVExportVisitor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException {
        Html html = new Html("https://en.wikipedia.org/wiki/Comparison_of_digital_SLRs ");

        export(html);
    }

    private static void export(Html link) throws IOException {
        CSVExportVisitor exportVisitor = new CSVExportVisitor();
        List<String[]> list = exportVisitor.export(link);
        for(int i =0; i < list.size(); i++){
            String[] element = list.get(i);
            CSVWriter writer = new CSVWriter(new FileWriter("test.csv",true));
            writer.writeNext(element);
            writer.flush();
            writer.close();

        }
    }

}

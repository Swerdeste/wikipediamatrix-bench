package fr.univrennes1.istic.wikipediamatrix;

import com.opencsv.CSVWriter;
import fr.univrennes1.istic.wikipediamatrix.link.Html;
import fr.univrennes1.istic.wikipediamatrix.visitor.CSVExportVisitor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Demo {
    public static void main(Html html) throws IOException {

        export(html);
    }

    private static void export(Html link) throws IOException {
        CSVExportVisitor exportVisitor = new CSVExportVisitor();
        List<String[]> list = exportVisitor.export(link);
        String csvFileName = mkCSVFileName(link.getUrl().substring(30), 1);
        for (String[] element : list) {
            CSVWriter writer = new CSVWriter(new FileWriter("output\\html\\"+csvFileName, true));
            writer.writeNext(element);
            writer.flush();
            writer.close();

        }
    }
    private static String mkCSVFileName(String url, int n) {
        return url.trim() + "-" + n + ".csv";
    }

}

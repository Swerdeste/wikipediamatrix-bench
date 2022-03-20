package fr.univrennes1.istic.wikipediamatrix;

import fr.univrennes1.istic.wikipediamatrix.link.Html;
import fr.univrennes1.istic.wikipediamatrix.visitor.CSVExportVisitor;

public class Demo {
    public static void main(String[] args) {
        Html html = new Html("https://en.wikipedia.org/wiki/Comparison_of_digital_SLRs ");

        export(html);
    }

    private static void export(Html link) {
        CSVExportVisitor exportVisitor = new CSVExportVisitor();
        System.out.println(exportVisitor.export(link));

    }
}

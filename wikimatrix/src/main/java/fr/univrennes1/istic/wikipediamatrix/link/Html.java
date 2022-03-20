package fr.univrennes1.istic.wikipediamatrix.link;

import fr.univrennes1.istic.wikipediamatrix.visitor.Visitor;

import java.util.List;


public class Html implements Link {
    private String url;

    public Html() {
    }

    public Html(String url) {
        this.url = url;
    }

    @Override
    public List<String[]> accept(Visitor visitor) {
        List<String[]> visitHtml = visitor.visitHtml(this);
        return visitHtml;
    }

    public String getUrl() {return url;
    }

    }


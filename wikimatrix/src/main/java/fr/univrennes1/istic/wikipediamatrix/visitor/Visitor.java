package fr.univrennes1.istic.wikipediamatrix.visitor;



import fr.univrennes1.istic.wikipediamatrix.link.Html;

import java.util.List;

public interface Visitor {
    List<String[]> visitHtml(Html html);

}

package fr.univrennes1.istic.wikipediamatrix.link;


import fr.univrennes1.istic.wikipediamatrix.visitor.Visitor;

import java.util.List;

public interface Link {
    List<String[]> accept(Visitor visitor);
}

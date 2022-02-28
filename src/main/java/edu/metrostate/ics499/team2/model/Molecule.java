package edu.metrostate.ics499.team2.model;

import java.util.List;

import javax.lang.model.element.Element;

@Document (collection = "molecule")
public class Molecule {
	@Id
	private String id;

	private String symbol;
  private List<Element> elements;

    public Molecule(List<Element> Elements) {
        this.elements = elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<Element> getElements() {
        return elements;
    }
}


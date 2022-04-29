package edu.metrostate.ics499.team2.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "compound")
public class Compound {

    private final HashMap<String, Integer> elements;
    private String formula;
    private String title;
    private final String userId;

    public Compound(HashMap<String, Integer> elements, String userId) {
        this.elements = elements;
        this.formula = createFormula();
        this.userId = userId;
    }

    private String createFormula() {
        StringBuilder formula = new StringBuilder();
        for (Map.Entry<String, Integer> entry : elements.entrySet()) {
            String key = entry.getKey();
            int val = entry.getValue();
            if (val == 1) {
                formula.append(key);
            } else {
                formula.append(key).append(val);
            }
        }
        return formula.toString();
    }

    public HashMap<String, Integer> getElements() {
        return this.elements;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(Compound compound) {
        return this.formula.equals(compound.getFormula());
    }

}

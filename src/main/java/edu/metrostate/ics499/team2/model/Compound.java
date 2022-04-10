package edu.metrostate.ics499.team2.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "compound")
public class Compound {
	
	@Id
	private String id;
	
	private HashMap<String, Integer> elements;
	private String formula;
	private String title;
	private String userId;
	
	public Compound(HashMap<String, Integer> elements, String userId) {
		this.elements = elements;
		this.formula = createFormula();
		this.userId = userId;
	}
	
	private String createFormula() {
		String formula = "";
		
		for(Map.Entry<String, Integer> entry : elements.entrySet()) {
			String key = entry.getKey();
			int val = entry.getValue();
			
			if (val == 1) {
				formula += key;
			}
			else {
				formula += key + val;
			}
		}
		
		return formula;
	}
	
	public HashMap<String, Integer> getElements() {
		return this.elements;
	}
	
	public String getFormula() {
		return this.formula;
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

package edu.metrostate.ics499.team2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CompoundDTO {
	private ArrayList<Data> data;
	
	@JsonCreator
	public CompoundDTO(ArrayList<Data> data) {
		this.data = data;
	}
	
	public ArrayList<Data> getData() {
		return this.data;
	}
	
	public String getConcatPayload() {
		return data.stream().map(element -> element.getElement()).collect(Collectors.joining());
	}
	
	public ArrayList<String> getArrayPayload() {
		return data.stream().map(element -> element.getElement()).collect(Collectors.toCollection(ArrayList::new));
	}
	
	static class Data {
		private String element;
		
		@JsonCreator
		public Data (String element) {
			this.element = element;
		}
		
		public String getElement() {
			return this.element;
		}
	}	
}
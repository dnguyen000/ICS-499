package edu.metrostate.ics499.team2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

public class ElementToValidate {
	private ArrayList<Data> data;
	
	@JsonCreator
	public ElementToValidate(ArrayList<Data> data) {
		this.data = data;
	}
	
	public ArrayList<Data> getData() {
		return this.data;
	}
	
	public String getPayload() {
		return data.stream().map(element -> element.getElement()).collect(Collectors.joining());
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
//	static class Elements {
//		private final String element;
//		
//		@JsonCreator
//		public Elements(String element) {
//			this.element = element;
//		}
//		
//		public String getElement() {
//			return this.element;
//		}
//	private Data data;
////	private UserData[] data;
//	
//	@JsonCreator
//	public UserAppInputPojo(Data data) {
//		this.data = data;
//	};
//	
//	public void setData(Data data) {
//		this.data = data;
//	}
//	
//	public Data getData() {
////		return data.getElement().stream().collect(Collectors.joining());
//		return this.data;
//	}
//	
//	public String getPayload() {
//		return getData().elements.stream().map(item -> item.getElement()).collect(Collectors.joining());
//	}
//	
//	static class Data {
//		private List<Elements> elements;
//		
//		@JsonCreator
//		public Data(List<Elements> elements) {
//			this.elements = elements;
//		}
//		
//		public void setElements(List<Elements> elements) {
//			this.elements = elements;
//		}
//		
//		public List<Elements> getElements() {
//			return this.elements;
//		}
//		
////		public String getElements() {
////			return this.elements.stream().map(item -> item.get("element")).collect(Collectors.joining());
////		}
//		
//	}
//	
//	static class Elements {
//		private String element;
//		
//		@JsonCreator
//		public Elements(String element) {
//			this.element = element;
//		}
//		
//		public String getElement() {
//			return this.element;
//		}


//class MyObject {
//	private String element;
//	
//	public String getElement() {
//		return this.element;
//	}
//}
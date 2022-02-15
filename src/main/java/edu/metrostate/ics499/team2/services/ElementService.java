package edu.metrostate.ics499.team2.services;

import java.util.List;

import edu.metrostate.ics499.team2.model.Element;

public interface ElementService {
	List<Element> getElementByFamily(String family);
	
	// still mostly command line friendly
	// 2. Get item by symbol
	void getElementBySymbol(String symbol);
 
	// still mostly command line friendly
	// 3. Get name and symbol of a all items of a particular family
	void getElementsByFamily(String family);
 
	// still mostly command line friendly
	// 4. Get count of documents in the collection
	void findCountOfElements();
	
	boolean isValid(Element item);
 
	// still mostly command line friendly	
	// Print details in readable form 
	static String getItemDetails(Element item) {
//		System.out.println(
//		"Item Name: " + item.getName() + 
//		", \nSymbol: " + item.getSymbol() +
//		", \nItem Atomic Number: " + item.getAtomicNum() +
//		", \nItem Family: " + item.getFamily()
//		);         
//		return "";
		return
			"Item Name: " + item.getName() + 
			", \nSymbol: " + item.getSymbol() +
			", \nItem Atomic Number: " + item.getAtomicNumber() +
			", \nItem Family: " + item.getFamily()
		;
	}

}

package edu.metrostate.ics499.team2.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.controllers.ElementController;
import edu.metrostate.ics499.team2.model.Element;

@Service
public class ElementService {
	private final ElementController elmController;
	
	@Autowired
	public ElementService(ElementController elmController) {
		this.elmController = elmController;		
	}
	
	//	algorithm partially from url below
	//	https://www.linkedin.com/learning/learning-spring-with-spring-boot-2019/develop-a-service-object-with-spring
	public List<Element> getElementByFamily(String family) {
		Iterable<Element> elmsFams = elmController.showAll(family);
		Map<String, Element> elementsMap = new HashMap<String, Element>();
		elmsFams.forEach(element -> {
			elementsMap.put(element.getSymbol(), element);
		});
		List<Element> elms = new ArrayList<>();
		for(String symbol: elementsMap.keySet()) {
			elms.add(elementsMap.get(symbol));
		}
		// apparently this sorts by atomicNumber?
		Collections.sort(elms);  
		return elms;	
	}
	
	// still mostly command line friendly
	// 2. Get item by symbol
	public void getElementBySymbol(String symbol) {
		System.out.println("Getting item by symbol: " + symbol);
		Element elm = elmController.findElementBySymbol(symbol);
		System.out.println(getItemDetails(elm));
	}
 
	// still mostly command line friendly
	// 3. Get name and symbol of a all items of a particular family
	public void getElementsByFamily(String family) {
		System.out.println("Getting items for the family " + family);
		List<Element> list = elmController.showAll(family);
		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Symbol: " + item.getSymbol()));
	}
 
	// still mostly command line friendly
	// 4. Get count of documents in the collection
	public void findCountOfElements() {
		long count = elmController.count();
		System.out.println("Number of documents in the collection: " + count);
	}
 
	// still mostly command line friendly	
	// Print details in readable form 
	public static String getItemDetails(Element item) {
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

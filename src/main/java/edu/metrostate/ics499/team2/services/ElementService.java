package edu.metrostate.ics499.team2.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;

@Service
public class ElementService {
	private final ElementRepository elmRepo;
	
	@Autowired
	public ElementService(ElementRepository elmRepo) {
		this.elmRepo = elmRepo;		
	}
	
	public List<Element> getElementByFamily(String family) {
		Iterable<Element> elements = this.elmRepo.findAll();
		Map<String, Element> elementsMap = new HashMap<String, Element>();
		elements.forEach(element -> {
			elementsMap.put(element.getSymbol(), element);
		});
		
//		Iterable<Element> elmsFams = elmRepo.findElementByFamily(family);
//		elmsFams.forEach(elmFam -> {
//			
//		});
		
		List<Element> elms = new ArrayList<>();
		for(String symbol: elementsMap.keySet()) {
			elms.add(elementsMap.get(symbol));
		}
		return elms;		
	}

}

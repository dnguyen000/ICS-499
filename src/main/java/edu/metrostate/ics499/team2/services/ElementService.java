package edu.metrostate.ics499.team2.services;

import java.util.List;

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

	// 1. Show all the data
	public List<Element> getAllElements() {
    	return elmRepo.findAll();
	}
	
	// 2. Get item by symbol
	public Element getElementBySymbol(String symbol) {
		return elmRepo.findElementBySymbol(symbol);
	}
 
	// 3. Get name and symbol of a all items of a particular family
	public Element getElementByAtomicNumber(String atomicNumber) {
		return elmRepo.getElementByAtomicNumber(atomicNumber);
	}

}

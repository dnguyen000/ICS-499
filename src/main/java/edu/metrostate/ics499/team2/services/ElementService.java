package edu.metrostate.ics499.team2.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.metrostate.ics499.team2.exceptions.domain.FailedToLoadPTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;

import static edu.metrostate.ics499.team2.constants.FileConstants.PERIODIC_TABLE_PATH;

@Service
@Slf4j
public class ElementService {
	private final ElementRepository elmRepo;
	
	@Autowired
	public ElementService(ElementRepository elmRepo) {
		this.elmRepo = elmRepo;		
	}

	public List<Element> getAllElements() throws FailedToLoadPTException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			log.info("populating periodic table");
			return mapper.readValue(new File(PERIODIC_TABLE_PATH),
					new TypeReference<List<Element>>() {});
		} catch (IOException e) {
			throw new FailedToLoadPTException(PERIODIC_TABLE_PATH + " not found.");
		}
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

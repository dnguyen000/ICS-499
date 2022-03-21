package edu.metrostate.ics499.team2.services;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompoundService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Value("${pubchem.prolog}")
	private String prolog;
	@Value("${pubchem.input}")
	private String input;
	@Value("${pubchem.operation}")
	private String operation;
	@Value("${pubchem.output}")
	private String output;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	/**
	 * TODO:
	 * - Add handler for adding compound into compound repo if doesn't exist
	 * - Add handler for when request to PUG API doesn't return element
	 * - Add local variable for last request sent (6 second delay if within window of last request)
	 * - 
	 * 
	 */
	
	public Map<String, ?> validateInput(String molecule, ArrayList<String> elementsArray) {
		LOG.info("calling PUG API with argument: " + molecule);
		
		return restTemplate.getForObject(prolog + input + molecule + operation + output, Map.class);
	}
}
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
	
	@Value("${pubchem.host}")
	private String host;
	
	@Value("${pubchem.pathPrefix}")
	private String pathPrefix;
	
	@Value("${pubchem.pathPostfix}")
	private String pathPostfix;
	
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
		
		return restTemplate.getForObject(host + pathPrefix + molecule + pathPostfix, Map.class);
	}
}

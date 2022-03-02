package edu.metrostate.ics499.team2.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.metrostate.ics499.team2.model.ElementToValidate;
import edu.metrostate.ics499.team2.services.ElementService;

@RestController
@RequestMapping("/compound")
public class CompoundController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping(value = "validate")
	public Map<String, ?> process(@RequestBody  ElementToValidate payload) {
		String prefix = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/fastformula/";
		String postfix = "/property/MolecularFormula,MolecularWeight,Title/JSON";
		
		String molecule = payload.getPayload();
		LOG.info(molecule);
		
		Map<String, ?> response = restTemplate.getForObject(prefix + molecule + postfix, Map.class);
		
		return response;
		
		
//		String molecule = payload.get("data").stream().map(item -> {
//			LOG.info("symbol: " + item.get("element"));
//			
//			return item.get("element");
//			
//		}).collect(Collectors.joining());
	}

}


class UserCompound {
	private String element;
	
	public UserCompound(String element) {
		this.element = element;
	}
	
	public String getElment() {
		return this.element;
	}
}

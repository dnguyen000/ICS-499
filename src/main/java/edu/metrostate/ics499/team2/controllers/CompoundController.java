package edu.metrostate.ics499.team2.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.CompoundDTO;
import edu.metrostate.ics499.team2.services.CompoundService;

@RestController
@RequestMapping("/compound")
public class CompoundController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CompoundService compoundService;
	
	@GetMapping(value = "validate")
	public Map<String, ?> validate(@RequestBody  CompoundDTO payload) {	
		String molecule = payload.getConcatPayload();
		ArrayList<String> elementsArray = payload.getArrayPayload();
		LOG.info("Controller received: " + molecule);
		
		
		return compoundService.validateInput(molecule, elementsArray);
	}

}
package edu.metrostate.ics499.team2.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.model.CompoundDTO;
import edu.metrostate.ics499.team2.services.CompoundService;

@RestController
@RequestMapping("/compound")
public class CompoundController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CompoundService compoundService;
	
	@PostMapping(value = "validate")
	public Compound validate(@RequestBody  CompoundDTO payload) {
		Compound compound = new Compound(payload.getMappedPayload(), payload.getUserId());
		LOG.info("Controller received: " + compound.getFormula());
		
		
		return compoundService.validateInput(compound);
	}

}
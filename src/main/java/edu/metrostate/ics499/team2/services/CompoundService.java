package edu.metrostate.ics499.team2.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.model.PugApiDTO;
import edu.metrostate.ics499.team2.repositories.CompoundRepository;

import static edu.metrostate.ics499.team2.constants.PugApiConstants.*;

@Service
public class CompoundService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CompoundRepository compoundRepo;
	
	/**
	 * TODO:
	 * - Add handler for when request to PUG API doesn't return element
	 * - Add local variable for last request sent (6 second delay if within window of last request)
	 * - 
	 * 
	 */
	
	public boolean doesValueExistInRepo(String formula) {
		return compoundRepo.findCompoundByFormula(formula).size() > 0 ? true : false;
	}
	
	private Compound retrieveCompoundFromRepo(String formula) {
		LOG.info("Value " + formula + " exists in repo, retrieving...");
		
		return compoundRepo.findCompoundByFormula(formula).get(0);
	}
	
	private Compound retrieveCompoundFromPugApi(String formula, Compound compound) {
		LOG.info("calling PUG API with argument: " + formula);
		
		PugApiDTO pugApiValue = restTemplate.getForObject(PUG_PROLOG + PUG_INPUT + formula + PUG_OPERATION + PUG_OUTPUT, PugApiDTO.class);
		compound.setTitle(pugApiValue.getFirstPropertyTitle());
		
		return compoundRepo.save(compound);
	}
	
	public Compound validateInput(Compound compound) {
		String formula = compound.getFormula();	
		
		return doesValueExistInRepo(formula) ? retrieveCompoundFromRepo(formula) : retrieveCompoundFromPugApi(formula, compound);
	}
}
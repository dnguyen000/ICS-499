package edu.metrostate.ics499.team2.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
//import org.springframework.test.web.client.MockRestServiceServer;

import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.model.PugApiDTO;
import edu.metrostate.ics499.team2.repositories.CompoundRepository;

import java.util.List;

import static edu.metrostate.ics499.team2.constants.PugApiConstants.*;

@Service
public class CompoundService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CompoundRepository compoundRepo;
	
	@Autowired
	private QuizService quizService;
	
	/**
	 * TODO:
	 * - Add handler for when request to PUG API doesn't return element
	 * - Add local variable for last request sent (6 second delay if within window of last request)
	 * - 
	 * 
	 */
	
	public boolean doesValueExistInRepo(String formula) {
		return compoundRepo.findCompoundByFormula(formula).size() > 0;
	}
	
	private Compound retrieveCompoundFromRepo(String formula) {
		LOG.info("Value " + formula + " exists in repo, retrieving...");
		
		return compoundRepo.findCompoundByFormula(formula).get(0);
	}
	
	private Compound retrieveCompoundFromPugApi(String formula, Compound compound) {
		LOG.info("calling PUG API with argument: " + formula);
		Compound returnValue = null;
		
		try {
			PugApiDTO pugApiValue = restTemplate.getForObject(PUG_PROLOG + PUG_INPUT + formula + PUG_OPERATION + PUG_OUTPUT, PugApiDTO.class);
			String userId = compound.getUserId();
			compound.setTitle(pugApiValue.getFirstPropertyTitle());
			returnValue = compoundRepo.save(compound);
			
			quizService.createNewQuizes(compound, userId, "compound");
			quizService.createNewQuizes(compound, userId, "element");
		} catch (HttpStatusCodeException exception) {
			LOG.error("Received " + exception.getStatusCode().value() + " response code from PUG API.");
		}
		
		return returnValue;
	}
	
	public Compound validateInput(Compound compound) {
		String formula = compound.getFormula();	
		
		return doesValueExistInRepo(formula) ? retrieveCompoundFromRepo(formula) : retrieveCompoundFromPugApi(formula, compound);
	}

	public List<Compound> getCompoundsByUserId(String userId) {
		return compoundRepo.findCompoundByUserId(userId);
	}
}

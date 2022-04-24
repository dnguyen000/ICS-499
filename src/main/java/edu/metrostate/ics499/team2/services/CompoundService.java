package edu.metrostate.ics499.team2.services;

import edu.metrostate.ics499.team2.exceptions.domain.PugApiException;
import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.model.PugApiDTO;
import edu.metrostate.ics499.team2.repositories.CompoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

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
     */

    public boolean doesValueExistInRepo(String formula) {
        return compoundRepo.findCompoundByFormula(formula).size() > 0;
    }

    private Compound retrieveCompoundFromRepo(String formula) {
        LOG.info("Value " + formula + " exists in repo, retrieving...");
        return compoundRepo.findCompoundByFormula(formula).get(0);
    }

    private Compound retrieveCompoundFromPugApi(String formula, Compound compound) throws PugApiException {
        LOG.info("Calling PUG API with argument: " + formula);
        String pubChemUrl = PUG_PROLOG + PUG_INPUT + formula + PUG_OPERATION + PUG_OUTPUT;
        try {
//            LOG.info("Sending PugAPI url in service: {}", pubChemUrl);
            PugApiDTO pugApiValue = restTemplate.getForObject(pubChemUrl, PugApiDTO.class);
            assert pugApiValue != null;
            compound.setTitle(pugApiValue.getFirstPropertyTitle());
            if (compound.getUserId() != null) {
                quizService.createCompoundQuizes(compound);
                return compoundRepo.save(compound);
            } else
                return compound;
        } catch (HttpStatusCodeException exception) {
            // to do: parse the JSON returned as an error to get a useful response
            // LOG.error(exception.getResponseBodyAsString());
//            LOG.error("Received " + exception.getStatusCode().value() + " response code from PUG API.");
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new PugApiException("404 error from PugAPI url: " + pubChemUrl);
            } else
                throw new PugApiException("pug api error");
        }
    }

    public Compound validateInput(Compound compound) throws PugApiException {
        String formula = compound.getFormula();
        return doesValueExistInRepo(formula) ? retrieveCompoundFromRepo(formula) : retrieveCompoundFromPugApi(formula, compound);
    }
}

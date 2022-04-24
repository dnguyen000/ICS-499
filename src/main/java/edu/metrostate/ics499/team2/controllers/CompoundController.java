package edu.metrostate.ics499.team2.controllers;

import edu.metrostate.ics499.team2.exceptions.domain.PugApiException;
import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.model.CompoundDTO;
import edu.metrostate.ics499.team2.services.CompoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compound")
public class CompoundController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private CompoundService compoundService;

    @PostMapping(value = "validate")
    public Compound validate(@RequestBody CompoundDTO payload) throws PugApiException {
        Compound compound = new Compound(payload.getMappedPayload(), payload.getUserId());
        LOG.info("Controller received formula, userId: {}, {}", compound.getFormula(), compound.getUserId());
        return compoundService.validateInput(compound);
    }

}
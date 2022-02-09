package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.Quize;
import edu.metrostate.ics499.team2.repositories.QuizeRepository;



@RestController
@RequestMapping("/quize")
public class QuizeController {
	@Autowired
	private QuizeRepository quizeRepo;
	
	@GetMapping(value = "/all")
	public List<Quize> list() {
	    return quizeRepo.findAll();
	}
	@GetMapping(value = "{quizeId}")
	public Quize getQuizeById(String id) {
		return this.quizeRepo.findByQuizeId(id);
	}
	
	@PostMapping("/add")
	public Quize create(@RequestBody final Quize quize) {
		return quizeRepo.save(quize);
		
	}
}
	

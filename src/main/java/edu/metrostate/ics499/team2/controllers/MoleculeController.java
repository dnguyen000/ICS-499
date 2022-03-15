package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.model.Molecule;
import edu.metrostate.ics499.team2.repositories.MoleculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/molecule")
public class MoleculeController {

	@Autowired
	private MoleculeRepository moleculeRepo;

	@GetMapping(value = "/all")
	public List<Molecule> list() {
		return moleculeRepo.findAll();
	}

	@GetMapping(value = "{element}")
	public Molecule getElementBySymbol(String symbol) {
		return this.moleculeRepo.findBySymbol(symbol);
	}
	
	@PostMapping("/add")
	public Molecule create(@RequestBody final Molecule element) {
		return moleculeRepo.save(element);
	}
}

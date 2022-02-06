package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;

@RestController
@RequestMapping("/flashcards")
public class FlashcardController {
	
	@Autowired
	private FlashcardRepository flashcardRepo;
	
	@GetMapping("/all")
	public List<Flashcard> list() {
		return flashcardRepo.findAll();
	}
	
	@GetMapping("{id}")
	public Flashcard get(@PathVariable Long id) {
		return flashcardRepo.getOne(id);
	}
}

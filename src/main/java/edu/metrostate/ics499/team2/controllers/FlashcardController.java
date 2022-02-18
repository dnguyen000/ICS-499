package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;
import edu.metrostate.ics499.team2.services.FlashcardService;

@RestController
@RequestMapping("/flashcards")
public class FlashcardController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FlashcardService flashcardService;
	
	@GetMapping(value = "/all")
	public @ResponseBody List<Flashcard> list() {
		LOG.info("Getting all flashcards.");
		return flashcardService.list();
	}
	
	@GetMapping(value = "/{gameId}")
	public Flashcard getFlashcardById(String id) {
		LOG.info("Return flashcard by id.");
		return this.flashcardService.getFlashcardById(id);
	}
	
	@GetMapping(value = "/questions")
	public List<Flashcard> queryQuestions(String question) {
		LOG.info("Getting all flashcards that match the question");
		return flashcardService.queryQuestions(question);
	} 
	
	@GetMapping(value = "/answers")
	public List<Flashcard> queryAnswers(String answer) {
		LOG.info("Getting all flashcards that match the answer");
		return flashcardService.queryAnswers(answer);
	}
	
	@PostMapping("/add")
	public Flashcard create(@RequestBody final Flashcard flashcard) {
		return flashcardService.create(flashcard);
	}
}

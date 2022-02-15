package edu.metrostate.ics499.team2.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.controllers.FlashcardController;
import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.services.FlashcardService;

@Service
public class FlashcardServiceImp implements FlashcardService {
	
	@Autowired
	private FlashcardController flashcardController;

	@Override
	public boolean isValid(Flashcard flashcardToAdd) {
		return flashcardController.list().stream()
				.filter(fc -> fc.getQuestion().equalsIgnoreCase(flashcardToAdd.getQuestion()))
				.filter(fc -> fc.getAnswer().equalsIgnoreCase(flashcardToAdd.getAnswer())).toList().size() > 0 ? false : true;
	}

}

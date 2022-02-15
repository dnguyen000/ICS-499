package edu.metrostate.ics499.team2.services;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.controllers.FlashcardController;
import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.serviceImp.FlashcardServiceImp;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FlashcardServiceImpTest {
	@Autowired
	private FlashcardServiceImp flashcardServiceImp;
	
	@Test
	@DisplayName("it should return false")
	void isValid_returns_false() {
		String question1 = "Does this work?";
		String answer1 = "yes";
		Flashcard fc = new Flashcard(question1, answer1);
		
		assertFalse(flashcardServiceImp.isValid(fc));
	}
	
	@Test
	@DisplayName("it should return true")
	void isValid_returns_true() {
		String question1 = "Is this a unique value?";
		String answer1 = "Maybe";
		Flashcard fc = new Flashcard(question1, answer1);
		
		assertTrue(flashcardServiceImp.isValid(fc));
	}

}

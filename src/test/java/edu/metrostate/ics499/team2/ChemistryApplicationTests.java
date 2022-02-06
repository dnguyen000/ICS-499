package edu.metrostate.ics499.team2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.metrostate.ics499.team2.controllers.FlashcardController;
import edu.metrostate.ics499.team2.model.Flashcard;

@SpringBootTest
class ChemistryApplicationTests {
	
	@Autowired
	private FlashcardController flashcardController;

	@Test
	void contextLoads() {
		
		Flashcard f1 = flashcardController.get((long) 2);
		
		System.out.println("Test output: " + f1.toString());
	}

}

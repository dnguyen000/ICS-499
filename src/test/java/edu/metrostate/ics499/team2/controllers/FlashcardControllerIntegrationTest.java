package edu.metrostate.ics499.team2.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;

import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FlashcardControllerTest {
	@Autowired
	private FlashcardRepository flashcardRepo;
	
	@Autowired
	private FlashcardController flashcardController;

	@Test
	@DisplayName("It should instantiate the controller")
	void testInit() {
		assertNotNull(flashcardController);
	}
	
	@Test
	@DisplayName("It should not insert the flashcard into the db if it is not unique")
	void testCreateFail() {
		int beforeInsert = flashcardController.list().size();
		String question1 = "Does this work?";
		String answer1 = "yes";
		Flashcard f1 = new Flashcard(question1, answer1);
		flashcardController.create(f1);
		assertEquals(beforeInsert, flashcardController.list().size());
	}
	
	@Test
	@DisplayName("It should insert the flashcard into the db")
	void testCreate() {
		String question1 = "Does this work?";
		String answer1 = "yes";
		FlashcardRepository repoMock = Mockito.spy(flashcardRepo);
		
		Flashcard result = Mockito.doReturn(true).when(repoMock).save(Mockito.any());
		Flashcard f1 = new Flashcard(question1, answer1);
		flashcardController.create(f1);
		assertNotNull(result);
	}
	
	@Test
	@DisplayName("It should return a list of questions from the db")
	void testQueryQuestion() {
		String question1 = "Does this work?";
		String answer1 = "yes";
		Flashcard f1 = new Flashcard(question1, answer1);
		flashcardController.create(f1);
		
		String question2 = "Does this still work?";
		String answer2 = "no";
		Flashcard f2 = new Flashcard(question2, answer2);
		flashcardController.create(f2);
		
		assertTrue(flashcardController.queryQuestions(question1).size() > 0);
	}
	
	@Test
	@DisplayName("It should return a list of questions from the db")
	void testQueryAnswers() {
		String question1 = "Does this work?";
		String answer1 = "yes";
		Flashcard f1 = new Flashcard(question1, answer1);
		flashcardController.create(f1);
		
		String question2 = "Does this still work?";
		String answer2 = "no";
		Flashcard f2 = new Flashcard(question2, answer2);
		flashcardController.create(f2);
		assertTrue(flashcardController.queryAnswers(answer1).size() > 0);
	}
	
	
	@AfterAll
	public void tearDown() throws Exception {
		//TODO: Find a way to clean up DB after each test
	}
}

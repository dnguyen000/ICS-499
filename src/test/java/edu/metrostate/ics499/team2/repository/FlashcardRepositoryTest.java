package edu.metrostate.ics499.team2.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FlashcardRepositoryTest {
	
	@Autowired
	private FlashcardRepository flashcardRepo;
	
	@BeforeEach
	void setUp() {
		Flashcard fc1 = new Flashcard("Is this the first?", "yes");
		Flashcard fc2 = new Flashcard("Is this the first still?", "no");
		Flashcard fc3 = new Flashcard("iS ThIs ThE fIrSt UnIqUe?", "no");
		Flashcard fc4 = new Flashcard("Is this the last?", "yes");
		flashcardRepo.save(fc1);
		flashcardRepo.save(fc2);
		flashcardRepo.save(fc3);
		flashcardRepo.save(fc4);
	}
	
	@AfterEach
	void tearDown() {
		flashcardRepo.deleteAll();
	}
	
	@Test
	@DisplayName("it should return an empty array if findByQuestion query returns no result")
	void test_findByQuestion_return_empty() {
		
		assertEquals(0, flashcardRepo.findByQuestion("Does this query exist?").size());
	}

	@Test
	@DisplayName("it should query the repo by questions and return a list of flashcards")
	void test_findByQuestion() {
		
		assertNotNull(flashcardRepo.findByQuestion("Is this the first?"));
	}
	
	@Test
	@DisplayName("it should return an empty array if findByAnswer query returns no result")
	void test_findByAnswer_return_empty() {
		
		assertEquals(0, flashcardRepo.findByQuestion("This is an invalid answer.").size());
	}
	
	@Test
	@DisplayName("it should query the repo by answers and return a list of flashcards")
	void test_findByAnswer() {
		
		assertNotNull(flashcardRepo.findByAnswer("yes"));
		assertEquals(2, flashcardRepo.findByAnswer("yes").size());
	}
	
	@Test
	@DisplayName("it should find the flashcard by gameId")
	void test_findByGameId() {	
		List<Flashcard> allFlashcards = flashcardRepo.findAll();
		
		Flashcard fc = allFlashcards.get(0);
		Flashcard result = flashcardRepo.findByGameId(fc.getGameId());
		
		assertTrue(fc.getGameId().equals(result.getGameId()));
	}

}

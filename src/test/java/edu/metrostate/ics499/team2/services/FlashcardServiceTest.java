package edu.metrostate.ics499.team2.services;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FlashcardServiceTest {
	
	@Autowired
	private FlashcardService flashcardService;
	
	@MockBean
	private FlashcardRepository flashcardRepo;
	
	private final String question1 = "is this mock value 1?";
	private final String question2 = "is this mock value 2?";
	private final String question3 = "is this mock value 3?";
	private final String question4 = "is this mock value 4?";
	private final String question5 = "This might be unique?";
	private final String answerYes = "yes";
	private final String answerNo = "no";
	
	@BeforeEach
	void setUp() {
		when(flashcardRepo.findAll()).thenReturn(
				Stream.of(new Flashcard(question1, answerYes), 
						new Flashcard(question2, answerYes),
						new Flashcard(question3, answerNo),
						new Flashcard(question4, answerNo),
						new Flashcard(question5, answerYes))
				.collect(Collectors.toList()));
	}
	
	@Test
	@DisplayName("it should request findAll")
	void test_list() {
		flashcardService.list();
		
		assertEquals(5, flashcardService.list().size());
	}
	
	@Test
	@DisplayName("it should not insert into the DB if not valid")
	void test_create_fail() {
		Flashcard fc = new Flashcard(question1, answerYes);
		
		assertNull(flashcardService.create(fc));
	}
	
	@Test
	@DisplayName("it should insert into the DB")
	void test_create_true() {
		Flashcard fc = new Flashcard("is this question the same as the other mocks?", "no");
		
		when(flashcardRepo.save(fc)).thenReturn(fc);
		
		assertEquals(fc.getQuestion(), flashcardService.create(fc).getQuestion());
		assertEquals(fc.getAnswer(), flashcardService.create(fc).getAnswer());
	}
	
	@Test
	@DisplayName("it should return the question")
	void test_get_questions() {
		List<Flashcard> returnValue = new ArrayList<>();
		returnValue.add(new Flashcard(question1, answerYes));
		returnValue.add(new Flashcard(question2, answerYes));
		returnValue.add(new Flashcard(question3, answerNo));
		returnValue.add(new Flashcard(question4, answerNo));
		when(flashcardRepo.findByQuestion(question1)).thenReturn(returnValue);
		
		List<Flashcard> result = flashcardService.queryQuestions(question1);
		
		assertNotNull(result);
	}
	
	@Test
	@DisplayName("it should return false")
	void isValid_returns_false() {
		Flashcard fc = new Flashcard(question1, answerYes);
		
		boolean result = flashcardService.isValid(fc);
		
		assertFalse(result);
	}
	
	@Test
	@DisplayName("it should return true")
	void isValid_returns_true() {
		String question1 = "Is this a unique value?";
		String answer1 = "Maybe";
		Flashcard fc = new Flashcard(question1, answer1);
		
		assertTrue(flashcardService.isValid(fc));
	}

}

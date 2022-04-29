package edu.metrostate.ics499.team2.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.metrostate.ics499.team2.model.game.Flashcard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import edu.metrostate.ics499.team2.repositories.FlashcardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class FlashcardServiceTest {

	@Autowired
	private FlashcardService flashcardService;
	
	private FlashcardRepository repoMock;
	
	private final String question1 = "is this mock value 1?";
	private final String question2 = "is this mock value 2?";
	private final String question3 = "is this mock value 3?";
	private final String question4 = "is this mock value 4?";
	private final String answerYes = "yes";
	private final String answerNo = "no";

	@BeforeEach
	void setUp() {
		repoMock = mock(FlashcardRepository.class);
		ReflectionTestUtils.setField(flashcardService, "flashcardRepo", repoMock);
		String question5 = "This might be unique?";

		when(repoMock.findAll()).thenReturn(
				Stream.of(new Flashcard(question1, answerYes),
						new Flashcard(question2, answerYes),
						new Flashcard(question3, answerNo),
						new Flashcard(question4, answerNo),
						new Flashcard(question5, answerYes))
				.collect(Collectors.toList()));
	}
	
	@AfterEach
	void tearDown() {
		
	}
	
	@Test
	@DisplayName("it should request findAll")
	void test_list() {
		flashcardService.list();
		
		verify(repoMock, times(1)).findAll();
	}

	@Test
	@DisplayName("it should not insert into the DB if not valid")
	void test_create_fail() {
		Flashcard fc = new Flashcard(question1, answerYes);
		assertNull(flashcardService.create(fc));
	}
	
//	@Test
//	@DisplayName("it should throw if flashcard is not valid")
//	void test_create_should_throw() {
//		Flashcard fc = new Flashcard(question1, answerYes);
//		
//		Exception exception = assertThrows(MongoException.class, () -> {
//			flashcardService.create(fc);
//		});
//		
//		String expectedMessage = "Invalid entry into database";
//		String actualMessage = exception.getMessage();
//		
//		assertTrue(actualMessage.contains(expectedMessage));
//	}
	
	@Test
	@DisplayName("it should insert into the DB")
	void test_create_true() {
		Flashcard fc = new Flashcard("is this question the same as the other mocks?", "no");
		
		Mockito.doReturn(fc).when(repoMock).save(fc);
		
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
		when(repoMock.findByQuestion(question1)).thenReturn(returnValue);

		List<Flashcard> result = flashcardService.queryByQuestion(question1);

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
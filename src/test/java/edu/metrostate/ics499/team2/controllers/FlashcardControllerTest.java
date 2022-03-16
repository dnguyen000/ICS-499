package edu.metrostate.ics499.team2.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.model.FlashcardDTO;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;
import edu.metrostate.ics499.team2.services.FlashcardService;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FlashcardControllerTest {

	private FlashcardRepository repoMock;
	
	@Autowired
	private FlashcardService flashcardService;
	
	@Autowired
	private FlashcardController flashcardController;
	
    @Autowired
    private WebApplicationContext webApplicationContext;
	
	@Captor
	ArgumentCaptor<Flashcard> valueCaptor;
	
    private MockMvc mockMvc;

	private final String question = "is this unique?";
	private final String answer = "yes";
	private FlashcardDTO flashcardDto = new FlashcardDTO(question, answer);
	
	@BeforeEach
	void setUp() {
		repoMock = mock(FlashcardRepository.class);
		ReflectionTestUtils.setField(flashcardService, "flashcardRepo", repoMock);
	}

	@Test
	@DisplayName("It should instantiate the controller")
	void testInit() {
		assertNotNull(flashcardController);
	}
	
	@Test
	@DisplayName("It should not insert the flashcard into the db if it is not unique")
	void testCreateFail() {
		ArrayList<Flashcard> mockResult = new ArrayList<Flashcard>();
		mockResult.add(new Flashcard(question, answer));

		
		Mockito.doReturn(mockResult).when(repoMock).findAll();

		int beforeInsert = flashcardController.list().size();

		flashcardController.create(flashcardDto);
		
		assertEquals(beforeInsert, flashcardController.list().size());
	}
	
	@Test
	@DisplayName("It should insert the flashcard into the db")
	void testCreate() {
		String question1 = "Make me unique?";
		String answer1 = "yes";
		FlashcardDTO flashcardDto2 = new FlashcardDTO(question1, answer1);
		Flashcard fc = new Flashcard(question1, answer1); 

		Mockito.doReturn(new ArrayList<Flashcard>()).when(repoMock).findAll();
		Mockito.doReturn(fc).when(repoMock).save(fc);
		
		flashcardController.create(flashcardDto2);
		
		verify(repoMock, times(1)).save(valueCaptor.capture());
	}
	
	@Test
	@DisplayName("It should request findByQuestion when queryQuestion called")
	void testQueryQuestion() {
		flashcardController.queryQuestions(question);
		
		verify(repoMock, times(1)).findByQuestion(question);
	}
	
	@Test
	@DisplayName("It should return a list of questions from the db")
	void testQueryAnswers() {
		flashcardController.queryAnswers(answer);
		
		verify(repoMock, times(1)).findByAnswer(answer);
	}
}

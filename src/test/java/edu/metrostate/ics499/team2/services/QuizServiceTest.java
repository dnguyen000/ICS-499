package edu.metrostate.ics499.team2.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import edu.metrostate.ics499.team2.model.game.Quiz;
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

import edu.metrostate.ics499.team2.repositories.QuizRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class QuizServiceTest {
	
	@Autowired
	private QuizService quizService;
	
	private QuizRepository repoMock;

	@BeforeEach
	void setUp() {
		repoMock = mock(QuizRepository.class);
		ReflectionTestUtils.setField(quizService, "quizRepo", repoMock);
	}
	
	@AfterEach
	void tearDown() {
		repoMock.deleteAll();
	}
	
	@Test
	@DisplayName("it should not insert quiz into the repo if a quiz already exists for a user")
	void createQuiz_fail() {
		Quiz q1 = new Quiz("this shouldn't work", "yes", "12345");
		List<Quiz> quizList = new ArrayList<>();
		quizList.add(q1);
		
		Mockito.doReturn(quizList).when(repoMock).findAll();

		quizService.createQuiz(q1);
		
		verify(repoMock, never()).save(q1);
	}

	@Test
	@DisplayName("it should not insert quiz into the DB if the userId is non-empty")
	void createQuiz_fail_2() {
		Quiz q1 = new Quiz("", "this shouldn't work", "yes");
		List<Quiz> quizList = new ArrayList<>();

		Mockito.doReturn(quizList).when(repoMock).findAll();

		quizService.createQuiz(q1);

		verify(repoMock, never()).save(q1);
	}
	
	@Test
	@DisplayName("it should insert a new quiz into the repo")
	void createQuiz_success() {
		Quiz q1 = new Quiz("12345", "Does this work?", "yes");
		
		quizService.createQuiz(q1);
		
		verify(repoMock, times(1)).save(q1);
	}

	@Test
	@DisplayName("it should same quiz but for different user")
	void createQuiz_success_2() {
		Quiz q1 = new Quiz("12345", "the first quiz", "yes");
		Quiz q2 = new Quiz("54321","the first quiz", "yes");
		List<Quiz> quizList = new ArrayList<>();
		quizList.add(q1);

		Mockito.doReturn(quizList).when(repoMock).findAll();

		quizService.createQuiz(q2);

		verify(repoMock, times(1)).save(q2);
	}
	
	@Test
	@DisplayName("it should iterate through the list of quizes and insert into the repo")
	void createQuizes() {
		Quiz q1 = new Quiz("Does this work?", "yes", "12345");
		
		List<Quiz> quizList = new ArrayList<>();
		quizList.add(q1);
		quizList.add(q1);
		quizList.add(q1);
		quizList.add(q1);
		quizList.add(q1);
		
		quizService.createQuizes(quizList);
		
		verify(repoMock, times(5)).save(q1);
	}
	
	@Test
	@DisplayName("it should request findAll from repo")
	void list() {
		quizService.list();
		
		verify (repoMock, times(1)).findAll();
	}

	@Test
	@DisplayName("it should request findQuizByUserId from repo")
	void findQuizByUserId() {
		String userId = "12345";
		quizService.findQuizByUserId(userId);

		verify (repoMock, times(1)).findQuizByUserId(userId);
	}

	@Test
	@DisplayName("it should request findQuizByQuizType from repo")
	void findQuizByQuizType() {
		String quizType = "test";
		quizService.findQuizByQuizType(quizType);

		verify (repoMock, times(1)).findQuizByQuizType(quizType);
	}

}

package edu.metrostate.ics499.team2.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Quiz;

	@ExtendWith(SpringExtension.class)
	@SpringBootTest
	public class QuizControllerTest {		
		@Autowired
		private QuizController quizController;

		@Test
		@DisplayName("It should instantiate the controller")
		void testInit() {
			assertNotNull(quizController);
		}
		
		@Test
		@DisplayName("It should insert the quize into the db")
		void testCreate() {
			String question = "Does this work?";
			String answer = "yes";
			Quiz quiz = new Quiz(question, answer);
			quizController.create(quiz);
			assertNotNull(quizController.getQuizById(quiz.getGameId()));
		}
		
		@Test
		@DisplayName("It should return a list of questions from the db")
		void testQueryQuestion() {
			
			String question = "Does questions display?";
			String answer = "yes";
			Quiz quiz = new Quiz(question, answer);
			quizController.create(quiz);
			
			
			assertTrue(quizController.queryQuestion(question).size() > 0);
		}
		
		@Test
		@DisplayName("It should return a list of quiz answers from the db")
		void testQueryAnswers() {
			String question = "Does questions display?";
			String answer = "yes";
			Quiz quiz = new Quiz(question, answer);
			quizController.create(quiz);
			assertTrue(quizController.queryAnswer(answer).size() > 0);
		}
		@Test
		@DisplayName("It should return a list of quiz description from the db")
		void testQueryDescription() {
			String question = "Does questions display?";
			String answer = "yes";
			Quiz quiz = new Quiz(question, answer);
			quizController.create(quiz);
			assertTrue(quizController.queryAnswer(answer).size() > 0);
		}
		@After
		public void tearDown() throws Exception {
			//TODO: Find a way to clean up DB after each test
		}
		
		
		

}

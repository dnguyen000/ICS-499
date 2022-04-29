package edu.metrostate.ics499.team2.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import edu.metrostate.ics499.team2.model.game.Quiz;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.repositories.QuizRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepo;

    @BeforeEach
    void setUp() {
        Quiz q1 = new Quiz("12345", "Is this the first?", "yes", "test");
        Quiz q2 = new Quiz("12345", "Is this the first still?", "no", "test");
        Quiz q3 = new Quiz("54321", "iS ThIs ThE fIrSt UnIqUe?", "no", "non-test");
        Quiz q4 = new Quiz("6789", "Is this the last?", "yes", "still-testing");

        quizRepo.save(q1);
        quizRepo.save(q2);
        quizRepo.save(q3);
        quizRepo.save(q4);
    }

    @AfterEach
    void tearDown() {
        quizRepo.deleteAll();
    }

    @Test
    @DisplayName("it should return an empty array if findByQuestion query returns no result")
    void test_findByQuestion_return_empty() {

        assertEquals(0, quizRepo.findByQuestion("Does this query exist?").size());
    }

    @Test
    @DisplayName("it should query the repo by questions and return a list of flashcards")
    void test_findByQuestion() {

        assertNotNull(quizRepo.findByQuestion("Is this the first?"));
    }

    @Test
    @DisplayName("it should return an empty array if findByAnswer query returns no result")
    void test_findByAnswer_return_empty() {

        assertEquals(0, quizRepo.findByQuestion("This is an invalid answer.").size());
    }

    @Test
    @DisplayName("it should query the repo by answers and return a list of flashcards")
    void test_findByAnswer() {

        assertNotNull(quizRepo.findByAnswer("yes"));
        assertEquals(2, quizRepo.findByAnswer("yes").size());
    }

    @Test
    @DisplayName("it should return a list of quizes from a specific user")
    void test_findQuizByUserId() {
        List<Quiz> result = quizRepo.findQuizByUserId("12345");

        assertTrue(result.size() == 2);
    }

    @Test
    @DisplayName("it should return a list of quizes by type")
    void test_findQuizByQuizType() {
        List<Quiz> result = quizRepo.findQuizByQuizType("test");

        assertTrue(result.size() == 2);
    }
}

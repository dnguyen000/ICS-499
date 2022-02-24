package edu.metrostate.ics499.team2.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.model.Quiz;
import edu.metrostate.ics499.team2.model.RegisteredUser;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
	Quiz findByGameId(String id);
	List<Quiz> findByQuestion(String question);
	List<Quiz> findByAnswer(String Answer);	
}

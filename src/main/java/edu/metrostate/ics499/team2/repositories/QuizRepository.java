package edu.metrostate.ics499.team2.repositories;

import java.util.List;

import edu.metrostate.ics499.team2.model.game.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import edu.metrostate.ics499.team2.model.RegisteredUser;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
	List<Quiz> findByQuestion(String question);
	List<Quiz> findByAnswer(String Answer);

	@Query("{ userId: '?0' }")
	List<Quiz> findQuizByUserId(String userId);

	@Query("{ quizType: '?0' }")
	List<Quiz> findQuizByQuizType(String quizType);
}

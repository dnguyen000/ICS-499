package edu.metrostate.ics499.team2.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.Quiz;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String>{
	public List <Quiz> findByGameId(String Id);
	List<Quiz> findByQuestion(String question);
	List<Quiz> findByAnswer(String answer);
	
}


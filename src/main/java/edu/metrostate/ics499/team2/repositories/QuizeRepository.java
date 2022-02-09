package edu.metrostate.ics499.team2.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.model.Quize;
import edu.metrostate.ics499.team2.model.RegisteredUser;

@Repository
public interface QuizeRepository extends MongoRepository<Quize, String>{
	Quize findByQuizeId(String id);
	List<Quize> findByQuestion(String question);
	List<Quize> findByCorrectAnswer(String correctAnswer);
	
}


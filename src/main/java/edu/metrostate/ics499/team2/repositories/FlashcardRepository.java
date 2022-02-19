package edu.metrostate.ics499.team2.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.Flashcard;

@Repository
public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
	Flashcard findByGameId(String id);
	
	@Query("{ 'question' : { $regex: ?0 } }")
	List<Flashcard> findByQuestion(String question);
	
	@Query("{ 'answer' : { $regex: ?0 } }")
	List<Flashcard> findByAnswer(String answer);
}

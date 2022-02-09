package edu.metrostate.ics499.team2.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.metrostate.ics499.team2.model.Flashcard;

@Repository
public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
	Flashcard findByGameId(String id);
	List<Flashcard> findByQuestion(String question);
	List<Flashcard> findByAnswer(String answer);
}

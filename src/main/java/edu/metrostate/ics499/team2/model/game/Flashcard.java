package edu.metrostate.ics499.team2.model.game;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flashcard")
public class Flashcard extends Game {

	public Flashcard() {};
	
	public Flashcard(String userId, String question, String answer) {
		super(userId, question, answer);
	}
}

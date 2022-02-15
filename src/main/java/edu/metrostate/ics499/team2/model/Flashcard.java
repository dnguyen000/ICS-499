package edu.metrostate.ics499.team2.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flashcard")
public class Flashcard extends Game {
	
	public Flashcard(String question, String answer) {
		super(question, answer);
	}
}

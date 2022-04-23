package edu.metrostate.ics499.team2.model.game;

import edu.metrostate.ics499.team2.model.game.Game;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quiz")
public class Quiz extends Game {
	public Quiz() {
		super();
	}


	public Quiz(String question, String answer) {
		super(question, answer);
	}
	
	public Quiz(String question, String answer, String userId) {
		super(question, answer, userId);
	}
}
		
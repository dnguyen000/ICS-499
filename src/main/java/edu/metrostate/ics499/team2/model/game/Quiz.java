package edu.metrostate.ics499.team2.model.game;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quiz")
public class Quiz extends Game {
	private String quizType;

	public Quiz() {
		super();
	}

	public Quiz(String question, String answer) {
		super(question, answer);
	}

	public Quiz(String userId, String question, String answer) {
		super(userId, question, answer);
	}

	public Quiz(String userId, String question, String answer, String quizType) {
		super(userId, question, answer);
		this.quizType = quizType;
	}

	public String getQuizType() {
		return this.quizType;
	}

	public void setQuizType(String quizType) {
		this.quizType = quizType;
	}

}

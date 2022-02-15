package edu.metrostate.ics499.team2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
public abstract class Game {
	
	@Id
	private String gameId;
	
	private String question;
	private String answer;
	
	public Game() {
		super();
	}
	
	public Game(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	
	public String getGameId() {
		return this.gameId;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Boolean isEqual(String id) {
		return this.gameId.equals(id);
	}

}
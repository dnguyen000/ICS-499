package edu.metrostate.ics499.team2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
public abstract class Game {
	
	@Id
	private String gameId;
	
	private String question;
	private String answer;
	private String userId;
	
	public Game() {
		super();
	}
	
	public Game(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	
	public Game(String question, String answer, String userId) {
		this.question = question;
		this.answer = answer;
		this.userId = userId;
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
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Boolean isEqual(String id) {
		return this.gameId.equals(id);
	}

}
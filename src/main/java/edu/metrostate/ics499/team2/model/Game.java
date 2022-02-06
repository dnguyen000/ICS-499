package edu.metrostate.ics499.team2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="game")
public abstract class Game {
	
	@Id
	@GeneratedValue
	private Long gameId;
	
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
	
	public Long getGameId() {
		return this.gameId;
	}
	
	public void setGameId(Long gameId) {
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

}

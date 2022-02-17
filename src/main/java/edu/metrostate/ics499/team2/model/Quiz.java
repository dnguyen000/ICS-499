package edu.metrostate.ics499.team2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quiz")
public class Quiz {
	@Id
	private String quizId;
	private String createquestions;
	private int displayscore;
	private String options;
	private String correctAnswers;
	
	
	public Quiz() {
		super();
	}


	public Quiz(String createquestions, int displayscore, String options, String correctAnswers) {
		super();
		this.createquestions = createquestions;
		this.displayscore = displayscore;
		this.options = options;
		this.correctAnswers = correctAnswers;
	}


	public String getQuizId() {
		return quizId;
	}


	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}


	public String getCreatequestions() {
		return createquestions;
	}


	public void setCreatequestions(String createquestions) {
		this.createquestions = createquestions;
	}


	public int getDisplayscore() {
		return displayscore;
	}


	public void setDisplayscore(int displayscore) {
		this.displayscore = displayscore;
	}


	public String getOptions() {
		return options;
	}


	public void setOptions(String options) {
		this.options = options;
	}


	public String getCorrectAnswers() {
		return correctAnswers;
	}


	public void setCorrectAnswers(String correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
	
	
	

}

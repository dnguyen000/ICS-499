package edu.metrostate.ics499.team2.model;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quiz")
public class Quiz extends Game {
	private String quizType;
	private String userId;

	public Quiz() {
		super();
	}


	public Quiz(String question, String answer) {
		super(question, answer);
	}
	
	public Quiz(String question, String answer, String userId) {

		super(question, answer);
		this.userId = userId;
	}

	public Quiz(String question, String answer, String userId, String quizType) {
		super(question, answer);
		this.userId = userId;
		this.quizType = quizType;
	}

	public String getQuizType() {
		return this.quizType;
	}

	public void setQuizType(String quizType) {
		this.quizType = quizType;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

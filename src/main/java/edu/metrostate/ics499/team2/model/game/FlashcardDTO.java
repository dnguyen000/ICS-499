package edu.metrostate.ics499.team2.model.game;

public class FlashcardDTO {

	private String userId;
	private String question;
	private String answer;

	public FlashcardDTO() {}

	public FlashcardDTO(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public FlashcardDTO(String userId, String question, String answer) {
		this.userId = userId;
		this.question = question;
		this.answer = answer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
}
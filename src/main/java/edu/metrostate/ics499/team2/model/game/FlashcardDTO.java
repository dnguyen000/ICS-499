package edu.metrostate.ics499.team2.model.game;

public class FlashcardDTO {

	private String userId;
	private final String question;
	private final String answer;
	
	public FlashcardDTO(String userId, String question, String answer) {
		this.userId = userId;
		this.question = question;
		this.answer = answer;
	}
	public String getUserId() { return this.userId; }
	public String getAnswer() {
		return this.answer;
	}
	public String getQuestion() {
		return this.question;
	}
}
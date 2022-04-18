package edu.metrostate.ics499.team2.model;

public class FlashcardDTO {
	private final String question;
	private final String answer;
	
	public FlashcardDTO(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public String getQuestion() {
		return this.question;
	}
}
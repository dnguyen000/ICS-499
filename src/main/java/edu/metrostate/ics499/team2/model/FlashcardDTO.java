package edu.metrostate.ics499.team2.model;

public class FlashcardDTO {
	private String question;
	private String answer;
	
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
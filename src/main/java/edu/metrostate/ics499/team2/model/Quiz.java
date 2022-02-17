package edu.metrostate.ics499.team2.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quiz")
public class Quiz extends Game {
	
//	private Map<String, String> chemQuiz;
//	
//	public Quiz() {
//		super();
//	}


	public Quiz(String answer, String question ) {
		super(answer, question);
		//this.chemQuiz = chemQuiz;
		
	}

//	public Map<String, String> getChemQuiz() {
//		return chemQuiz;
//	}
//
//	public void setChemQuiz(Map<String, String> chemQuiz) {
//		this.chemQuiz = chemQuiz;
//	}
//	public void addQuestion(String answer, String question) {
//		chemQuiz.put(question, answer);
//		
//	}
	

}

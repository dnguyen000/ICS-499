package edu.metrostate.ics499.team2.services;

import edu.metrostate.ics499.team2.model.Quiz;

public interface QuizService {
	public Quiz getQuizById(String id);
	public Quiz deletQuizById(String id);
    public Quiz getQuestionById(String question);
	public Quiz getAnswerById(String answer);
	public Quiz createQuiz(Quiz quiz);
	


}

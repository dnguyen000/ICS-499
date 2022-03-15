package edu.metrostate.ics499.team2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import edu.metrostate.ics499.team2.model.Quiz;
import edu.metrostate.ics499.team2.repositories.QuizRepository;

@Service
public class QuizService implements ServiceInterface<Quiz>{
	@Autowired
	private QuizRepository quizRepo;
	
	public Quiz createQuiz(Quiz quiz) {
		Quiz q  = new Quiz(quiz.getAnswer(), quiz.getQuestion());
		return quizRepo.save(q);
		
	}
	public List<Quiz> list() {
	    return quizRepo.findAll();
	}

	public Quiz getQuizById(String id) {
		if(quizRepo.findById(id).isPresent())
			return quizRepo.findById(id).get();
		else
			throw new MongoException ("Quiz not found");		
		
	}

	public List<Quiz> queryQuestions(String question) {
		
		return quizRepo.findByQuestion(question);
	} 
	
	public List<Quiz> queryAnswers(String answer) {
		
		return quizRepo.findByAnswer(answer);
	}
	
	public Quiz deletQuizById(String id) {
		if(quizRepo.findById(id).isPresent()) {
			Quiz quiz = quizRepo.findById(id).get();
			 quizRepo.delete(quiz);
			return quiz;
		}
		else
			throw new MongoException ("Record can not found");
		
	}

	public Quiz getQuestionById(String question) {
		if(quizRepo.findById(question).isPresent())
			return quizRepo.findById(question).get();
		else
			throw new MongoException ("Record not found");
		
	}

	public Quiz getAnswerById(String answer) {
		if(quizRepo.findById(answer).isPresent())
			return quizRepo.findById(answer).get();
		else
			throw new MongoException ("Answer not found");
	}
	@Override
	public boolean isValid(Quiz obj) {
		List<Quiz> result = quizRepo.findAll();
				return result.stream()
				.filter(quiz -> quiz.getQuestion().equalsIgnoreCase(obj.getQuestion()))
				.filter(quiz -> quiz.getAnswer().equalsIgnoreCase(obj.getAnswer())).collect(Collectors.toList()).size() > 0 ? false : true;
	}
}

package edu.metrostate.ics499.team2.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import edu.metrostate.ics499.team2.model.Quiz;
import edu.metrostate.ics499.team2.repositories.QuizRepository;
<<<<<<< HEAD
import edu.metrostate.ics499.team2.service.QuizService;
=======
import edu.metrostate.ics499.team2.services.QuizService;
>>>>>>> main

@Service
public class QuizServiceImpl implements  QuizService{
	
	@Autowired
	private QuizRepository quizRepo;
	
	
	@Override
<<<<<<< HEAD
	public Quiz createQuize(Quiz quiz) {
=======
	public Quiz createQuiz(Quiz quiz) {
>>>>>>> main
		Quiz q  = new Quiz(quiz.getAnswer(), quiz.getQuestion());
		return quizRepo.save(q);
		
	}


	@Override
	public Quiz getQuizById(String id) {
		if(quizRepo.findById(id).isPresent())
			return quizRepo.findById(id).get();
		else
			throw new MongoException ("Quiz not found");		
		
	}

	@Override
	public Quiz deletQuizById(String id) {
		if(quizRepo.findById(id).isPresent()) {
			Quiz quiz = quizRepo.findById(id).get();
			 quizRepo.delete(quiz);
			return quiz;
		}
		else
			throw new MongoException ("Record can not found");
		
	}

	@Override
	public Quiz getQuestionById(String question) {
		if(quizRepo.findById(question).isPresent())
			return quizRepo.findById(question).get();
		else
			throw new MongoException ("Record not found");
		
	}

	@Override
	public Quiz getAnswerById(String answer) {
		if(quizRepo.findById(answer).isPresent())
			return quizRepo.findById(answer).get();
		else
			throw new MongoException ("Answer not found");
	}

	

	

}

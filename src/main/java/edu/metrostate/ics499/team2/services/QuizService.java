package edu.metrostate.ics499.team2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.model.Quiz;
import edu.metrostate.ics499.team2.repositories.QuizRepository;

@Service
public class QuizService implements ServiceInterface<Quiz>{
	@Autowired
	private QuizRepository quizRepo;
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public Quiz createQuiz(Quiz quiz) {
		Quiz q = null;
		
		try {
			if (isValid(quiz)) {
				LOG.info("Inserting new quiz into DB");
				
				q = quizRepo.save(quiz);
			} else {
				throw new MongoException("Invalid entry into database");
			}
		} catch (MongoException mongoException) {
			LOG.error("Invalid entry into database");
		}
		
		return q;	
	}
	
	public void createQuizes(List<Quiz> quizList) {
		quizList.forEach(quiz -> {
			quizRepo.save(quiz);
		});
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
	
	public boolean createCompoundQuizes(Compound compound) {
		List<Quiz> quizList = new ArrayList<>();
		String q1 = "What is the name of this compound: " + compound.getFormula() + "?";
		String a1 = compound.getTitle();
		String q2 = "What is the formula for " + compound.getTitle() + "?";
		String a2 = compound.getFormula();
		Quiz quiz1 = new Quiz(q1, a1);
		Quiz quiz2 = new Quiz(q2, a2);
		
		quizList.add(quiz1);
		quizList.add(quiz2);
		
		createQuizes(quizList);
		
		return true;
	}
	
	public void createElementQuizes(Element element) {
		List<Quiz> quizList = new ArrayList<>();
		String q1 = "What is the name of the element " + element.getSymbol() + "?";
		String a1 = element.getName();
		String q2 = "What is the symbol for " + element.getName() + "?";
		String a2 = element.getSymbol();
		Quiz quiz1 = new Quiz(q1, a1);
		Quiz quiz2 = new Quiz(q2, a2);
		
		quizList.add(quiz1);
		quizList.add(quiz2);
		
		createQuizes(quizList);
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

package edu.metrostate.ics499.team2.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.Quiz;
import edu.metrostate.ics499.team2.repositories.QuizRepository;



@RestController
@RequestMapping("/quize")
public class QuizController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private QuizRepository quizRepo;
	
	@GetMapping(value = "/all")
	public List<Quiz> list() {
		LOG.info("Return all Quiz");
	    return quizRepo.findAll();
	}
	@GetMapping(value = "{gameId}")
	public List<Quiz> getQuizById(String id) {
		LOG.info("Must return all quiz by id");
		return this.quizRepo.findByGameId(id);
	}
	@GetMapping(value ="/questinons")
	public List<Quiz> queryQuestion(String question){
		LOG.info("Get all quiz that match with the question");
		return quizRepo.findByQuestion(question);
	}
	
	@GetMapping(value ="/answer")
	public List<Quiz> queryAnswer(String answer){
		LOG.info("Get all quiz answer");
		return quizRepo.findByAnswer(answer);
	}
	
	@PostMapping("/add")
	public Quiz create(@RequestBody final Quiz quiz) {
		return quizRepo.save(quiz);
		
	}
}

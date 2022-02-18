package edu.metrostate.ics499.team2.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;

@Service
public class FlashcardService implements ServiceInterface<Flashcard> {
	
	@Autowired
	private FlashcardRepository flashcardRepo;
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public List<Flashcard> list() {
		LOG.info("Getting all flashcards.");
		return flashcardRepo.findAll();
	}
	
	public Flashcard create(final Flashcard flashcard) {
		return isValid(flashcard) ? flashcardRepo.save(flashcard) : null;
	}
	
	public Flashcard getFlashcardById(String id) {
		return flashcardRepo.findByGameId(id);
	}
	
	public List<Flashcard> queryQuestions(String question) {
		return flashcardRepo.findByQuestion(question);
	}
	
	public List<Flashcard> queryAnswers(String answer) {
		return flashcardRepo.findByAnswer(answer);
	}

	@Override
	public boolean isValid(Flashcard obj) {
		List<Flashcard> result = flashcardRepo.findAll();
		return result.stream()
				.filter(fc -> fc.getQuestion().equalsIgnoreCase(obj.getQuestion()))
				.filter(fc -> fc.getAnswer().equalsIgnoreCase(((Flashcard) obj).getAnswer())).toList().size() > 0 ? false : true;
	}
}

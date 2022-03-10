package edu.metrostate.ics499.team2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.repositories.FlashcardRepository;

@Service
public class FlashcardService implements ServiceInterface<Flashcard> {
	
	@Autowired
	private FlashcardRepository flashcardRepo;
	
	@Autowired
	private ElementService elementService;
	
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
	
	public int generatePeriodicTableQuestions() {
		if (elementService.showAllElements().size() == 0) {
			elementService.createPeriodicElements();
		}
		
		List<Element> periodicTable = elementService.showAllElements();
		List<Flashcard> flashcards = new ArrayList<>();
		
		for (Element element : periodicTable) {
			final String nameQuestion = "What is the name of this symbol: " + element.getSymbol() + "?";
			final String symbolQuestion = "What is the symbol for " + element.getName() + "?";
			final String familyQuestion = "What family does the element " + element.getName() + " belong to?";
			
			Flashcard nameFlashcard = new Flashcard(symbolQuestion, element.getSymbol());
			Flashcard symbolFlashcard = new Flashcard(nameQuestion, element.getName());
			Flashcard familyFlashcard = new Flashcard(familyQuestion, element.getFamily());
			
			flashcards.add(nameFlashcard);
			flashcards.add(symbolFlashcard);
			flashcards.add(familyFlashcard);
		}
		
		for (Flashcard flashcard : flashcards) {
			create(flashcard);
		}
		
		return flashcardRepo.findAll().size();
	}

	@Override
	public boolean isValid(Flashcard obj) {
		List<Flashcard> result = flashcardRepo.findAll();
		return result.stream()
				.filter(fc -> fc.getQuestion().equalsIgnoreCase(obj.getQuestion()))
				.filter(fc -> fc.getAnswer().equalsIgnoreCase(obj.getAnswer())).collect(Collectors.toList()).size() > 0 ? false : true;
	}
}

package edu.metrostate.ics499.team2.controllers;

import edu.metrostate.ics499.team2.model.game.Flashcard;
import edu.metrostate.ics499.team2.model.game.FlashcardDTO;
import edu.metrostate.ics499.team2.services.FlashcardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/flashcards")
public class FlashcardController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private FlashcardService flashcardService;

    @GetMapping(value = "/all")
    @ResponseBody
    public List<Flashcard> list() {
        LOG.info("Getting all flashcards.");
        return flashcardService.list();
    }

    @GetMapping(value = "/{gameId}")
    public Flashcard getFlashcardById(String id) {
        LOG.info("Return flashcard by id.");
        return this.flashcardService.getFlashcardById(id);
    }

    @GetMapping(value = "/userflashcards/{userId}")
    public ResponseEntity<List<Flashcard>> getFlashcardByUserId(@PathVariable("userId") String userId) {
        LOG.info("Returning flashcards by userId: {} in controller.", userId);
        List<Flashcard> flashcards = flashcardService.listUserFlashcards(userId);
        return new ResponseEntity<>(flashcards, OK);
    }

    @GetMapping(value = "/questions")
    public List<Flashcard> queryQuestions(String question) {
        LOG.info("Getting all flashcards that match the question");
        return flashcardService.queryByQuestion(question);
    }

    @GetMapping(value = "/answers")
    public List<Flashcard> queryAnswers(String answer) {
        LOG.info("Getting all flashcards that match the answer");
        return flashcardService.queryByAnswer(answer);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> create(@RequestBody final FlashcardDTO flashcardDto) {
        Flashcard flashcard = new Flashcard(flashcardDto.getUserId(), flashcardDto.getQuestion(), flashcardDto.getAnswer());
        return flashcardService.create(flashcard) != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
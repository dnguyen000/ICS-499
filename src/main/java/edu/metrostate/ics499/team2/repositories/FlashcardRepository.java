package edu.metrostate.ics499.team2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.metrostate.ics499.team2.model.Flashcard;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

}

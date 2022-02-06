package edu.metrostate.ics499.team2.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="flashcard")
public class Flashcard extends Game {
	
	@Id
	@GeneratedValue
	private Long flashcardId;
	
	private String question;
	private String answer;
}

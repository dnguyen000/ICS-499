package edu.metrostate.ics499.team2.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "games")
public abstract class Games {

	
	@Id
	private Long GamesID;
	
	private String questions;
	private String answers;
	private String matchingSymbol;
	private String elementName;
	private String wordSearch;
	public Games(String matchingSymbol, String elementName, String wordSearch, String question, String answers) {
		
		this.matchingSymbol = matchingSymbol;
		this.elementName = elementName;
		this.wordSearch = wordSearch;
		
		this.answers = answers;
		this.questions = questions;
	}
	public Games() {
		super();
	}
	public Long getGameID() {
		return GamesID;
	}
	public void setGameID(Long gameID) {
		GamesID = gameID;
	}
	public String getMatchingSymbol() {
		return matchingSymbol;
	}
	public void setMatchingSymbol(String matchingSymbol) {
		this.matchingSymbol = matchingSymbol;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getWordSearch() {
		return wordSearch;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	public void setWordSearch(String wordSearch) {
		this.wordSearch = wordSearch;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int output = 1;
		output = prime * output + ((answers == null) ? 0 : answers.hashCode());
		output = prime * output + ((questions == null) ? 0 : questions.hashCode());
		output = prime * output + ((wordSearch == null) ? 0 : wordSearch.hashCode());
		output = prime * output + ((elementName == null) ? 0 : elementName.hashCode());
		output = prime * output + ((matchingSymbol == null) ? 0 : matchingSymbol.hashCode());
		return output;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		
		Games other = (Games) obj;
		if (matchingSymbol == null) {
			if (other.matchingSymbol != null)
				return false;
		} else if (!matchingSymbol.equals(other.matchingSymbol))
			return false;
		if ( elementName== null) {
			if (other.elementName != null)
				return false;
		} else if (!elementName.equals(other.elementName))
			return false;
		if (wordSearch == null) {
			if (other.wordSearch != null)
				return false;
		} else if (!wordSearch.equals(other.wordSearch))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Game, GameID=" + GamesID + ", matchingSymbol=" + matchingSymbol + ", elementName=" + elementName
				+ ", wordSearch=" + wordSearch + "";
	}
	
	
	
	

}



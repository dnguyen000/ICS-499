package edu.metrostate.ics499.team2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "registered user")
public class RegisteredUser {
	
	@Id
	private String userId;
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int highScore;
	
	public RegisteredUser(String firstName, String lastName, String email, String password, int highScore) {
		this.firstName= firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.highScore = highScore;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}

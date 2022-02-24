package edu.metrostate.ics499.team2.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class RegisteredUser {
	
	@Id
	private String email;
	
	private String firstName;
	private String lastName;
	private String password;
	private int highScore;
	
	// default constructor
	public RegisteredUser() { }
	
	public RegisteredUser(String firstName, String lastName, String email, String password, int highScore) {
		this.firstName= firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.highScore = highScore;
	}

	public RegisteredUser(RegisteredUser createUser) {
		this.password = createUser.getPassword();
		this.email = createUser.getEmail();
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
	
	public String getName() {
		return getFirstName() + " " + getLastName();
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		RegisteredUser other = (RegisteredUser) obj;
//		return Objects.equals(username, other.username);
//	}
}

package edu.metrostate.ics499.team2.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class RegisteredUser implements Serializable {
	
	@Id
	private String id;
	private String userId;
	private String email;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String profileImgUrl;
	private Date lastLoginDate;
	private Date lastLoginDisplay;
	private Date joinDate;
	//spring properties
	private String role; 					// ROLE_USER{ read, edit }, ROLE_ADMIN{ delete , update, create }
	private String[] authorities;
	private boolean isActive;
	private boolean isNotLocked;

	private int highScore;
	
	// default empty constructor
	public RegisteredUser() { }

	public RegisteredUser(String id, String userId, String email, String username, String firstName, String lastName, String password, String profileImgUrl, Date lastLoginDate, Date lastLoginDisplay, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked, int highScore) {
		this.id = id;
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.profileImgUrl = profileImgUrl;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginDisplay = lastLoginDisplay;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
		this.highScore = highScore;
	}

	public RegisteredUser(UserLoginDTO userDTO) {
		this.password = userDTO.getPassword();
		this.email = userDTO.getEmail();
	}

	public RegisteredUser(String username) {
		this.username = username;
	}

	public RegisteredUser(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public Date getLastLoginDisplay() {
		return lastLoginDisplay;
	}

	public void setLastLoginDisplay(Date lastLoginDisplay) {
		this.lastLoginDisplay = lastLoginDisplay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isNotLocked() {
		return isNotLocked;
	}

	public void setNotLocked(boolean notLocked) {
		isNotLocked = notLocked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

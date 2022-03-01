package edu.metrostate.ics499.team2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class RegisteredUser implements Serializable {
	
	@Id
	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String profileImgUrl;
	private Date lastLoginDate;
	private Date lastLoginDisplay;
	private Date joinDate;
	//spring properties
	private List<Role> roles; 					// ROLE_USER{ read, edit }, ROLE_ADMIN{ delete , update, create }
	private String[] authorities;
	private boolean isActive;
	private boolean isNotLocked;

	private int highScore;
	
	// default constructor
	public RegisteredUser() { }

	public RegisteredUser(String email, String firstName, String lastName, String password, String profileImgUrl, Date lastLoginDate, Date joinDate, List<Role> roles, String[] authorities, boolean isActive, int highScore) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.profileImgUrl = profileImgUrl;
		this.lastLoginDate = lastLoginDate;
		this.joinDate = joinDate;
		this.roles = roles;
		this.authorities = authorities;
		this.isActive = isActive;
		this.highScore = highScore;
	}

	public RegisteredUser(UserLoginDTO userDTO) {
		this.password = userDTO.getPassword();
		this.email = userDTO.getEmail();
	}

	public Date getLastLoginDisplay() {
		return lastLoginDisplay;
	}

	public void setLastLoginDisplay(Date lastLoginDisplay) {
		this.lastLoginDisplay = lastLoginDisplay;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

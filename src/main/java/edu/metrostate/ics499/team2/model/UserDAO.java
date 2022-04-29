package edu.metrostate.ics499.team2.model;

import java.util.List;

public class UserDAO {
	
    private String username;
    private String role;
	
	public UserDAO(String username, String role) {
		this.username = username;
		this.role = role;
	}
	
	public UserDAO(String username) {
		this.username = username;
	}
	
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

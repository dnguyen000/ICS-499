package edu.metrostate.ics499.team2.model;

import java.util.List;

public class UserDAO {
	
    private String username;
    private List<String> roles;
	
	public UserDAO(String username, List<String> roles) {
		this.username = username;
		this.roles = roles;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}

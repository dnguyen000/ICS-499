package edu.metrostate.ics499.team2.model;

import java.util.List;

public class UserCreationDTO {
	
    private String email;
	private String password;
	private List<String> roles;
	
	public UserCreationDTO() {};
	
	public UserCreationDTO(String email, String password, List<String> roles) {
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
    public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}

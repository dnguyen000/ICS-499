package edu.metrostate.ics499.team2.model;

public class UserCreationDTO {
	
    private String email;  
	private String password;
	
	public UserCreationDTO(String email, String password) {
		this.email = email;
		this.password = password;
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

}

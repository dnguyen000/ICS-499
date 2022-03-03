package edu.metrostate.ics499.team2.model;

public class UserLoginDTO {
	
    private String email;
	private String password;
	private String role;
	
	public UserLoginDTO() {};
	
	public UserLoginDTO(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
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

	public String getRoles() {
		return role;
	}

	public void setRoles(String roles) {
		this.role = role;
	}

}

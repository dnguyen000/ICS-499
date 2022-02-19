package edu.metrostate.ics499.team2.appRole.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection ="users")
public class Users {
	@Id
	private String id;
	
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups =true)
	private String userName;
	private String email;
	private String password;
	private boolean enabled;
	
	@DBRef
	private Set<UsersAppRole> roles = new HashSet<>();
	
	public Users() {
		
	}

	public Users(String userName, String email, String password, boolean enabled) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
	return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public void setPasword(String password) {
		this.password = password;
	}
	public Set<UsersAppRole> getRoles(){
		return roles;
	}
	public void setUsersAppRole(Set<UsersAppRole> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
		

}

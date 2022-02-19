package edu.metrostate.ics499.team2.appRole.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class UsersAppRole {
	@Id
	private long id;
	
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups =true)
	private String fName;
	private String lName;
	private String email;
	private String password;
	private UsersAppRole usersAppRole;
	private boolean isLocked = false;
	private boolean isEnabled = false;
	
	public UsersAppRole(String fName, String lName, String email, String password, UsersAppRole usersAppRole,
			boolean isLocked, boolean isEnabled) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.usersAppRole = usersAppRole;
		this.isLocked = isLocked;
		this.isEnabled = isEnabled;
	}
	
     public String getPassword() {
	        return password;
	    }

	    public String getUsername() {
	        return email;
	    }

	    public String getFName() {
	        return fName;
	    }

	    public String getLName() {
	        return getLName();
	    }

	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    public boolean isAccountNonLocked() {
	        return !isLocked;
	    }

	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    public boolean isEnabled() {
	        return isEnabled;
	    }
	    
		
	}
	
	
	




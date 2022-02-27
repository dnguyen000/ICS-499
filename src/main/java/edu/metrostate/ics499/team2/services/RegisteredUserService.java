package edu.metrostate.ics499.team2.services;

import java.util.List;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.Role;

public interface RegisteredUserService {	
	RegisteredUser saveUser(RegisteredUser user);
	Role saveRole(Role role);
	void addRoleToUser(String email, String name);
	RegisteredUser getUser(String email);
	List<RegisteredUser>getUsers();
}

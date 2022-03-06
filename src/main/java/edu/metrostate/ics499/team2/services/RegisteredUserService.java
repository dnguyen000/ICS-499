package edu.metrostate.ics499.team2.services;

import java.util.List;

import edu.metrostate.ics499.team2.exceptions.domain.EmailExistException;
import edu.metrostate.ics499.team2.exceptions.domain.UserNotFoundException;
import edu.metrostate.ics499.team2.exceptions.domain.UsernameExistException;
import edu.metrostate.ics499.team2.model.RegisteredUser;
import org.springframework.web.multipart.MultipartFile;

public interface RegisteredUserService {	
	RegisteredUser register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException;
//	Role saveRole(Role role);
//	void addRoleToUser(String email, String name);
//	RegisteredUser getUser(String email);
	List<RegisteredUser>getUsers();
	RegisteredUser findUserByUsername(String username);
	RegisteredUser addNewUser(String firstName, String lastName, String username, String email, String role,
							  boolean isNonLocked, boolean isActive, MultipartFile profileImg);
	RegisteredUser updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role,
							boolean isNonLocked, boolean isActive, MultipartFile profileImg);
	void deleteUser(String id);
	void resetPassword(String email);
	RegisteredUser updateProfileImage(String username, MultipartFile profileImg);
}

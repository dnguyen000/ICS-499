package edu.metrostate.ics499.team2.services;

import java.io.IOException;
import java.util.List;

import edu.metrostate.ics499.team2.exceptions.domain.*;
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
							  boolean isNonLocked, boolean isActive, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException;
	RegisteredUser updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role,
							boolean isNonLocked, boolean isActive, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException;
	void deleteUser(String id) throws IOException;
	void resetPassword(String email) throws EmailNotFoundException;
	RegisteredUser updateProfileImage(String username, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException;
}

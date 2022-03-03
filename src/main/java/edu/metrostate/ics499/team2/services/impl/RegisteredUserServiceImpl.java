package edu.metrostate.ics499.team2.services.impl;

import java.util.Date;
import java.util.List;

import edu.metrostate.ics499.team2.exceptions.domain.EmailExistException;
import edu.metrostate.ics499.team2.exceptions.domain.UserNotFoundException;
import edu.metrostate.ics499.team2.exceptions.domain.UsernameExistException;
import edu.metrostate.ics499.team2.services.RegisteredUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;
import edu.metrostate.ics499.team2.security.RegisteredUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static edu.metrostate.ics499.team2.security.constants.Role.ROLE_USER;
import static edu.metrostate.ics499.team2.security.constants.UserImplementationConstant.*;

@Service @Slf4j
public class RegisteredUserServiceImpl implements RegisteredUserService, UserDetailsService {
	private final RegisteredUserRepository userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public RegisteredUserServiceImpl(RegisteredUserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public RegisteredUser register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);
		RegisteredUser user = new RegisteredUser();
		user.setUserId(generateUserId());
		String password = generatePassword();
		String encodedPassword = encodePassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setEmail(email);
		user.setJoinDate(new Date());
		user.setPassword(encodedPassword);
		user.setActive(true);
		user.setNotLocked(true);
		user.setRole(ROLE_USER.name());
		user.setAuthorities(ROLE_USER.getAuthorities());
		user.setProfileImgUrl(getTemporaryProfileImageUrl());
		userRepo.save(user);
		log.info("New user password: " + password);
		return user;
	}

	private RegisteredUser validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
		RegisteredUser userByNewUsername = findUserByUsername(newUsername);
		RegisteredUser userByNewEmail = findUserByEmail(newEmail);
		if(StringUtils.isNotBlank(currentUsername)) {
			RegisteredUser currentUser = findUserByUsername(currentUsername);
			if(currentUser == null) {
				throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
			}
			if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
				throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
			}
			if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return currentUser;
		} else {
			if(userByNewUsername != null) {
				throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
			}
			if(userByNewEmail != null) {
				throw new EmailExistException(EMAIL_ALREADY_EXISTS);
			}
			return null;
		}
	}

	public RegisteredUser findUserByEmail(String newEmail) {
		return userRepo.findRegisteredUserByEmail(newEmail);
	}

	public RegisteredUser findUserByUsername(String newUsername) {
		return userRepo.findRegisteredUserByUsername(newUsername);
	}

	public List<RegisteredUser> getUsers() {
		log.info("fetching all users");
		return userRepo.findAll();
	}

//	@Override
//	public void addRoleToUser(String email, String roleName) {
//		log.info("adding role: {}, to user: {}", roleName, email);
//		RegisteredUser user = userRepo.findByEmail(email);
//		Role role = roleRepo.findByName(roleName);
//		user.getRole().add(role);
//		userRepo.save(user);
//	}

//	@Override
//	public Role saveRole(Role role) {
//		log.info("saving new role: {}, to the database", role.getName());
//		return roleRepo.save(role);
//	}

//	@Override
//	public RegisteredUser getUser(String email) {
//		log.info("fetching user {}", email);
//		return this.userRepo.findByEmail(email);
//	}
	
//	@Override
//	public boolean isValid(RegisteredUser obj) {
//		return getUsers().stream()
//				.filter(user -> user.equals(obj)).collect(Collectors.toList()).size() > 0 ? false : true;
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisteredUser user = userRepo.findRegisteredUserByUsername(username);
        if (user == null) {
        	log.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
			user.setLastLoginDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepo.save(user);
        	log.info("user: {} found in the database", username);
        }
        return new RegisteredUserPrincipal(user);
	}

	private String encodePassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

	private String getTemporaryProfileImageUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH).toUriString();
	}

	private String generateUserId() {
		// return random number length 10
		return RandomStringUtils.randomNumeric(10);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

}

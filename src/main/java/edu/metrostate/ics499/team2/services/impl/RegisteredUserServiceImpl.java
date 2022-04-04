package edu.metrostate.ics499.team2.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import edu.metrostate.ics499.team2.constants.Role;
import edu.metrostate.ics499.team2.exceptions.domain.EmailExistException;
import edu.metrostate.ics499.team2.exceptions.domain.EmailNotFoundException;
import edu.metrostate.ics499.team2.exceptions.domain.UserNotFoundException;
import edu.metrostate.ics499.team2.exceptions.domain.UsernameExistException;
import edu.metrostate.ics499.team2.services.EmailService;
import edu.metrostate.ics499.team2.services.LoginAttemptService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static edu.metrostate.ics499.team2.constants.FileConstants.*;
import static edu.metrostate.ics499.team2.constants.Role.ROLE_USER;
import static edu.metrostate.ics499.team2.constants.UserImplementationConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service @Slf4j
public class RegisteredUserServiceImpl implements RegisteredUserService, UserDetailsService {

	private final RegisteredUserRepository userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final LoginAttemptService loginAttemptService;
	private final EmailService emailService;
	
	@Autowired
	public RegisteredUserServiceImpl(RegisteredUserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
									 LoginAttemptService loginAttemptService, EmailService emailService) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.loginAttemptService = loginAttemptService;
		this.emailService = emailService;
	}

	@Override
	public RegisteredUser register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException {
		validateNewUsernameAndEmail(EMPTY, username, email);
		RegisteredUser user = new RegisteredUser();
		user.setUserId(generateUserId());
		String password = generatePassword();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setEmail(email);
		user.setJoinDate(new Date());
		user.setPassword(encodePassword(password));
		user.setActive(true);
		user.setNotLocked(true);
		user.setRole(ROLE_USER.name());
		user.setAuthorities(ROLE_USER.getAuthorities());
		user.setProfileImgUrl(getTemporaryProfileImageUrl(username));
		userRepo.save(user);
		log.info("New user password: " + password);
		emailService.sendNewPasswordEmail(firstName, password, email);
		return user;
	}

	private RegisteredUser validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
			RegisteredUser userByNewUsername = findUserByUsername(newUsername);
			RegisteredUser userByNewEmail = findUserByEmail(newEmail);
			if (StringUtils.isNotBlank(currentUsername)) {
				RegisteredUser currentUser = findUserByUsername(currentUsername);
				if (currentUser == null) {
					throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
				}
				if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
					throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
				}
				if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
					throw new EmailExistException(EMAIL_ALREADY_EXISTS);
				}
				return currentUser;
			} else {
				if (userByNewUsername != null) {
					throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
				}
				if (userByNewEmail != null) {
					throw new EmailExistException(EMAIL_ALREADY_EXISTS);
				}
			}
		return null;
	}

	public RegisteredUser findUserByEmail(String email) {
		return userRepo.findRegisteredUserByEmail(email);
	}

	public RegisteredUser findUserByUsername(String username) {
		return userRepo.findRegisteredUserByUsername(username);
	}

	@Override
	public RegisteredUser addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException {
		validateNewUsernameAndEmail(EMPTY, username, email);
		RegisteredUser user = new RegisteredUser();
		String password = generatePassword();
		user.setUserId(generateUserId());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setJoinDate(new Date());
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(encodePassword(password));
		user.setActive(isActive);
		user.setNotLocked(isNonLocked);
		user.setRole(getRoleEnumName(role).name());
		user.setAuthorities(getRoleEnumName(role).getAuthorities());
		user.setProfileImgUrl(getTemporaryProfileImageUrl(username));
		userRepo.save(user);
		saveProfileImg(user, profileImg);
		log.info("New user password: " + password);
//		emailService.sendNewPasswordEmail(firstName, password, email);
		return user;
	}

	@Override
	public RegisteredUser updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException {
		RegisteredUser user = validateNewUsernameAndEmail(currentUsername, newUsername, newEmail);
		user.setFirstName(newFirstName);
		user.setLastName(newLastName);
		user.setUsername(newUsername);
		user.setEmail(newEmail);
		user.setActive(isActive);
		user.setNotLocked(isNonLocked);
		user.setRole(getRoleEnumName(role).name());
		user.setAuthorities(getRoleEnumName(role).getAuthorities());
		userRepo.save(user);
		saveProfileImg(user, profileImg);
		return user;
	}

	@Override
	public void deleteUser(String id) {
		userRepo.deleteById(id);
	}

	@Override
	public void resetPassword(String email) {
		try {
			RegisteredUser user = userRepo.findRegisteredUserByEmail(email);
			if(user == null) {
				throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
			}
			String password = generatePassword();
			user.setPassword(encodePassword(password));
			userRepo.save(user);
			log.info("New user password: " + password);
			emailService.sendNewPasswordEmail(user.getFirstName(), password, user.getEmail());
		} catch (EmailNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RegisteredUser updateProfileImage(String username, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException {
		RegisteredUser user = validateNewUsernameAndEmail(username, null, null);
		saveProfileImg(user, profileImg);
		return user;
	}

	public List<RegisteredUser> getUsers() {
		log.info("fetching all users");
		return userRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisteredUser user = userRepo.findRegisteredUserByUsername(username);
        if (user == null) {
        	log.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
			validateLoginAttempt(user);
			user.setLastLoginDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepo.save(user);
        	log.info("user: {} found in the database", username);
        }
        return new RegisteredUserPrincipal(user);
	}

	// update 500 instead of user not found ?
	private void validateLoginAttempt(RegisteredUser user) {
		if(user.isNotLocked()) {
			if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
				user.setNotLocked(false);
			} else {
				user.setNotLocked(true);
			}
		} else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
		}
	}

	private String encodePassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

	private String getTemporaryProfileImageUrl(String username) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
	}

	private String generateUserId() {
		// return random number length 10
		return RandomStringUtils.randomNumeric(10);
	}

	private String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private Role getRoleEnumName(String role) {
		return Role.valueOf(role.toUpperCase());
	}

	private void saveProfileImg(RegisteredUser user, MultipartFile profileImg) {
		try {
			if (profileImg != null) {
				Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
				if (!Files.exists(userFolder)) {
					Files.createDirectories(userFolder);
					log.info(DIRECTORY_CREATED + userFolder);
				}
				Files.deleteIfExists(Paths.get(userFolder + user.getUsername() + DOT + JPG_EXTENSION));
				Files.copy(profileImg.getInputStream(), userFolder.resolve(user.getUsername() + DOT + JPG_EXTENSION),
						REPLACE_EXISTING);
				user.setProfileImgUrl(setProfileImgURL(user.getUsername()));
				userRepo.save(user);
				log.info(FILE_SAVED_IN_FILE_SYSTEM + profileImg.getOriginalFilename());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String setProfileImgURL(String username) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username + FORWARD_SLASH
				+ username + DOT + JPG_EXTENSION).toUriString();
	}

}

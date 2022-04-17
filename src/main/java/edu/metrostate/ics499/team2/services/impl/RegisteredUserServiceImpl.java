package edu.metrostate.ics499.team2.services.impl;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import edu.metrostate.ics499.team2.constants.Role;
import edu.metrostate.ics499.team2.exceptions.domain.*;
import edu.metrostate.ics499.team2.services.EmailService;
import edu.metrostate.ics499.team2.services.LoginAttemptService;
import edu.metrostate.ics499.team2.services.RegisteredUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import static edu.metrostate.ics499.team2.constants.Role.USER;
import static edu.metrostate.ics499.team2.constants.UserImplementationConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.http.MediaType.*;

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
		user.setRole(USER.name());
		user.setAuthorities(USER.getAuthorities());
		user.setProfileImgUrl(getTemporaryProfileImageUrl(username));
		userRepo.save(user);
//		log.info("New user password: " + password);
		emailService.sendNewPasswordEmail(firstName, password, email);
		return user;
	}

	public RegisteredUser findUserByEmail(String email) {
		return userRepo.findRegisteredUserByEmail(email);
	}

	public RegisteredUser findUserByUsername(String username) {
		return userRepo.findRegisteredUserByUsername(username);
	}

	@Override
	public RegisteredUser addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException {
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
//		log.info("New user password: " + password);
		emailService.sendNewPasswordEmail(firstName, password, email);
		return user;
	}

	@Override
	public RegisteredUser updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException {
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
	public RegisteredUser editUser(String userId, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException {
		RegisteredUser user = validateEditUsernameAndEmail(userId, newUsername, newEmail);
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

	public void saveLastLogin(Date date, String username) {
		RegisteredUser user = userRepo.findRegisteredUserByUsername(username);
		user.setLastLoginDate(new Date());
		userRepo.save(user);
	}

	@Override
	public void deleteUser(String username) throws IOException {
		RegisteredUser user = userRepo.findRegisteredUserByUsername(username);
		Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
		FileUtils.deleteDirectory(new File(userFolder.toString()));
		userRepo.deleteById(user.getId());
	}

	@Override
	public void resetPassword(String email) throws EmailNotFoundException {
		RegisteredUser user = userRepo.findRegisteredUserByEmail(email);
		if(user == null) {
			throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
		}
		String password = generatePassword();
		user.setPassword(encodePassword(password));
		userRepo.save(user);
//		log.info("New user password: " + password);
		emailService.sendNewPasswordEmail(user.getFirstName(), password, user.getEmail());
	}

	@Override
	public RegisteredUser updateProfileImage(String username, MultipartFile profileImg) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException {
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
        	log.info("user: {} found in the database", username);
        }
        return new RegisteredUserPrincipal(user);
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

	private RegisteredUser validateEditUsernameAndEmail(String userId, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
		RegisteredUser userByNewUsername = findUserByUsername(newUsername);
		RegisteredUser userByNewEmail = findUserByEmail(newEmail);
		if (StringUtils.isNotBlank(userId)) {
			RegisteredUser currentUser = findUserByUserId(userId);
			log.info(currentUser.getUserId());
			if (currentUser == null) {
				throw new UserNotFoundException("No user found by id: " + userId);
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

	private RegisteredUser findUserByUserId(String userId) {
		return userRepo.findRegisteredUserByUserId(userId);
	}

	// update 500 instead of user not found ?
	private void validateLoginAttempt(RegisteredUser user) {
		if (user.isNotLocked()) {
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

	private void saveProfileImg(RegisteredUser user, MultipartFile profileImg) throws IOException, NotAnImageFileException {
		if (profileImg != null) {
			if(!Arrays.asList(IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE, IMAGE_GIF_VALUE).contains(profileImg.getContentType())) {
				throw new NotAnImageFileException(profileImg.getOriginalFilename() + " is not an image file. Please upload an image.");
			}
			Path userFolder = Paths.get(USER_FOLDER + user.getUsername()).toAbsolutePath().normalize();
			if (!Files.exists(userFolder)) {
				Files.createDirectories(userFolder);
				log.info(DIRECTORY_CREATED + userFolder);
			}
			// calculate file hash
			String md5Hash = createMD5HashImg(profileImg);
			String filename = md5Hash + "_" + user.getUsername();
//			Files.deleteIfExists(Paths.get(userFolder + filename + DOT + JPG_EXTENSION));
			log.info("image hash: " + md5Hash);
			Files.copy(profileImg.getInputStream(), userFolder.resolve(filename + DOT + JPG_EXTENSION), REPLACE_EXISTING);
			user.setProfileImgUrl(setProfileImgURL(user.getUsername(), md5Hash));
			userRepo.save(user);
			log.info(FILE_SAVED_IN_FILE_SYSTEM + profileImg.getOriginalFilename());
		}
	}

	private String setProfileImgURL(String username, String hash) {
		log.info("image hash: " + hash);
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username + FORWARD_SLASH
				+ hash + "_" + username + DOT + JPG_EXTENSION).toUriString();
	}

	private String createMD5HashImg(final MultipartFile input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Compute message digest of the input
			byte[] messageDigest = md.digest(input.getBytes());
			return convertToHex(messageDigest);
		} catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String convertToHex(final byte[] messageDigest) {
		BigInteger bigint = new BigInteger(1, messageDigest);
		String hexText = bigint.toString(16);
		while (hexText.length() < 32) {
			hexText = "0".concat(hexText);
		}
		return hexText;
	}

}

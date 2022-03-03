package edu.metrostate.ics499.team2.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import edu.metrostate.ics499.team2.exceptions.domain.EmailExistException;
import edu.metrostate.ics499.team2.exceptions.domain.ExceptionHandling;
import edu.metrostate.ics499.team2.exceptions.domain.UserNotFoundException;
import edu.metrostate.ics499.team2.exceptions.domain.UsernameExistException;
import edu.metrostate.ics499.team2.security.JwtTokenProvider;
import edu.metrostate.ics499.team2.security.RegisteredUserPrincipal;
import edu.metrostate.ics499.team2.security.http.HttpResponse;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import edu.metrostate.ics499.team2.model.Mapper;
import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.UserDAO;
import edu.metrostate.ics499.team2.services.RegisteredUserService;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static edu.metrostate.ics499.team2.constants.FileConstants.*;
import static edu.metrostate.ics499.team2.constants.SecurityConstants.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping(path = {"/", "/user"})
public class RegisteredUserController extends ExceptionHandling {

	public static final String EMAIL_SENT = "Email with new password sent to: ";
	public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	private final RegisteredUserService userService;
	private final Mapper mapper;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	public RegisteredUserController(RegisteredUserService userService, Mapper mapper, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.userService = userService;
		this.mapper = mapper;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping("/login")
	public ResponseEntity<RegisteredUser> login(@RequestBody RegisteredUser user, HttpServletRequest req) {
		authenticate(user.getUsername(), user.getPassword());
		RegisteredUser loginUser = userService.findUserByUsername(user.getUsername());
		RegisteredUserPrincipal userPrincipal = new RegisteredUserPrincipal(loginUser);
		String issuer = ServletUriComponentsBuilder.fromRequestUri(req)
				.replacePath(null)
				.build()
				.toUriString();
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal, issuer);
		return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<RegisteredUser> register(@RequestBody RegisteredUser user) throws UserNotFoundException, UsernameExistException, EmailExistException {
		// might want validation
		RegisteredUser newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
		return new ResponseEntity<>(newUser, OK);
	}

	@PostMapping("/add")
	public ResponseEntity<RegisteredUser> addNewUser(@RequestParam("firstName") String firstName,
														@RequestParam("lastName") String lastName,
														@RequestParam("username") String username,
														@RequestParam("email") String email,
														@RequestParam("role") String role,
														@RequestParam("isActive") String isActive,			// boolean
														@RequestParam("isNonLocked") String isNonLocked,	// boolean
														@RequestParam(value = "profileImg", required = false) MultipartFile profileImg) {
		RegisteredUser newUser = userService.addNewUser(firstName, lastName, username, email, role,
				Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImg);
		return new ResponseEntity<>(newUser, OK);
	}

	@PostMapping("/update")
	public ResponseEntity<RegisteredUser> update(@RequestParam("currentUsername") String currentUsername,
												 @RequestParam("firstName") String firstName,
												 @RequestParam("lastName") String lastName,
												 @RequestParam("username") String username,
												 @RequestParam("email") String email,
												 @RequestParam("role") String role,
												 @RequestParam("isActive") String isActive,			// boolean
												 @RequestParam("isNonLocked") String isNonLocked,	// boolean
												 @RequestParam(value = "profileImg", required = false) MultipartFile profileImg)
	{
		RegisteredUser updatedUser = userService.updateUser(currentUsername, firstName, lastName, username, email, role,
				Boolean.parseBoolean(isNonLocked), Boolean.parseBoolean(isActive), profileImg);
		return new ResponseEntity<>(updatedUser, OK);
	}

	@PostMapping("/updateprofileimg")
	public ResponseEntity<RegisteredUser> update(@RequestParam("username") String username, @RequestParam(value = "profileImg") MultipartFile profileImg)
	{
		RegisteredUser user = userService.updateProfileImage(username, profileImg);
		return new ResponseEntity<>(user, OK);
	}

	// validate
	@GetMapping("/find/{username}")
	public ResponseEntity<RegisteredUser> getUser(@PathVariable("username") String username) {
		RegisteredUser user = userService.findUserByUsername(username);
		return new ResponseEntity<>(user, OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<UserDAO>> getAllUsers() {
//		List<RegisteredUser> users = userService.getUsers();
//		return new ResponseEntity<>(users, OK);
		return ResponseEntity.ok().body(userService
				.getUsers()
				.stream()
				.map(mapper::toDao)
				.collect(Collectors.toList()));
	}

	@GetMapping("/resetpassword/{email}")
	public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email)
	{
		userService.resetPassword(email);
		return response(OK, EMAIL_SENT + email);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyAuthority('user:delete')")
	public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") String id) {
		userService.deleteUser(id);
		return response(NO_CONTENT, USER_DELETED_SUCCESSFULLY);
	}

	@GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
	public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
	}

	@GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
	public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
		URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try (InputStream inputStream = url.openStream()) {
			int bytesRead;
			byte[] chunk = new byte[1024];
			while((bytesRead = inputStream.read(chunk)) > 0) {
				byteArrayOutputStream.write(chunk, 0, bytesRead);
			}
		}
		return byteArrayOutputStream.toByteArray();
	}


	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
				message.toUpperCase()), httpStatus);
	}

	private void authenticate(String username, String password)	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	private HttpHeaders getJwtHeader(RegisteredUserPrincipal userPrincipal, String issuer) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userPrincipal, issuer));
		return headers;
	}
}

@Data
class RoleToUserForm {
	private String username;
	private String roleName;
}

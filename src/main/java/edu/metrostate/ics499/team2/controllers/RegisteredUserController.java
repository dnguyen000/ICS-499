package edu.metrostate.ics499.team2.controllers;

import java.util.List;
import java.util.stream.Collectors;

import edu.metrostate.ics499.team2.exceptions.domain.EmailExistException;
import edu.metrostate.ics499.team2.exceptions.domain.ExceptionHandling;
import edu.metrostate.ics499.team2.exceptions.domain.UserNotFoundException;
import edu.metrostate.ics499.team2.exceptions.domain.UsernameExistException;
import edu.metrostate.ics499.team2.security.JwtTokenProvider;
import edu.metrostate.ics499.team2.security.RegisteredUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.metrostate.ics499.team2.model.Mapper;
import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.UserDAO;
import edu.metrostate.ics499.team2.services.RegisteredUserService;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

import static edu.metrostate.ics499.team2.security.constants.SecurityConstants.JWT_TOKEN_HEADER;

@RestController
@RequestMapping(path = {"/", "/user"})
public class RegisteredUserController extends ExceptionHandling {

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
    
	@GetMapping("/users")
	public ResponseEntity<List<UserDAO>> getUsers()
	{
	    return ResponseEntity.ok().body(userService
	                                    .getUsers()
	                                    .stream()
	                                    .map(mapper::toDao)
	                                    .collect(Collectors.toList()));
	}
	
	@GetMapping("/home")
	public RegisteredUser showUser() throws UserNotFoundException {
		// application works
		throw new UserNotFoundException("user not found");
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
		RegisteredUser newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
		return new ResponseEntity<>(newUser, HttpStatus.OK);
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

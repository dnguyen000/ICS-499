package edu.metrostate.ics499.team2.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.metrostate.ics499.team2.services.RegisteredUserService;
import lombok.Data;
import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.Role;
import edu.metrostate.ics499.team2.model.Mapper;

@RestController
@RequestMapping("/api")
public class RegisteredUserController {	
	
	private RegisteredUserService userService;
//	private RoleService roleService;
	private Mapper mapper;
	
	@Autowired
	public RegisteredUserController(RegisteredUserService userService, Mapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}
	
	@PostMapping("/user/save")
	public ResponseEntity<RegisteredUser> saveUser(@RequestBody RegisteredUser user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
		RegisteredUser savedUser = userService.saveUser(user);
		if (null != savedUser)
			return ResponseEntity.created(uri).body(savedUser);
		else
			return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}
	
	@PostMapping("/role/addtouser")
	public ResponseEntity<?> addRoletoUser(@RequestBody RoleToUserForm form) {
		userService.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}
    
    @GetMapping("/users")
    public ResponseEntity<List<RegisteredUser>>getUsers() {
    	return ResponseEntity.ok().body(userService.getUsers());
    }
	
	@GetMapping("/email")
	public RegisteredUser getUserByEmail(@PathVariable String email) {
		return this.userService.getUser(email);
	}
}

@Data
class RoleToUserForm {
	private String username;
	private String roleName;
}

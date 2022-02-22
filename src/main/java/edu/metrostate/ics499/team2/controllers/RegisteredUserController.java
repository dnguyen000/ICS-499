package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.services.RegisteredUserService;

@RestController
@RequestMapping("/registeredUser")
public class RegisteredUserController {
	
	
	private RegisteredUserService userService;
	
	@Autowired
	public RegisteredUserController(RegisteredUserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/all")
	public List<RegisteredUser> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping(value = "{userId}")
	public RegisteredUser getUserById(@PathVariable String id) {
		return this.userService.getUserById(id);
	}
	
	@GetMapping(value = "/email")
	public RegisteredUser getUserByEmail(@PathVariable String email) {
		return this.userService.getUserByEmail(email);
	}
	
	@GetMapping("/add")
	@ResponseBody
	public RegisteredUser create(@RequestBody final RegisteredUser registeredUser) {
		//System.out.print("cont create\n");
		return this.userService.create(registeredUser);
	}
	
}

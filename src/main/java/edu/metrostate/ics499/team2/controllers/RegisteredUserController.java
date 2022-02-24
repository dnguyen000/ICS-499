package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.UserCreationDTO;
import edu.metrostate.ics499.team2.model.UserDTO;
import edu.metrostate.ics499.team2.services.RegisteredUserService;

import edu.metrostate.ics499.team2.model.Mapper;

@RestController
@RequestMapping("/api/registereduser")
public class RegisteredUserController {	
	
	private RegisteredUserService userService;
	private Mapper mapper;
	
	@Autowired
	public RegisteredUserController(RegisteredUserService userService) {
		this.userService = userService;
	}
	
//    @GetMapping
//    @ResponseBody
//    public List<UserDTO> getUsers() {
//        return userService.getAll()
//          .stream()
//          .map(mapper::toDto)
//          .collect(toList());
//    }
	
	@GetMapping("/all")
	public List<RegisteredUser> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/email")
	public RegisteredUser getUserByEmail(@PathVariable String email) {
		return this.userService.getUserByEmail(email);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public String create(@RequestBody final RegisteredUser createUser) {
		return this.userService.create(createUser);
	}
	
}

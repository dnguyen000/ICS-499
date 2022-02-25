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

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registereduser")
public class RegisteredUserController {	
	
	private RegisteredUserService userService;
//	private RoleService roleService;
	private Mapper mapper;
	
	@Autowired
	public RegisteredUserController(RegisteredUserService userService, Mapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}
	
    @GetMapping
    @ResponseBody
    public List<UserDTO> getUsers() {
        return userService.getUsers()
          .stream()
          .map(mapper::toDto)
          .collect(Collectors.toList());
    }
	
//	@GetMapping("/all")
//	public List<RegisteredUser> getUsers() {
//		return userService.getUsers();
//	}
	
	@GetMapping("/email")
	public RegisteredUser getUserByEmail(@PathVariable String email) {
		return this.userService.getUserByEmail(email);
	}
	
	// define a POST end point
	@PostMapping("/add")
	@ResponseBody
	public String create(@RequestBody final RegisteredUser createUser) {
		return this.userService.create(createUser);
//		RegisteredUser user = mapper.toUser(userDTO);
//        userDTO.getRoles()
//          .stream()
//          .map(role -> roleService.getOrCreate(role))
//          .forEach(user::addRole);
//        userService.save(user);
//        return new UserIdDTO(user.getId());
	}
	
}

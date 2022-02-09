package edu.metrostate.ics499.team2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;

@RestController
@RequestMapping("/registeredUser")
public class RegisteredUserController {
	
	@Autowired
	private RegisteredUserRepository registeredUserRepo;
	
	@GetMapping(value = "/all")
	public List<RegisteredUser> list() {
		return registeredUserRepo.findAll();
	}
	
	@GetMapping(value = "{userId}")
	public RegisteredUser getUserById(String id) {
		return this.registeredUserRepo.findByUserId(id);
	}
	
	@PostMapping("/add")
	public RegisteredUser create(@RequestBody final RegisteredUser registeredUser) {
		return registeredUserRepo.save(registeredUser);
	}
}

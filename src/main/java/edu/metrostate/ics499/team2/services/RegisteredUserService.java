package edu.metrostate.ics499.team2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;

@Service
public class RegisteredUserService {
	private RegisteredUserRepository userRepo;
	
	@Autowired
	public RegisteredUserService(RegisteredUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public List<RegisteredUser> getUsers() {
		return userRepo.findAll();
	}

	public RegisteredUser getUserById(@PathVariable String id) {
		return this.userRepo.findByUserId(id);
	}

	public RegisteredUser getUserByEmail(@PathVariable String email) {
		return this.userRepo.findByEmail(email);
	}
	
	public RegisteredUser getByUserEmailAndPassword(String email, String password) {
		return this.userRepo.findByEmailAndPassword(email, password);
	}

	public RegisteredUser create(@RequestBody final RegisteredUser registeredUser) {
		return userRepo.save(registeredUser);
	}
}
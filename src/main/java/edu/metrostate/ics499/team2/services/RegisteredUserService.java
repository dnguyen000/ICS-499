package edu.metrostate.ics499.team2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.UserCreationDTO;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;

@Service
public class RegisteredUserService implements ServiceInterface<RegisteredUser>{
	private RegisteredUserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public RegisteredUserService(RegisteredUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public List<RegisteredUser> getUsers() {
		return userRepo.findAll();
	}
	
	public RegisteredUser getUserByEmail(@PathVariable String email) {
		return this.userRepo.findByEmail(email);
	}

	@Transactional(rollbackFor = Exception.class)
	public String create(@RequestBody final RegisteredUser user) {
		if(isValid(user))
			user.setPassword(bCryptPasswordEncoder
			.encode(user.getPassword())); 
		return userRepo.save(user).getEmail();
	}

	@Override
	public boolean isValid(RegisteredUser obj) {
		return getUsers().stream()
				.filter(user -> user.equals(obj)).toList().size() > 0 ? false : true;
	}

}

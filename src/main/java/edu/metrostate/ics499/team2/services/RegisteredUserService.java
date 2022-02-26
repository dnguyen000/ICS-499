package edu.metrostate.ics499.team2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;

@Service
public class RegisteredUserService implements ServiceInterface<RegisteredUser> {
	
	private RegisteredUserRepository userRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public RegisteredUserService(RegisteredUserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Transactional(rollbackFor = Exception.class)
	public String saveUser(final RegisteredUser user) {
		if(isValid(user))
			user.setPassword(bCryptPasswordEncoder
					.encode(user.getPassword()));
		return userRepo.save(user).getEmail();
	}
	
	public RegisteredUser getUserByEmail(String email) {
		return this.userRepo.findByEmail(email);
	}

	public List<RegisteredUser> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public boolean isValid(RegisteredUser obj) {
		return getUsers().stream()
				.filter(user -> user.equals(obj)).toList().size() > 0 ? false : true;
	}

}

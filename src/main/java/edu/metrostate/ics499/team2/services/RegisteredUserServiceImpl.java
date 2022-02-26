package edu.metrostate.ics499.team2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.Role;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;
import edu.metrostate.ics499.team2.repositories.RoleRepo;
import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class RegisteredUserServiceImpl implements RegisteredUserService, ServiceInterface<RegisteredUser> {
	
	private final RegisteredUserRepository userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final RoleRepo roleRepo;
	
	@Autowired
	public RegisteredUserServiceImpl(RegisteredUserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepo = roleRepo;
	}

	@Override
	public RegisteredUser saveUser(final RegisteredUser user) {
		log.info("saving new user: {}, to the database", user.getName());
		if(isValid(user))
			user.setPassword(bCryptPasswordEncoder
					.encode(user.getPassword()));
		return userRepo.save(user);
	}	

	@Override
	public Role saveRole(Role role) {
		log.info("saving new role: {}, to the database", role.getName());
		return roleRepo.save(role);
	}

	public List<RegisteredUser> getUsers() {
		log.info("fetching all users");
		return userRepo.findAll();
	}

	@Override
	public void addRoleToUser(String email, String roleName) {
		log.info("adding role: {}, to user: {}", roleName, email);
		RegisteredUser user = userRepo.findByEmail(email);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
		userRepo.save(user);
	}

	@Override
	public RegisteredUser getUser(String email) {
		log.info("fetching user {}", email);
		return this.userRepo.findByEmail(email);
	}
	
	@Override
	public boolean isValid(RegisteredUser obj) {
		return getUsers().stream()
				.filter(user -> user.equals(obj)).toList().size() > 0 ? false : true;
	}


}

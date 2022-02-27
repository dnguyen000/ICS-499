package edu.metrostate.ics499.team2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.Role;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;
import edu.metrostate.ics499.team2.repositories.RoleRepo;
import edu.metrostate.ics499.team2.security.RegisteredUserPrincipal;
import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class RegisteredUserServiceImpl implements RegisteredUserService, ServiceInterface<RegisteredUser>, UserDetailsService {
	
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
		if(isValid(user)) {
			log.info("saving new user: {}, to the database", user.getName());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRepo.save(user);
		} else {
			log.warn("input user not valid");
			return null;
		}
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisteredUser user = userRepo.findByEmail(username);
        if (user == null) {
        	log.error("user not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        } else {
        	log.info("user: {} found in the database", username);
        }
        return new RegisteredUserPrincipal(user);
	}

}

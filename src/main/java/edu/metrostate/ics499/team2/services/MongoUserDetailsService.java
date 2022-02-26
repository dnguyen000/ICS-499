package edu.metrostate.ics499.team2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.repositories.RegisteredUserRepository;
import edu.metrostate.ics499.team2.security.MongoUserPrincipal;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisteredUserRepository userRepository;

    // define how to return a user from the database
    @Override
    public UserDetails loadUserByUsername(String username) {
        RegisteredUser user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MongoUserPrincipal(user);
    }
    
}
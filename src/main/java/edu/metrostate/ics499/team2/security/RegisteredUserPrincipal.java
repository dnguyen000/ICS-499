package edu.metrostate.ics499.team2.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.model.Role;

// implement UserDetails to be returned by UserDetailsService
public class RegisteredUserPrincipal implements UserDetails {
    
	private RegisteredUser user;

    public RegisteredUserPrincipal(RegisteredUser user) {
        this.user = user;
    }    

    // https://www.baeldung.com/spring-security-granted-authority-vs-role
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    List<String> roles = this.user
				.getRoles()
				.stream()
				.map(Role::getName)
				.collect(Collectors.toList());
	    for (String role : roles) {
	        authorities.add(new SimpleGrantedAuthority(role));
	    }			    
	    return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() { return true;	}

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return true; }
	
	public RegisteredUser getRegisteredUser() {	return user; }
	
}

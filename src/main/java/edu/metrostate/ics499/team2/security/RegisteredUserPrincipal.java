package edu.metrostate.ics499.team2.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.metrostate.ics499.team2.model.RegisteredUser;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

// implement UserDetails to be returned by UserDetailsService
public class RegisteredUserPrincipal implements UserDetails {
    
	private final RegisteredUser user;	// passing our user to spring security

    public RegisteredUserPrincipal(RegisteredUser user) {
        this.user = user;
    }    

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return stream(this.user.getAuthorities()).map(SimpleGrantedAuthority::new).collect(toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { return true;	}

	@Override
	public boolean isAccountNonLocked() { return this.user.isNotLocked(); }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return this.user.isActive(); }
	
}

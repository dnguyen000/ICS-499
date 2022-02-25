package edu.metrostate.ics499.team2.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// implement UserDetails to be returned by UserDetailsService
public class MongoUserPrincipal implements UserDetails {
    
	private RegisteredUser user;

    public MongoUserPrincipal(RegisteredUser user) {
        this.user = user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("user"));
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

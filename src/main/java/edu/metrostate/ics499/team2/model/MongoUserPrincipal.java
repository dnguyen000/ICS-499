package edu.metrostate.ics499.team2.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MongoUserPrincipal implements UserDetails {
    private RegisteredUser user;

    public MongoUserPrincipal(RegisteredUser user) {
        this.user = user;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("User"));
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
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public RegisteredUser getRegisteredUser() {
		return user;
	}
    //...
}

package edu.metrostate.ics499.team2.constants;

import static edu.metrostate.ics499.team2.constants.Authority.*;

public enum Role {
	USER(USER_AUTHORITIES),
	HR(HR_AUTHORITIES),
	MANAGER(MANAGER_AUTHORITIES),
	ADMIN(ADMIN_AUTHORITIES),
	SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

	private final String[] authorities;

	Role(String... authorities) {
		this.authorities = authorities;
	}

	public String[] getAuthorities() {
		return this.authorities;
	}
	
}

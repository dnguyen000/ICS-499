package edu.metrostate.ics499.team2.security;

public class SecurityConstants {

	  public static final String SECRET = "SECRET_KEY";							// any secret key
	  public static final long EXPIRATION_TIME = 900_000; 						// 15 mins
	  public static final String TOKEN_PREFIX = "Bearer ";
	  public static final String HEADER_STRING = "Authorization";
	  // an allowed end point to create users
	  public static final String SIGN_UP_URL = "/api/registereduser/add";
	  public static final String AUTHORITIES = "authorities";
}

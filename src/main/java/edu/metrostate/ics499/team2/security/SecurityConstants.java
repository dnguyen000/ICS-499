package edu.metrostate.ics499.team2.security;

public class SecurityConstants {

	  public static final String SECRET = "SECRET_KEY";							// any secret key
	  public static final long EXPIRATION_TIME = 900_000; 						// 15 mins
	  public static final String TOKEN_PREFIX = "Bearer ";
	  public static final String HEADER_STRING = "Authorization";
	  // an allowed end point to create users
	  public static final String[] PUBLIC_URLS = {"/api/user/save", "api/user/login", "/user/register", "/user/resetpassword/**", "/user/image/**"};
	  public static final String AUTHORITIES = "authorities";
	  public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
	  public static final String UNAUTHORIZED_MESSAGE = "You are not authorized";
	  public static final String OPTIONS_HTTP_METHOD = "OPTIONS";

}

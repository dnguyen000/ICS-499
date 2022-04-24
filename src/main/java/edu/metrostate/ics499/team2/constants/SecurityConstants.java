package edu.metrostate.ics499.team2.constants;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 900_000; 						// 15 min
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page.";
	public static final String UNAUTHORIZED_MESSAGE = "You are not authorized.";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "cannot be verified";
	// array of open/public routes
	 public static final String[] PUBLIC_URLS = {"/", "/styles/**", "/elements/**", "/user/login", "/user/register", "/user/image/**", "/compound/validate/**"};
//	public static final String[] PUBLIC_URLS = {"**"};
}

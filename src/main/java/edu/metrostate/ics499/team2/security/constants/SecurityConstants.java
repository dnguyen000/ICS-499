package edu.metrostate.ics499.team2.security.constants;

public class SecurityConstants {
	// @Value("${jwt.secret}")                           						// wait for .yml
	public static final String SECRET = "SECRET_KEY";							// any secret key
	public static final long EXPIRATION_TIME = 900_000; 						// 15 min
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
	public static final String UNAUTHORIZED_MESSAGE = "You are not authorized";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "cannot be verified";
	// array of public/allowed routes
	// public static final String[] PUBLIC_URLS = {"/api/user/save", "api/user/login", "/user/register", "/user/resetpassword/**", "/user/image/**"};
	public static final String[] PUBLIC_URLS = {"**"};
}

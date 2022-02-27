package edu.metrostate.ics499.team2.security;

import static edu.metrostate.ics499.team2.security.SecurityConstants.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        if (req.getServletPath().equals("/api/ligin")) {
        	chain.doFilter(req, res);
        } else {
            String header = req.getHeader(HEADER_STRING);
            if (header == null || !header.startsWith(TOKEN_PREFIX)) {
                chain.doFilter(req, res);
                return;
            }
        }
        // determine authentication (who) and authorization (resources)
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req, res);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
        	try {
	            // parse the token.
	        	DecodedJWT decoded = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
	                    .build()
	                    .verify(token.replace(TOKEN_PREFIX, ""));        	
	        	String user = decoded.getSubject();        	
	        	String[] roles = decoded.getClaim("authorities").asArray(String.class);
	            if (user != null)
	                return new UsernamePasswordAuthenticationToken(user, null, getAuthorities(roles));
        	} catch (Exception ex) {
        		log.error("error loging in: {}", ex.getMessage());
        		response.setHeader("error", ex.getMessage());
//        		response.sendError(FORBIDDEN.value());
        		Map<String, String> error = new HashMap<>();
        		error.put("error_message", ex.getMessage());
        		response.setContentType(APPLICATION_JSON_VALUE);
        		new ObjectMapper().writeValue(response.getOutputStream(), error);
        	}
            return null;
        }
        return null;
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(String[] roles) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    for (String role: roles) {
	        authorities.add(new SimpleGrantedAuthority(role));
	    }
	    return authorities;
    }
}

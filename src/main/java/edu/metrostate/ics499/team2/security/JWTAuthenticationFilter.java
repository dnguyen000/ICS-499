package edu.metrostate.ics499.team2.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.metrostate.ics499.team2.model.UserCreationDTO;

import static edu.metrostate.ics499.team2.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;        
        // spring defines the /login end point automatically, this just defines where it is
        setFilterProcessesUrl("/api/login");        
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
        	UserCreationDTO creds = new ObjectMapper().readValue(req.getInputStream(), UserCreationDTO.class);
            return authenticationManager.authenticate (
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
    	RegisteredUserPrincipal user = (RegisteredUserPrincipal)auth.getPrincipal();
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(req.getRequestURL().toString())
                .withClaim(AUTHORITIES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes())
		);
        String body = user.getUsername() + " " + access_token;
        
        res.setHeader("access_token", access_token);
        res.getWriter().write(body);
        res.getWriter().flush();
    }
}

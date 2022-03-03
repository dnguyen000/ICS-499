package edu.metrostate.ics499.team2.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static edu.metrostate.ics499.team2.security.constants.SecurityConstants.AUTHORITIES;
import static edu.metrostate.ics499.team2.security.constants.SecurityConstants.EXPIRATION_TIME;
import static edu.metrostate.ics499.team2.security.constants.SecurityConstants.TOKEN_CANNOT_BE_VERIFIED;

import static java.util.Arrays.stream;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secret;

    // generate the token
    public String generateJwtToken (RegisteredUserPrincipal userPrincipal, String issuer) {
        String[] claims = getClaimsFromUser(userPrincipal);
        return JWT.create().withIssuer(issuer)
//                .withAudience("administration")
                .withIssuedAt(new Date()).withSubject(userPrincipal.getUsername())
                .withArrayClaim(AUTHORITIES, claims).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(secret.getBytes()));
    }

    // authorities from token
    public List<GrantedAuthority> getAuthorities(String token, String issuer) {
        String[] claims = getClaimsFromToken(token, issuer);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    // get authentication of user
    // once token verified, set authentication in spring security context
    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new
                UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthToken;
    }

    public boolean isTokenValid(String username, String token, String issuer) {
        JWTVerifier verifier = getJWTVerifier(issuer);
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }

    public String getSubject(String token, String issuer) {
        JWTVerifier verifier = getJWTVerifier(issuer);
        return verifier.verify(token).getSubject();
    }

    // single responsibility principle private methods
    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    // claims from token
    private String[] getClaimsFromToken(String token, String issuer) {
        JWTVerifier verifier = getJWTVerifier(issuer);
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    // claims from user
    private String[] getClaimsFromUser(RegisteredUserPrincipal userPrincipal) {
        // list of string
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : userPrincipal.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]); // return as String array
    }

    private JWTVerifier getJWTVerifier(String issuer) {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(issuer).build();
        } catch (JWTVerificationException exception) {
            // hide actual exception
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }
}

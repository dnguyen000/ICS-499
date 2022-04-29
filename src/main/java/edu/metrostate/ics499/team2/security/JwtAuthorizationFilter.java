package edu.metrostate.ics499.team2.security;

import edu.metrostate.ics499.team2.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static edu.metrostate.ics499.team2.constants.SecurityConstants.TOKEN_PREFIX;

@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        // https://huongdanjava.com/get-base-url-in-controller-in-spring-mvc-and-spring-boot.html
        String issuer = ServletUriComponentsBuilder.fromRequestUri(req)
                .replacePath(null)
                .build()
                .toUriString();
        // if http options method return ok
        if (req.getMethod().equalsIgnoreCase(SecurityConstants.OPTIONS_HTTP_METHOD)) {
            res.setStatus(HttpStatus.OK.value());
        } else {
            String authorizationHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
            log.info("Authorization: " + authorizationHeader);
            // if null or not starts with "Bearer "
            if (authorizationHeader == null || authorizationHeader.equals("Bearer null") || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
                chain.doFilter(req, res);
                return;
            }
            try {
                String token = authorizationHeader.substring(TOKEN_PREFIX.length());
                String username = jwtTokenProvider.getSubject(token, issuer);
                if (jwtTokenProvider.isTokenValid(username, token, issuer) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token, issuer);
                    Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, req);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } catch(Exception e) {
                log.warn("Authorization: " + authorizationHeader + "; invalid.");
                throw new ServletException(e);
            }
        }
        chain.doFilter(req, res);
    }
}

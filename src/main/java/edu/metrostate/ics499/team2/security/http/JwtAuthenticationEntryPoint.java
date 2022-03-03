package edu.metrostate.ics499.team2.security.http;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.metrostate.ics499.team2.security.constants.SecurityConstants;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        HttpResponse httpResponse = new HttpResponse(FORBIDDEN.value(), FORBIDDEN, FORBIDDEN.getReasonPhrase().toUpperCase(), SecurityConstants.FORBIDDEN_MESSAGE);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponse);
        outputStream.flush();
    }
}

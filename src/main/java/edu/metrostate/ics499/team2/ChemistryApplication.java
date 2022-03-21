package edu.metrostate.ics499.team2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan( basePackages = {"edu.metrostate.ics499.team2.model"} )
public class ChemistryApplication {
	
	@Bean public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return new RestTemplate();
	}
	
	// define a BCrypt bean
	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ChemistryApplication.class, args);
	}
	
}

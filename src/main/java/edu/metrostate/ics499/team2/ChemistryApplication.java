package edu.metrostate.ics499.team2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan( basePackages = {"edu.metrostate.ics499.team2.model"} )
public class ChemistryApplication {
	
	// define a BCrypt bean
	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder(); 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ChemistryApplication.class, args);
	}
	
}

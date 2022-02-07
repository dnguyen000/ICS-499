package edu.metrostate.ics499.team2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"edu.metrostate.ics499.team2.model"} )
public class ChemistryApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChemistryApplication.class, args);
	}
}

package edu.metrostate.ics499.team2.services;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Element;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ElementServiceTest {
	
	@Autowired
	ElementService elmService;
	
	@Test
	@DisplayName("it should return false")
	void isValid_returns_false() {
		Element hydrogen = new Element("Hydrogen", "H", "Nonmetal", 1, 1.008, 0, 0);
		assertFalse(elmService.isValid(hydrogen));
	}
	
	@Test
	@DisplayName("it should return true")
	void isValid_returns_true() {
		Element superUnknown = new Element("superUnknown", "SUk", "Nonmetal", 1000, 1.008, 0, 0);
		assertFalse(elmService.isValid(superUnknown));
	}

}

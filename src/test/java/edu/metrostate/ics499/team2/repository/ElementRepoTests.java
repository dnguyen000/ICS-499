package edu.metrostate.ics499.team2.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ElementRepoTests {

	@Autowired
	private ElementRepository elementRepo;

	@Test
	@DisplayName("it should instantiate the repo")
	void testInit() {
		assertNotNull(elementRepo);
	}
	
	@Test
	@DisplayName("should equal Hydrogen")
	void testFindBySymbol() {
		Element elm = elementRepo.findElementBySymbol("H");
		assertEquals(elm.getSymbol(), "H");
	}
	
}

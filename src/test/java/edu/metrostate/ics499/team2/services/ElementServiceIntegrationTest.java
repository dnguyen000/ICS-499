package edu.metrostate.ics499.team2.services;

import edu.metrostate.ics499.team2.exceptions.domain.FailedToLoadPTException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Element;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ElementServiceIntegrationTest {

	@Autowired
	ElementService elmService;

	@Test
	@DisplayName("should list all 118 elements from database")
	void testListAll() throws FailedToLoadPTException {
		List<Element> allElements = elmService.getAllElements();
		assertEquals(allElements.size(), 118);
	}

	@Test
	@DisplayName("should equal H (hydrogen)")
	void testFindBySymbol() {
		Element elm = elmService.getElementBySymbol("H");
		assertEquals(elm.getSymbol(), "H");
	}

	@Test
	@DisplayName("should fail to find element by symbol D")
	void testFailToFindBySymbol() {
		Element elm = elmService.getElementBySymbol("D");
		assertNull(elm);
	}

	@Test
	@DisplayName("should equal 1 (hydrogen)")
	void testFindByAtomicNumber() {
		Element elm = elmService.getElementByAtomicNumber("1");
		assertEquals(elm.getAtomicNumber(), "1");
	}

	@Test
	@DisplayName("should fail to find element by atomic number 0")
	void testFailToFindByAtomicNumber() {
		Element elm = elmService.getElementByAtomicNumber("0");
		assertNull(elm);
	}

}

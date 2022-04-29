package edu.metrostate.ics499.team2.services;

import edu.metrostate.ics499.team2.exceptions.domain.FailedToLoadPTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Element;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ElementServiceMockTest {

	private ElementService elmService;
	private Element elm;

	@BeforeEach
	public void setupMock() {
		elmService = mock(ElementService.class);
		elm = mock(Element.class);
	}

	@Test
	public void testMockCreation(){
		assertNotNull(elmService);
		assertNotNull(elm);
	}

	@Test
	@DisplayName("should list all elements")
	// https://stackoverflow.com/questions/54674251/how-to-mock-jpa-repositorys-find-method-in-unit-tests
	void testListAll() throws FailedToLoadPTException {
		Element elm1 = new Element();
		elm1.setAtomicNumber("1");
		elm1.setAtomicMass("4.5");
		Element elm2 = new Element();
		elm2.setAtomicNumber("2");
		elm2.setAtomicMass("10.3");
		// define what will happen
		when(elmService.getAllElements()).thenReturn(asList(elm1, elm2));
		List<Element> allElements = elmService.getAllElements();
		assertThat(allElements).containsExactly(elm1, elm2);
		verify(elmService).getAllElements();
	}

	@Test
	@DisplayName("should find Element with symbol of H")
	void testFindBySymbol() {
		Element testElm = new Element();
		testElm.setSymbol("H");
		when(elmService.getElementBySymbol("H")).thenReturn(testElm);
		Element actualElm = elmService.getElementBySymbol("H");
		assertEquals(testElm.getSymbol(), actualElm.getSymbol());
		verify(elmService).getElementBySymbol("H");
	}

	@Test
	@DisplayName("should find Element with atomic number of 1")
	void testFindByAtomicNumber() {
		Element testElm = new Element();
		testElm.setAtomicNumber("1");
		when(elmService.getElementByAtomicNumber("1")).thenReturn(testElm);
		Element actualElm = elmService.getElementByAtomicNumber("1");
		assertEquals(testElm.getAtomicNumber(), actualElm.getAtomicNumber());
		verify(elmService).getElementByAtomicNumber("1");
	}

}

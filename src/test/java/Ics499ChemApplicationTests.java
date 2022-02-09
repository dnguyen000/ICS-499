

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.metrostate.ics499.team2.model.Element;
import edu.metrostate.ics499.team2.repositories.ElementRepository;

@SpringBootTest
class Ics499ChemApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private ElementRepository ElementRepo;

	@Test
	@DisplayName("It should instantiate the repo")
	void testInit() {
		assertNotNull(ElementRepo);
	}
	
	@Test
	@DisplayName("should equal Hydrogen")
	void testFindBySymbol() {
		Element elm = ElementRepo.findItemBySymbol("H");
		assertEquals(elm.getSymbol(), "H");
	}
	
}

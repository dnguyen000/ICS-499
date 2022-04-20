package edu.metrostate.ics499.team2.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.metrostate.ics499.team2.model.Compound;
import edu.metrostate.ics499.team2.repositories.CompoundRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CompoundRepositoryTest {
	
	@Autowired
	private CompoundRepository compoundRepo;
	private Compound c1;
	
	@BeforeEach
	void setUp() {
		String userId = "12345";
		HashMap<String, Integer> elements = new HashMap<>();
		elements.put("H", 2);
		elements.put("O", 1);
		
		c1 = new Compound(elements, userId);
		
		compoundRepo.save(c1);
	}
	
	@AfterEach
	void tearDown() {
		compoundRepo.deleteAll();
	}
	
	@Test
	@DisplayName("it should fetch the compound from the repo if it exists")
	void getCompoundByFormula() {
		List<Compound> result = compoundRepo.findCompoundByFormula("H2O");
		assertTrue(result.get(0).equals(c1));
	}

}

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

	@Test
	@DisplayName("it should return all quizes from the queried user")
	void getCompoundsByUserId() {
		List<Compound> result = compoundRepo.findCompoundByUserId("12345");
		System.out.println("size: " + result.size());
		assertTrue(result.size() == 1);
	}

	@Test
	@DisplayName("it should only return quizes fro the specified user")
	void getCompoundsByUserId_2() {
		String userId = "12345";
		HashMap<String, Integer> elements2 = new HashMap<>();
		elements2.put("H", 1);
		elements2.put("O", 2);

		Compound c2 = new Compound(elements2, userId);

		HashMap<String, Integer> elements3 = new HashMap<>();
		elements2.put("Na", 1);
		elements2.put("Cl", 1);

		Compound c3 = new Compound(elements2, userId);

		HashMap<String, Integer> elements4 = new HashMap<>();
		elements4.put("H", 1);
		elements4.put("Cl", 1);

		Compound c4 = new Compound(elements2, "54321");

		HashMap<String, Integer> elements5 = new HashMap<>();
		elements5.put("C", 1);
		elements5.put("O", 2);

		Compound c5 = new Compound(elements2, "54321");

		compoundRepo.save(c2);
		compoundRepo.save(c3);
		compoundRepo.save(c4);
		compoundRepo.save(c5);
		List<Compound> result = compoundRepo.findCompoundByUserId("12345");
		System.out.println("size: " + result.size());
		assertTrue(result.size() == 3);
	}
}

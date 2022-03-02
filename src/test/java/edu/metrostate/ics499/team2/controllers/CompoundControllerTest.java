package edu.metrostate.ics499.team2.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import edu.metrostate.ics499.team2.model.ElementToValidate;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class CompoundControllerTest {
	
	@Autowired
	private CompoundController compoundController;
	
	private RestTemplate restMock = mock(RestTemplate.class);
	
	final String prefix = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/fastformula/";
	final String postfix = "/property/MolecularFormula,MolecularWeight,Title/JSON";
	
	
	@BeforeEach
	void setUp() {
		
		doReturn(null).when(restMock).getForObject(prefix + "" + postfix, Map.class);
	}

	@Test
	@DisplayName("testing pojo")
	void test_process() {
		ElementToValidate payload = new ElementToValidate(new ArrayList<>());
		
		assertEquals(null, compoundController.process(payload));
	}

}

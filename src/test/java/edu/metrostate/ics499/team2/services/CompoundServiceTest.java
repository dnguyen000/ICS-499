package edu.metrostate.ics499.team2.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static edu.metrostate.ics499.team2.constants.PugApiConstants.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompoundServiceTest {
	
	@Autowired
	private CompoundService compoundService;
	
	@Autowired
	private RestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;
	
	private MockRestServiceServer mockServer;
	
	private String compound = "NaCl";
	private ArrayList<String> elementsArray = new ArrayList<>();
	String responseBody = "{\n"
			+ "    \"PropertyTable\": {\n"
			+ "        \"Properties\": [\n"
			+ "            {\n"
			+ "                \"CID\": 5234,\n"
			+ "                \"MolecularFormula\": \"ClNa\",\n"
			+ "                \"MolecularWeight\": \"58.44\",\n"
			+ "                \"Title\": \"Sodium chloride\"\n"
			+ "            },\n"
			+ "            {\n"
			+ "                \"CID\": 23667643,\n"
			+ "                \"MolecularFormula\": \"ClNa\",\n"
			+ "                \"MolecularWeight\": \"57.45\",\n"
			+ "                \"Title\": \"Sodium chloride na-22\"\n"
			+ "            }\n"
			+ "        ]\n"
			+ "    }\n"
			+ "}";
	
	String responseBodyFail = "{}";
	
	@BeforeEach
	public void setUp() {
		this.mockServer = MockRestServiceServer.createServer(this.restTemplate);
	}
	
	@Test
	void test() throws Exception {
		elementsArray.add("Na");
		elementsArray.add("Cl");
		mockServer.expect(requestTo(PUG_PROLOG + PUG_INPUT + compound + PUG_OPERATION + PUG_OUTPUT))
		.andExpect(method(HttpMethod.GET))
		.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		Map<String, ?> result = compoundService.validateInput(compound, elementsArray);
		
		mockServer.verify();
		
		assertFalse(result.isEmpty());
		
//		ResponseEntity<String> response = testRestTemplate.getForEntity(null, null);
//		mockServer.verify();
	}

}
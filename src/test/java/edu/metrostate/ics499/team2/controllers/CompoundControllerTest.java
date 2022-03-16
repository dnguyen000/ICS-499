//package edu.metrostate.ics499.team2.controllers;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.hamcrest.Matcher;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.client.ExpectedCount;
//import org.springframework.test.web.client.MockRestServiceServer;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import edu.metrostate.ics499.team2.model.ElementToValidate;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//class CompoundControllerTest {
//	
//////	@Autowired
////	private CompoundController compoundController;
//	
//	@Autowired
//	CompoundController compoundController;
//	
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Autowired
//	private TestRestTemplate testRestTemplate;
//	
//	@Value("${spring.data.mongodb.host}")
//	private String apiHost;
//	
//	private MockRestServiceServer mockServer;
//	
//	private final String prefix = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/fastformula/";
//	private final String postfix = "/property/MolecularFormula,MolecularWeight,Title/JSON";
//	private String compound = "NaCl";
//	String responseBody = "{\n"
//			+ "    \"PropertyTable\": {\n"
//			+ "        \"Properties\": [\n"
//			+ "            {\n"
//			+ "                \"CID\": 5234,\n"
//			+ "                \"MolecularFormula\": \"ClNa\",\n"
//			+ "                \"MolecularWeight\": \"58.44\",\n"
//			+ "                \"Title\": \"Sodium chloride\"\n"
//			+ "            },\n"
//			+ "            {\n"
//			+ "                \"CID\": 23667643,\n"
//			+ "                \"MolecularFormula\": \"ClNa\",\n"
//			+ "                \"MolecularWeight\": \"57.45\",\n"
//			+ "                \"Title\": \"Sodium chloride na-22\"\n"
//			+ "            }\n"
//			+ "        ]\n"
//			+ "    }\n"
//			+ "}";
//	
//	String responseBodyFail = "{}";
//	
//	@Before
//	public void setUp() {
//		this.mockServer = MockRestServiceServer.createServer(restTemplate);
//	}
//
//	@Test
//	@DisplayName("testing pojo")
//	void test_process() {
//		
////		UriComponents uriComponents = UriComponentsBuilder.newInstance()
////				.scheme("https")
////				.host(this.apiHost)
////				.path(postfix)
////				.build();
////		mockServer.expect(ExpectedCount.manyTimes(),
////				requestTo(uriComponents.toUriString()))
////				.andExpect(method(HttpMethod.GET))
////				.andRespond(withStatus(HttpStatus.OK)
////						.contentType(MediaType.APPLICATION_JSON)
////						.body(responseBody));
////		
////		
////		ResponseEntity<String> response = testRestTemplate.getForEntity("/compound/validate", String.class);
////		
////		
////		
////		System.out.println(response);
//		
//		mockServer.expect(requestTo("http:localhost:8080/compound/validate")).andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
//		
//		ElementToValidate test = new ElementToValidate(null);
//		String result = compoundController.validate(test);
//	}
//}

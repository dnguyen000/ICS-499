package edu.metrostate.ics499.team2.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.metrostate.ics499.team2.security.JwtTokenProvider;
import edu.metrostate.ics499.team2.security.http.JwtAccessDeniedHandler;
import edu.metrostate.ics499.team2.security.http.JwtAuthenticationEntryPoint;
import edu.metrostate.ics499.team2.services.CompoundService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CompoundController.class)
class CompoundControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private CompoundService compoundServiceMock;
	
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	@MockBean
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;
	@MockBean
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@MockBean
	UserDetailsService userDetailsService;
	@MockBean
	RestTemplateBuilder restTemplateBuilder;
	
	private MockMvc mockMvc;
	
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
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.webApplicationContext)
//                .apply(springSecurity())
				.build();
	}

	@Test
	@DisplayName("Test validation of payload from user")
	void test_process() {
	}
}
package edu.metrostate.ics499.team2.controllers;

import edu.metrostate.ics499.team2.model.Flashcard;
import edu.metrostate.ics499.team2.security.JwtTokenProvider;
import edu.metrostate.ics499.team2.security.http.JwtAccessDeniedHandler;
import edu.metrostate.ics499.team2.security.http.JwtAuthenticationEntryPoint;
import edu.metrostate.ics499.team2.services.FlashcardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FlashcardController.class)
class FlashcardControllerMockTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private FlashcardService flashcardServiceMock;
	@MockBean
	private JwtTokenProvider jwtTokenProvider;
	@MockBean
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;
	@MockBean
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@MockBean
	UserDetailsService userDetailsService;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.webApplicationContext)
//                .apply(springSecurity())
				.build();
	}

	@Test
	public void testList() throws Exception {
		assertNotNull(flashcardServiceMock);
		when(flashcardServiceMock.list())
				.thenReturn(List.of(new Flashcard("is this correct?", "no")));

		mockMvc.perform(get("/flashcard/all")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8"))
						.andExpect(status().isOk())
						.andExpect(content().contentType("application/json"))
						.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].question").value("is this correct?"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].answer").value("no"))
						.andDo(print());
		verify(flashcardServiceMock).list();
	}

}

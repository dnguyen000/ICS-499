package edu.metrostate.ics499.team2.controllers;

import edu.metrostate.ics499.team2.model.Mapper;
import edu.metrostate.ics499.team2.model.RegisteredUser;
import edu.metrostate.ics499.team2.security.JwtTokenProvider;
import edu.metrostate.ics499.team2.security.http.JwtAccessDeniedHandler;
import edu.metrostate.ics499.team2.security.http.JwtAuthenticationEntryPoint;
import edu.metrostate.ics499.team2.services.RegisteredUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(RegisteredUserController.class)
public class RegisteredUserControllerMockTest {
//    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private RegisteredUserService registeredUserService;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private Mapper mapper;
    @MockBean
    RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
//                .apply(springSecurity())
                .build();
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        when(registeredUserService.getUsers())
                .thenReturn(List.of(new RegisteredUser("duke", "duke@spring.io")));
        this.mockMvc
                .perform(get("/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(content().contentType("application/json"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("duke"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("duke@spring.io"))
                        .andDo(print());
        verify(registeredUserService).getUsers();
    }
}

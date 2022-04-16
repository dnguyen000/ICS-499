package edu.metrostate.ics499.team2.controllers;

import edu.metrostate.ics499.team2.config.SecurityConfig;
import edu.metrostate.ics499.team2.security.JwtAuthorizationFilter;
import edu.metrostate.ics499.team2.security.JwtTokenProvider;
import edu.metrostate.ics499.team2.security.http.JwtAccessDeniedHandler;
import edu.metrostate.ics499.team2.security.http.JwtAuthenticationEntryPoint;
import edu.metrostate.ics499.team2.services.ElementService;
import edu.metrostate.ics499.team2.services.RegisteredUserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SecurityConfig.class)
@WebMvcTest(controllers = ElementController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebAppConfiguration
class ElementControllerMockTest {
//    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ElementService elmServiceMock;
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
    @MockBean
    JwtAuthorizationFilter jwtAuthorizationFilter;
    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    RegisteredUserService registeredUserService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
                .build();
    }

    @Test
    public void testListView() throws Exception {
        assertThat(this.elmServiceMock).isNotNull();
        mockMvc.perform(MockMvcRequestBuilders.get("/elements"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("elements"))
                .andExpect(MockMvcResultMatchers.view().name("elements"))
                .andExpect(content().string(Matchers.containsString("Welcome to Elements Page")))
                .andDo(print());
    }

    @Test
    public void testList() throws Exception {
        assertThat(this.elmServiceMock).isNotNull();
        assertThat(this.mockMvc).isNotNull();
        mockMvc.perform(MockMvcRequestBuilders.options("/elements/list")
//                        .header("Authorization", "Bearer null")
                        .header("Access-Control-Request-Method", "GET")
                        .header("Origin", "http://localhost:4200")
//                        .contentType(MediaType.APPLICATION_JSON))
                )
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            ;
    }

    @Test
    @WithMockUser(roles="ADMIN")
    public void requestProtectedUrlWithUser() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/"));
//                    .andExpect(authenticated());
    }
}

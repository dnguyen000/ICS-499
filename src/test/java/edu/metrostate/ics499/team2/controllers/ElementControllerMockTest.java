package edu.metrostate.ics499.team2.controllers;

import edu.metrostate.ics499.team2.services.ElementService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SecurityConfig.class)
//@WebMvcTest(controllers = ElementController.class)
// load entire app context instead of explicitly as above
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
class ElementControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private ElementService elmServiceMock;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testListView() throws Exception {
        assertThat(this.elmServiceMock).isNotNull();
        mockMvc.perform(get("/elements"))
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
        mockMvc.perform(get("/elements/list")
                        .header("Authorization", "Bearer null")
                        .header("Access-Control-Request-Method", "GET")
                        .header("Origin", "http://localhost:4200")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());
    }

    // https://docs.spring.io/spring-security/reference/5.6.0-RC1/servlet/test/mockmvc.html#_running_as_a_user_in_spring_mvc_test_with_annotations
    @Test
    @WithMockUser(roles="USER")
    public void requestProtectedUrlWithUser() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/user/list"))
                .andExpect(status().isUnauthorized())
                .andExpect(authenticated());
    }

    @Test
    public void csrfValidate() throws Exception {
        mockMvc.perform(post("/").with(csrf()));
    }

    @Test
    public void invalidCsrf() throws Exception {
        mockMvc.perform(post("/").with(csrf().useInvalidToken()));
    }
}

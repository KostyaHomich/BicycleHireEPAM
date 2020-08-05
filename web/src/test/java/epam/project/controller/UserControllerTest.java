package epam.project.controller;


import epam.project.config.WebConfigTest;
import epam.project.entity.User;
import epam.project.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfigTest.class)
@WithMockUser(username = "Kostiks87", password = "kostiks87", roles = {"ADMIN"})
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private UserService userService;
    private User testUser;
    private User signInUser;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        testUser = new User();
        testUser.setId(1);
        testUser.setLogin("mafiosi");
        testUser.setPassword("JDUF7RUTI6JHUGYT7586UTJGHRY5746RYFHGURJT");
        testUser.setEmail("hyperemail2019@gmail.com");
        testUser.setFirstName("John");
        testUser.setLastName("Philips");
        testUser.setRegistrationDate("2019-01-08 01:52:00");

        signInUser = new User();
        signInUser.setLogin("Kostiks87");
        signInUser.setFirstName("ASDASDASD");
        signInUser.setLastName("ASDASDASD");
        signInUser.setEmail("ASDASD@mail.ru");
        signInUser.setUserRole(User.UserRole.ROLE_ADMIN);
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getUsers() throws Exception {
        Mockito.when(userService.takeAll()).thenReturn(Arrays.asList(testUser, testUser, testUser));
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);
        mockMvc.perform(MockMvcRequestBuilders.get("/users").session(mockHttpSession))
                .andExpect(model().attribute("users", hasSize(3)));

    }

    @Test
    public void addUser() throws Exception {
        Mockito.when(userService.register(testUser)).thenReturn(Boolean.TRUE);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/sign_up")
                .param("login", "login")
                .param("password", "asdfasdf")
                .param("repeat_password", "asdfasdf")
                .param("first_name", "first_name")
                .param("last_name", "last_name")
                .param("email", "email@mail.com"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        Mockito.when(userService.getById(1)).thenReturn(testUser);
        mockMvc.perform(MockMvcRequestBuilders.get("/user_details").session(mockHttpSession)
                .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        Mockito.when(userService.update(testUser)).thenReturn(Boolean.TRUE);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/update")
                .requestAttr("user", testUser)
                .session(mockHttpSession)
                .param("id", "1")
                .param("login", "login")
                .param("password", "asdfasdf")
                .param("repeat_password", "asdfasdf")
                .param("first_name", "first_name")
                .param("last_name", "last_name")
                .param("email", "email@mail.ru"))
                .andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        Mockito.when(userService.getById(1)).thenReturn(testUser);
        mockMvc.perform(MockMvcRequestBuilders.get("/user_details").session(mockHttpSession)
                .param("userId", "1"))
                .andExpect(status().isOk());
    }
}

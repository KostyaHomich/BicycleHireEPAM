package epam.project.controller;

import epam.project.config.WebConfigTest;
import epam.project.entity.User;
import epam.project.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfigTest.class)
@WithMockUser(username = "Kostiks87", password = "kostiks87", roles = {"ADMIN"})
@WebAppConfiguration
public class PageControllerTest {

    @Autowired
    private UserService userService;
    private User signInUser;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        User testUser = new User();
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
    public void main() throws Exception {
        mockMvc.perform(get("/main"))
                .andExpect(status().isOk());
    }

    @Test
    public void registration() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk());
    }

    @Test
    public void user() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        mockMvc.perform(get("/user").session(mockHttpSession))
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

}

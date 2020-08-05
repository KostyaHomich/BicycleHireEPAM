package epam.project.controller;

import epam.project.config.WebConfigTest;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import epam.project.service.impl.PointHireService;
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
public class PointHireControllerTest {

    @Autowired
    private PointHireService pointHireService;
    private PointHire testPointHire;
    private User signInUser;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        testPointHire = new PointHire();
        testPointHire.setId(1);
        testPointHire.setDescription("AAAAAAAAA");
        testPointHire.setLocation("AAAAAAAAAA");
        testPointHire.setTelephone("+3456837");

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
    public void getPointHires() throws Exception {
        Mockito.when(pointHireService.takeAll()).thenReturn(Arrays.asList(testPointHire, testPointHire, testPointHire));
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/point_hires").session(mockHttpSession))
                .andExpect(model().attribute("pointHireList", hasSize(3)));

    }

    @Test
    public void addPointHire() throws Exception {
        Mockito.when(pointHireService.add(testPointHire)).thenReturn(Boolean.TRUE);
        mockMvc.perform(MockMvcRequestBuilders.get("/point_hire/add")
                .param("id", "1")
                .param("location", "asdfasdf")
                .param("telephone", "+3734732")
                .param("description", "asdfasdfasdf"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePointHire() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        Mockito.when(pointHireService.getById(1)).thenReturn(testPointHire);
        mockMvc.perform(MockMvcRequestBuilders.get("/point_hire/delete").session(mockHttpSession)
                .param("pointHireId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePointHire() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);
        Mockito.when(pointHireService.update(testPointHire)).thenReturn(Boolean.TRUE);
        mockMvc.perform(MockMvcRequestBuilders.get("/point_hire/update")
                .session(mockHttpSession)
                .param("id", "1")
                .param("location", "asdfasdf")
                .param("telephone", "+3734732")
                .param("description", "asdfasdfasdf"))
                .andExpect(status().isOk());
    }

}

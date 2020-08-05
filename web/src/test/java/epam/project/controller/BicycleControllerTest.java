package epam.project.controller;

import epam.project.config.WebConfigTest;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import epam.project.service.impl.BicycleService;
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
@WebAppConfiguration
@WithMockUser(username = "Kostiks87", password = "kostiks87", roles = {"ADMIN"})
public class BicycleControllerTest {

    @Autowired
    private BicycleService bicycleService;
    private Bicycle testBicycle;
    private User signInUser;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        PointHire pointHire=new PointHire();
        pointHire.setId(1);

        testBicycle = new Bicycle();
        testBicycle.setId(1);
        testBicycle.setPointHire(pointHire);
        testBicycle.setName("AAAAAAAA");
        testBicycle.setDescription("AAAAAAAA");
        testBicycle.setLink("AAAAAAAA");

        signInUser = new User();
        signInUser.setId(1);
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
    public void getBicycles() throws Exception {
        Mockito.when(bicycleService.getBicycles(0, 5, signInUser)).thenReturn(Arrays.asList(testBicycle, testBicycle, testBicycle));
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/bicycles").session(mockHttpSession))
                .andExpect(model().attribute("bicycles", hasSize(3)));
    }

    @Test
    public void addBicycle() throws Exception {
        Mockito.when(bicycleService.add(testBicycle)).thenReturn(Boolean.TRUE);
        mockMvc.perform(MockMvcRequestBuilders.get("/bicycle/add")
                .param("name", "login")
                .param("description", "asdfasdf")
                .param("link", "asdfasdf")
                .param("pointHireId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBicycle() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        Mockito.when(bicycleService.getById(1)).thenReturn(testBicycle);
        mockMvc.perform(MockMvcRequestBuilders.get("/bicycle/delete").session(mockHttpSession)
                .param("bicycleId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBicycle() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        Mockito.when(bicycleService.update(testBicycle)).thenReturn(Boolean.TRUE);
        mockMvc.perform(MockMvcRequestBuilders.get("/bicycle/update")
                .session(mockHttpSession)
                .param("bicycleId", "1")
                .param("name", "login")
                .param("description", "asdfasdf")
                .param("link", "asdfasdf")
                .param("pointHireId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Kostiks87", password = "kostiks87")
    public void getBestBicycles() throws Exception {

        Mockito.when(bicycleService.showBestBicycles(signInUser)).thenReturn(Arrays.asList(testBicycle, testBicycle, testBicycle));
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/bicycles_best").session(mockHttpSession))
                .andExpect(model().attribute("bicycles", hasSize(3)));

    }

    @Test
    @WithMockUser(username = "Kostiks87", password = "kostiks87")
    public void addBestBicycle() throws Exception {
        Mockito.when(bicycleService.getById(1)).thenReturn(testBicycle);
        mockMvc.perform(MockMvcRequestBuilders.get("/bicycle_best/add")
                .param("bicycleId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Kostiks87", password = "kostiks87")
    public void deleteBestBicycle() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.putValue("signInUser", signInUser);

        Mockito.when(bicycleService.getById(1)).thenReturn(testBicycle);
        mockMvc.perform(MockMvcRequestBuilders.get("/bicycle_best/delete").session(mockHttpSession)
                .param("bicycleId", "1"))
                .andExpect(status().isOk());
    }
}

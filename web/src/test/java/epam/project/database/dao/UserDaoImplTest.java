package epam.project.database.dao;

import epam.project.config.DaoConfigTest;
import epam.project.database.exception.DaoException;
import epam.project.entity.User;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfigTest.class})
@WebAppConfiguration
public class UserDaoImplTest {

    private User testUser;

    @Autowired
    private UserDaoImpl userDaoTest;

    @Autowired
    private Flyway flyway;

    @Before
    public void initialize() {
        flyway.migrate();

        testUser = new User();
        testUser.setLogin("mafiosi");
        testUser.setPassword("JDUF7RUTI6JHUGYT7586UTJGHRY5746RYFHGURJT");
        testUser.setEmail("hyperemail2019@gmail.com");
        testUser.setFirstName("John");
        testUser.setLastName("Philips");
        testUser.setRegistrationDate("2019-01-08 01:52:00");

    }


    @Test
    public void getUserByPK() throws DaoException {
        testUser.setLogin("getUserByPK");
        User expected = userDaoTest.persist(testUser);
        int id = expected.getId();
        User actual = userDaoTest.getByPK(id);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void updateUser() throws DaoException {
        testUser.setLogin("updateUser");
        User expected = userDaoTest.persist(testUser);
        testUser.setLogin("nonMafiosi");
        testUser.setId(expected.getId());
        userDaoTest.update(testUser);
        User actual = userDaoTest.getByPK(expected.getId());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAll() throws DaoException {
        testUser.setLogin("getAll");
        userDaoTest.persist(testUser);
        testUser.setLogin("getAll22");
        userDaoTest.persist(testUser);
        List<User> users = userDaoTest.getAll();
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void checkLoginExistance() {
        testUser.setLogin("ASDASD");
        userDaoTest.persist(testUser);
        boolean actual=userDaoTest.checkLoginExistance(testUser.getLogin());
        Assert.assertTrue(actual);
    }

    @Test
    public void checkEmailExistance() {
        testUser.setEmail("ASDASD@epam.com");
        userDaoTest.persist(testUser);
        boolean actual=userDaoTest.checkEmailExistance(testUser.getEmail());
        Assert.assertTrue(actual);
    }

}

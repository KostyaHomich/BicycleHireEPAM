package epam.project.database.dao;

import epam.project.config.DaoConfigTest;
import epam.project.database.exception.DaoException;
import epam.project.entity.PointHire;
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
public class PointHireDaoImplTest {

    private PointHire testPointHire;

    @Autowired
    private PointHireDaoImpl dao;

    @Autowired
    private Flyway flyway;

    @Before
    public void initializePointHire() {
        flyway.migrate();
        testPointHire = new PointHire();
        testPointHire.setDescription("A");
        testPointHire.setTelephone("+375447031096");
        testPointHire.setLocation("AAAAAAAAAAAA");
    }

    @Test
    public void getAllTest() throws DaoException {
        dao.persist(testPointHire);
        testPointHire.setLocation("BBBBBBBBBBBBB");
        dao.persist(testPointHire);
        List<PointHire> list = dao.getAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void persistPointHire() throws DaoException {
        PointHire actual = dao.persist(testPointHire);
        testPointHire.setId(actual.getId());
        Assert.assertEquals(testPointHire, actual);
    }

    @Test
    public void getPointHireByPK() throws DaoException {
        PointHire expected = dao.persist(testPointHire);
        Integer id = expected.getId();

        PointHire actual = dao.getByPK(id);

        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }


    @Test
    public void updatePointHire() throws DaoException {
        PointHire expected = dao.persist(testPointHire);

        Integer id = expected.getId();
        expected.setDescription("forPizza");
        dao.update(expected);
        PointHire actual = dao.getByPK(id);

        Assert.assertEquals(expected.getLocation(), actual.getLocation());

    }


}

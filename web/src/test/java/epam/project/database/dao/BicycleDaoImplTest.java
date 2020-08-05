package epam.project.database.dao;

import epam.project.config.DaoConfigTest;
import epam.project.database.exception.DaoException;
import epam.project.entity.Bicycle;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfigTest.class})
@WebAppConfiguration
public class BicycleDaoImplTest {

    private Bicycle testBicycle;
    private PointHire pointHire;

    @Autowired
    private Flyway flyway;

    @Autowired
    private BicycleDaoImpl dao;

    @Before
    public void initialize() {
        pointHire=new PointHire();
        pointHire.setId(1);

        flyway.migrate();
        testBicycle = new Bicycle();
        testBicycle.setDescription("A");
        testBicycle.setLink("aaaaaaaa");
        testBicycle.setName("Jabka");
        testBicycle.setPointHire(pointHire);
    }

    @Test
    public void persistBicycle() throws DaoException {

        Bicycle actual = dao.persist(testBicycle);
        testBicycle.setId(actual.getId());
        Assert.assertEquals(testBicycle, actual);
    }

    @Test
    public void getBicycleByPK() throws DaoException {
        Bicycle expected = dao.persist(testBicycle);
        Bicycle actual = dao.getByPK(expected.getId());
        actual.setPointHire(pointHire);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void updateBicycle() throws DaoException {
        Bicycle expected = dao.persist(testBicycle);
        expected.setName("WannaPizza");
        dao.update(expected);
        Bicycle actual = dao.getByPK(expected.getId());
        actual.setPointHire(pointHire);
        Assert.assertEquals(expected, actual);
    }


}

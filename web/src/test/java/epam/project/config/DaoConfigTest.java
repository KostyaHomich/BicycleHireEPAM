package epam.project.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import epam.project.database.dao.BicycleDaoImpl;
import epam.project.database.dao.PointHireDaoImpl;
import epam.project.database.dao.UserDaoImpl;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebAppConfiguration
public class DaoConfigTest {

    private DataSource emDataSource;

    {
        try {
            emDataSource = EmbeddedPostgres.builder().start().getPostgresDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public HikariDataSource dataSource() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        try(Connection connection=emDataSource.getConnection()){
            hikariConfig.setDriverClassName("org.postgresql.Driver");
            hikariConfig.setJdbcUrl(connection.getMetaData().getURL());
            hikariConfig.setUsername(connection.getMetaData().getUserName());
            hikariConfig.setPassword("");
        }
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) throws Exception {
        try {
            LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
            localSessionFactoryBean.setDataSource(dataSource);
            localSessionFactoryBean.setPackagesToScan("epam.project.entity");
            return localSessionFactoryBean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    @Bean
    @Autowired
    public Flyway flyway(DataSource dataSource) throws SQLException {
        return Flyway.configure().dataSource(dataSource).load();
    }


    @Bean
    @Scope("prototype")
    public JdbcTemplate jdbcTemplate() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    @Scope("prototype")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws SQLException {
        return new NamedParameterJdbcTemplate(dataSource());
    }


    @Bean
    public UserDaoImpl userDaoImpl() {
        return new UserDaoImpl();
    }

    @Bean
    public PointHireDaoImpl pointHireDaoImpl() {
        return new PointHireDaoImpl();
    }

    @Bean
    public BicycleDaoImpl bicycleDaoImpl() {
        return new BicycleDaoImpl();
    }

}

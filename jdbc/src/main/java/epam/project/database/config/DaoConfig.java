package epam.project.database.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import epam.project.database.dao.BicycleDaoImpl;
import epam.project.database.dao.PointHireDaoImpl;
import epam.project.database.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DaoConfig {

    @Value("${driver}")
    private String driver;
    @Value("${host}")
    private String host;
    @Value("${password}")
    private String pass;
    @Value("${poolCapacity}")
    private String poolCapacity;
    @Value("${url}")
    private String url;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(host);
        hikariConfig.setPassword(pass);
        hikariConfig.setMaximumPoolSize(Integer.parseInt(poolCapacity));
        return new HikariDataSource(hikariConfig);
    }
    @Bean
    public AnnotationConfigApplicationContext applicationContextDao() {
        return new AnnotationConfigApplicationContext(this.getClass());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Scope("prototype")
    public JdbcTemplate jdbcTemplate() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    @Scope("prototype")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
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

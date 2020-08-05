package epam.project.database.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import epam.project.database.dao.BicycleDaoImpl;
import epam.project.database.dao.PointHireDaoImpl;
import epam.project.database.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("epam.project.entity");
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(localSessionFactoryBean().getObject());
        return transactionManager;
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

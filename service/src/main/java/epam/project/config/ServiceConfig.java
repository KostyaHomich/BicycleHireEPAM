package epam.project.config;



import epam.project.service.impl.BicycleService;
import epam.project.service.impl.PointHireService;
import epam.project.service.impl.UserService;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class ServiceConfig {

    @Bean
    public AnnotationConfigApplicationContext applicationContextService() {
        return new AnnotationConfigApplicationContext(this.getClass());
    }



    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public BicycleService bicycleService() {
        return new BicycleService();
    }

    @Bean
    public PointHireService pointHireService() {
        return new PointHireService();
    }
}

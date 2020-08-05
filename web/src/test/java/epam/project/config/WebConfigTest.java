package epam.project.config;

import epam.project.service.impl.BicycleService;
import epam.project.service.impl.PointHireService;
import epam.project.service.impl.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {SecurityConfig.class, DaoConfigTest.class})
public class WebConfigTest implements WebMvcConfigurer {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public BicycleService bicycleService() {
        return Mockito.mock(BicycleService.class);
    }

    @Bean
    public PointHireService pointHireService() {
        return Mockito.mock(PointHireService.class);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}

package epam.project.config;


import epam.project.builder.BicycleBuilder;
import epam.project.builder.PointHireBuilder;
import epam.project.builder.UserBuilder;
import epam.project.command.CommandLogIn;
import epam.project.command.bicycle.*;
import epam.project.command.point.hire.*;
import epam.project.command.user.CommandDeleteUser;
import epam.project.command.user.CommandShowUserDetails;
import epam.project.command.user.CommandShowUserList;
import epam.project.command.user.CommandSignUpUser;
import epam.project.util.DatabaseUpdater;
import epam.project.util.Parser;
import epam.project.validation.impl.BicycleValidator;
import epam.project.validation.impl.PointHireValidator;
import epam.project.validation.impl.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "epam.project")
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public Parser parser() {
        return new Parser();
    }

    @Bean
    public BicycleBuilder bicycleBuilder() {
        return new BicycleBuilder();
    }

    @Bean
    public PointHireBuilder pointHireBuilder() {
        return new PointHireBuilder();
    }

    @Bean
    public UserBuilder userBuilder() {
        return new UserBuilder();
    }


    @Bean
    public BicycleValidator bicycleValidator() {
        return new BicycleValidator();
    }

    @Bean
    public PointHireValidator pointHireValidator() {
        return new PointHireValidator();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }

    @Bean
    public DatabaseUpdater databaseUpdater() {
        return new DatabaseUpdater();
    }

    @Bean
    public CommandSignUpUser commandSignUpUser() {
        return new CommandSignUpUser();
    }

    @Bean
    public CommandLogIn commandLogIn() {
        return new CommandLogIn();
    }

    @Bean
    public CommandDeleteUser commandDeleteUser() {
        return new CommandDeleteUser();
    }

    @Bean
    public CommandShowUserDetails commandShowUserDetails() {
        return new CommandShowUserDetails();
    }

    @Bean
    public CommandShowUserList commandShowUserList() {
        return new CommandShowUserList();
    }

    @Bean
    public CommandAddBestBicycle commandAddBestBicycle() {
        return new CommandAddBestBicycle();
    }

    @Bean
    public CommandAddBicycle commandAddBicycle() {
        return new CommandAddBicycle();
    }

    @Bean
    public CommandDeleteBestBicycle commandDeleteBestBicycle() {
        return new CommandDeleteBestBicycle();
    }

    @Bean
    public CommandDeleteBicycle commandDeleteBicycle() {
        return new CommandDeleteBicycle();
    }

    @Bean
    public CommandShowBestBicycles commandShowBestBicycles() {
        return new CommandShowBestBicycles();
    }

    @Bean
    public CommandShowBicycleDetails commandShowBicycleDetails() {
        return new CommandShowBicycleDetails();
    }

    @Bean
    public CommandShowBicycleList commandShowBicycleList() {
        return new CommandShowBicycleList();
    }

    @Bean
    public CommandUpdateBicycle commandUpdateBicycle() {
        return new CommandUpdateBicycle();
    }

    @Bean
    public CommandAddPointHire commandAddPointHire() {
        return new CommandAddPointHire();
    }

    @Bean
    public CommandDeletePointHire commandDeletePointHire() {
        return new CommandDeletePointHire();
    }

    @Bean
    public CommandShowPointHireDetails commandShowPointHireDetails() {
        return new CommandShowPointHireDetails();
    }

    @Bean
    public CommandShowPointHireList commandShowPointHireList() {
        return new CommandShowPointHireList();
    }

    @Bean
    public CommandUpdatePointHire commandUpdatePointHire() {
        return new CommandUpdatePointHire();
    }

}

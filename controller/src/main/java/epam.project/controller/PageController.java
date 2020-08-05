package epam.project.controller;

import i18n.LanguageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PageController {

    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping({"/", "/main"})
    public String showMainPage() {
        return "main";
    }

    @RequestMapping(value = "/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/user")
    public String showUserPage(Model model) {
        return "user";
    }

    @RequestMapping("/lang")
    public String lang(@RequestParam String lang, HttpServletRequest request, HttpServletResponse response) {
        Optional<LanguageEnum> languageEnum = LanguageEnum.fromString(lang);
        Locale locale = languageEnum.map(LanguageEnum::getLocaleByEnum).orElseGet(() -> LanguageEnum.getLocaleByEnum(LanguageEnum.EN));
        localeResolver.setLocale(request, response, locale);
        return "forward:/main";
    }
}

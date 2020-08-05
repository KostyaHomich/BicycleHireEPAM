package epam.project.command;

import epam.project.builder.UserBuilder;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.RequestParameterParser;
import epam.project.validation.ValidationResult;
import epam.project.validation.impl.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Scope("prototype")
public class CommandLogIn implements Command {
    private static final String ERROR = "error";
    private static final String LOGIN = "login";


    @Autowired
    private UserService userService;
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private UserValidator userValidator;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {

        try {

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = userValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                User user = userBuilder.build(parameters);

                if (userService.checkLoginExistence(user.getLogin())) {
                    User signInUser = userService.signIn(user.getLogin(), user.getPassword());
                    if (signInUser.getEnabled()) {
                        return "forward:/user";
                    } else {
                        model.addAttribute(ERROR, "user.error.banned");
                        return LOGIN;
                    }
                } else {
                    model.addAttribute(ERROR, "user.error.login_not_exist");
                    return LOGIN;
                }
            } else {
                model.addAttribute("errorsList", validationResult);
                return LOGIN;
            }
        } catch (ServiceException e) {
            model.addAttribute(ERROR, "page.error.login_failed");
            throw new CommandException("Failed to login user");
        }

    }

}

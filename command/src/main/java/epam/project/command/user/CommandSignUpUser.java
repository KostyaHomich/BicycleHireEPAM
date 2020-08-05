package epam.project.command.user;

import epam.project.builder.UserBuilder;
import epam.project.command.Command;
import epam.project.command.CommandException;
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
import java.time.LocalDate;
import java.util.Map;

@Component
@Scope("prototype")
public class CommandSignUpUser implements Command {

    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "repeat_password";
    private static final String ERROR = "error";
    private static final String FORWARD_TO_REGISTRATION_PAGE = "forward:/registration";
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = userValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                User user = userBuilder.build(parameters);
                if (!userService.checkLoginExistence(user.getLogin())) {

                    String password = request.getParameter(PASSWORD);
                    String repeatPassword = request.getParameter(REPEAT_PASSWORD);
                    user.setRegistrationDate(LocalDate.now().toString());

                    if (password.equals(repeatPassword)) {
                        userService.register(user);
                        return "forward:/login";
                    } else {
                        model.addAttribute(ERROR, "user.error.password_are_not_equals");
                        return FORWARD_TO_REGISTRATION_PAGE;
                    }
                } else {
                    model.addAttribute(ERROR, "user.error.login_already_taken");
                    return FORWARD_TO_REGISTRATION_PAGE;
                }
            } else {
                model.addAttribute("errorsList", validationResult);
                return FORWARD_TO_REGISTRATION_PAGE;
            }
        } catch (ServiceException e) {
            model.addAttribute(ERROR, "user.error.register_user");
            throw new CommandException("Failed to register user", e);
        }
    }
}

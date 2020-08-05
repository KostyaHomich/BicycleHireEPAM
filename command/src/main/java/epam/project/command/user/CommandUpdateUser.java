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
import java.util.Map;

@Component
@Scope("prototype")
public class CommandUpdateUser implements Command {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserBuilder userBuilder;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = userValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                User user = userBuilder.build(parameters);
                userService.update(user);
                return "redirect:/users";
            } else {
                model.addAttribute("errorsList", validationResult);
                return "entity_details";
            }
        } catch (ServiceException e) {
            model.addAttribute("error", "user.error.update_user");
            throw new CommandException("Failed to update user", e);
        }
    }
}

package epam.project.command.user;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope("prototype")
public class CommandShowUserDetails implements Command {

    @Autowired
    private UserService userService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {

            int id = Integer.parseInt(request.getParameter("userId"));
            if (request.getParameter("userId") != null) {

                if (id == 0) {
                    User user = new User();
                    return setAttribute(model, user);
                } else {
                    User user = userService.getById(id);
                    return setAttribute(model, user);
                }
            } else {
                model.addAttribute("error", "page.error.show_user_details");
                return "forward:/users";
            }
        } catch (ServiceException e) {
            model.addAttribute("error", "page.error.show_user_details");
            throw new CommandException("Failed to show user details", e);
        }
    }

    private String setAttribute(Model model, User user) {
        model.addAttribute("user", user);
        return "entity_details";
    }
}

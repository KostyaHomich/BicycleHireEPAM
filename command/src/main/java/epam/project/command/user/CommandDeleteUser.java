package epam.project.command.user;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.EntityType;
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
public class CommandDeleteUser implements Command {

    @Autowired
    private UserService userService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {
            model.addAttribute("entity", EntityType.USER);
            if (request.getParameter("userId") != null) {
                int id = Integer.parseInt(request.getParameter("userId"));

                User user = userService.getById(id);
                userService.delete(user);

                return "forward:/users";
            } else {
                model.addAttribute("error", "user.error.delete_user");
                return "forward:/users";
            }
        } catch (ServiceException e) {
            model.addAttribute("error", "user.error.delete_user");
            throw new CommandException("Failed to delete user", e);

        }
    }


}

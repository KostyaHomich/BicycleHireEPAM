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
import java.util.List;

@Component
@Scope("prototype")
public class CommandShowUserList implements Command {

    @Autowired
    private UserService userService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {

            List<User> userList = userService.takeAll();
            model.addAttribute("users", userList);
            return "entity_list";
        } catch (ServiceException e) {
            model.addAttribute("error", "page.error.show_user_list");
            throw new CommandException("Failed to show user list", e);
        }
    }
}

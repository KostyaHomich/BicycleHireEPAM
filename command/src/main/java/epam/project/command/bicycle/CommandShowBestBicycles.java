package epam.project.command.bicycle;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.Bicycle;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@Scope("prototype")
public class CommandShowBestBicycles implements Command {
    @Autowired
    private BicycleService bicycleService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {

        try {
            model.addAttribute("viewName", "best_bicycle_list");
            User user = (User) request.getSession().getAttribute("signInUser");
            List<Bicycle> bicycleList = bicycleService.showBestBicycles(user);
            model.addAttribute("bicycles", bicycleList);
            return "entity_list";
        } catch (ServiceException e) {
            model.addAttribute("error", "page.error.show_bicycle_list");
            throw new CommandException("Failed to show best bicycle list", e);
        }


    }
}
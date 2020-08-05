package epam.project.command.bicycle;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.Bicycle;
import epam.project.entity.EntityType;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope("prototype")
public class CommandAddBestBicycle implements Command {

    @Autowired
    private BicycleService bicycleService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {
            model.addAttribute("entity", EntityType.BICYCLE);

            User user = (User) request.getSession().getAttribute("signInUser");
            int id = Integer.parseInt(request.getParameter("bicycleId"));
            Bicycle bicycle = bicycleService.getById(id);

            bicycleService.addBestBicycle(bicycle, user);
            return "forward:/bicycles";
        } catch (ServiceException e) {
            model.addAttribute("error", "bicycle.error.add_bicycle");
            throw new CommandException("Failed to add bicycle.", e);
        }

    }

}

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
public class CommandShowBicycleList implements Command {

    @Autowired
    private BicycleService bicycleService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {

        try {
            int page = 1;
            int recordsPerPage = 5;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int currentAmountBicycles = (page - 1) * recordsPerPage;

            User user = (User) request.getSession().getAttribute("signInUser");
            List<Bicycle> bicycleList = bicycleService.getBicycles(currentAmountBicycles, recordsPerPage, user);

            int amountBicycles = bicycleService.getAmountBicycles();
            int amountPages = amountBicycles / recordsPerPage;


            model.addAttribute("bicycles", bicycleList);
            model.addAttribute("amountPages", amountPages);
            model.addAttribute("page", page);

            return "entity_list";
        } catch (ServiceException e) {
            model.addAttribute("error", "page.error.show_bicycle_list");
            throw new CommandException("Failed to show bicycle list", e);
        }


    }
}

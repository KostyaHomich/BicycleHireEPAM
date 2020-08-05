package epam.project.command.bicycle;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHire;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope("prototype")
public class CommandShowBicycleDetails implements Command {

    @Autowired
    private BicycleService bicycleService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {
            model.addAttribute("viewName", "bicycle_details");

            if (request.getParameter("bicycleId") != null) {
                int id = Integer.parseInt(request.getParameter("bicycleId"));
                if (id == 0) {
                    int pointHireId = Integer.parseInt(request.getParameter("pointHireId"));
                    PointHire pointHire=new PointHire();
                    pointHire.setId(pointHireId);
                    Bicycle bicycle = new Bicycle();
                    bicycle.setPointHire(pointHire);
                    return setAttribute(model, bicycle);
                } else {
                    Bicycle bicycle = bicycleService.getById(id);
                    return setAttribute(model, bicycle);
                }
            } else {
                model.addAttribute("error", "page.error.show_bicycle_details");
                return "forward:/bicycles";
            }

        } catch (ServiceException e) {
            model.addAttribute("error", "page.error.show_bicycle_details");
            throw new CommandException("Failed to show bicycle details", e);

        }
    }

    private String setAttribute(Model model, Bicycle bicycle) {
        model.addAttribute("bicycle", bicycle);
        return "entity_list";
    }

}

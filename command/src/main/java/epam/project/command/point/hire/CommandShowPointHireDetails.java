package epam.project.command.point.hire;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.PointHire;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Component
@Scope("prototype")
public class CommandShowPointHireDetails implements Command {
    @Autowired
    private PointHireService pointHireService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {
            model.addAttribute("viewName", "point_hire_details");

            if (request.getParameter("pointHireId") != null) {
                int id = Integer.parseInt(request.getParameter("pointHireId"));

                if (id == 0) {
                    PointHire pointHire = new PointHire();

                    return setAttribute(model, pointHire);
                } else {
                    PointHire pointHire = pointHireService.getById(id);
                    return setAttribute(model, pointHire);
                }
            } else {
                model.addAttribute("error", "page.error.show_point_hire_details");
                return "entity_details";
            }
        } catch (ServiceException e) {
            model.addAttribute("error", "page.error.show_point_hire_details");
            throw new CommandException("Failed to show point hire details", e);
        }
    }

    private String setAttribute(Model model, PointHire pointHire) {

        model.addAttribute("pointHire", pointHire);
        return "entity_details";
    }
}

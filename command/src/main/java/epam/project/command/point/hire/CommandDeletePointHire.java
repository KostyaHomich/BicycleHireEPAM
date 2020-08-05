package epam.project.command.point.hire;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.EntityType;
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
public class CommandDeletePointHire implements Command {

    @Autowired
    private PointHireService pointHireService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {

            model.addAttribute("entity", EntityType.POINT_HIRE);
            if (request.getParameter("pointHireId") != null) {
                int id = Integer.parseInt(request.getParameter("pointHireId"));

                PointHire pointHire = pointHireService.getById(id);
                pointHireService.delete(pointHire);
                return "forward:/point_hires";
            } else {
                model.addAttribute("error", "point_hire.error.delete_point_hire");
                return "forward:/point_hires";
            }
        } catch (ServiceException e) {
            model.addAttribute("error", "point_hire.error.delete_point_hire");
            throw new CommandException("Failed to delete point hire", e);
        }
    }
}

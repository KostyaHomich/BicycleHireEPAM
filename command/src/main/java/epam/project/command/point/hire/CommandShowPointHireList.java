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
import java.util.List;

@Component
@Scope("prototype")
public class CommandShowPointHireList implements Command {


    @Autowired
    private PointHireService pointHireService;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {
            model.addAttribute("viewName", "point_hire_list");

            List<PointHire> pointHireList = pointHireService.takeAll();

            model.addAttribute("pointHireList", pointHireList);
            return "entity_list";

        } catch (ServiceException e) {
            model.addAttribute("error", "page.error.show_point_hire_list");
            throw new CommandException("Failed to show point hire list", e);
        }

    }
}

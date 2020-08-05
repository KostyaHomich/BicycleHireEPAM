package epam.project.command.point.hire;

import epam.project.builder.PointHireBuilder;
import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.PointHire;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import epam.project.util.RequestParameterParser;
import epam.project.validation.ValidationResult;
import epam.project.validation.impl.PointHireValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Scope("prototype")
public class CommandUpdatePointHire implements Command {

    @Autowired
    private PointHireService pointHireService;
    @Autowired
    private PointHireValidator pointHireValidator;
    @Autowired
    private PointHireBuilder pointHireBuilder;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = pointHireValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                PointHire pointHire = pointHireBuilder.build(parameters);
                pointHireService.update(pointHire);
                return "forward:/point_hires";

            } else {
                model.addAttribute("errorsList", validationResult);
                return "forward:/point_hire_details";
            }
        } catch (ServiceException e) {
            model.addAttribute("error", "point_hire.error.update_point_hire");
            throw new CommandException("Failed to update point hire", e);
        }


    }
}

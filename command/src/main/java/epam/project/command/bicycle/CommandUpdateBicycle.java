package epam.project.command.bicycle;

import epam.project.builder.BicycleBuilder;
import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.entity.Bicycle;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.RequestParameterParser;
import epam.project.validation.ValidationResult;
import epam.project.validation.impl.BicycleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Scope("prototype")
public class CommandUpdateBicycle implements Command {

    @Autowired
    private BicycleService bicycleService;
    @Autowired
    private BicycleValidator bicycleValidator;
    @Autowired
    private BicycleBuilder bicycleBuilder;

    @Override
    public String execute(HttpServletRequest request, Model model) throws CommandException {
        try {

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = bicycleValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                Bicycle bicycle = bicycleBuilder.build(parameters);
                bicycleService.update(bicycle);
                return "forward:/bicycles";

            } else {
                model.addAttribute("errorsList", validationResult);
                return "entity_details";
            }

        } catch (ServiceException e) {
            model.addAttribute("error", "bicycle.error.update_bicycle");
            throw new CommandException("Failed to update bicycle", e);
        }

    }


}

package epam.project.controller;

import epam.project.command.CommandException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerImpl {

    private static final String ERROR = "error";

    @ExceptionHandler(CommandException.class)
    public ModelAndView handleCustomException(CommandException exception) {
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(ERROR, "Sorry smth is breakdown "+exception.getMessage());
        return modelAndView;
    }
}

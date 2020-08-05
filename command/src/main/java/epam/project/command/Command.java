package epam.project.command;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request, Model model) throws CommandException;
}

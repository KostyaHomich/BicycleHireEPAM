package epam.project.controller;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.point.hire.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "/point_hire*")
@Log
public class PointHireController {
    @Autowired
    private ApplicationContext applicationContext;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/point_hire/delete")
    public String delete(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandDeletePointHire.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/point_hire/add")
    public String add(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandAddPointHire.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/point_hire_details")
    public String pointHireDetails(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandShowPointHireDetails.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/point_hires")
    public String pointHires(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandShowPointHireList.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/point_hire/update")
    public String updatePointHire(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandUpdatePointHire.class);
        return command.execute(request, model);
    }
}

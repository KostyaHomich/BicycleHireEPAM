package epam.project.controller;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.bicycle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "/bicycle*")
public class BicycleController {
    @Autowired
    private ApplicationContext applicationContext;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/bicycle/delete")
    public String delete(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandDeleteBicycle.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/bicycle_best/delete")
    public String deleteBestBicycle(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandDeleteBestBicycle.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/bicycle_best/add")
    public String addBestBicycle(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandAddBestBicycle.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/bicycle/add")
    public String add(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandAddBicycle.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/bicycle_details")
    public String bicycleDetails(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandShowBicycleDetails.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(value = "/bicycles_best")
    public String bestBicycles(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandShowBestBicycles.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/bicycles")
    public String bicycles(HttpServletRequest request, Model model) throws CommandException {
        model.addAttribute("viewName", "bicycle_list");
        Command command = applicationContext.getBean(CommandShowBicycleList.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/bicycle/update")
    public String updateBicycle(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandUpdateBicycle.class);
        return command.execute(request, model);
    }


}

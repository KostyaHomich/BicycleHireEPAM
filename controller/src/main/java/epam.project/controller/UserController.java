package epam.project.controller;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.CommandLogIn;
import epam.project.command.user.CommandShowUserDetails;
import epam.project.command.user.CommandShowUserList;
import epam.project.command.user.CommandSignUpUser;
import epam.project.command.user.CommandUpdateUser;
import epam.project.dto.UserDetailsImpl;
import epam.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller(value = "/user*")
public class UserController {

    private static final String VIEW_NAME = "viewName";

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/user/login")
    public String login(HttpServletRequest request, Model model, HttpSession session) throws CommandException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication.getPrincipal() instanceof UserDetailsImpl)) {
            model.addAttribute("error", "User with this login doesn't exists");
            return "login";
        }
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        session.setAttribute("signInUser", user);

        Command command = applicationContext.getBean(CommandLogIn.class);
        return command.execute(request, model);
    }


    @RequestMapping(value = "/user/sign_up")
    public String signUp(HttpServletRequest request, Model model) throws CommandException {
        Command command = applicationContext.getBean(CommandSignUpUser.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/user_details")
    public String userDetails(HttpServletRequest request, Model model) throws CommandException {
        model.addAttribute(VIEW_NAME, "user_details");
        Command command = applicationContext.getBean(CommandShowUserDetails.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/users")
    public String users(HttpServletRequest request, Model model) throws CommandException {
        model.addAttribute(VIEW_NAME, "user_list");
        Command command = applicationContext.getBean(CommandShowUserList.class);
        return command.execute(request, model);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/user/update")
    public String updateUser(HttpServletRequest request, Model model) throws CommandException {
        model.addAttribute(VIEW_NAME, "user_details");
        Command command = applicationContext.getBean(CommandUpdateUser.class);
        return command.execute(request, model);
    }

}

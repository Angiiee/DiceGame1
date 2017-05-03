package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.User;
import by.training.exception.CommandException;
import by.training.logic.profile.ShowProfileLogic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 19.03.2017.
 */
public class ShowProfileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/common/profilePersonalInfo.jsp";
        String login = (String) request.getSession().getAttribute("username");
        try {
            User user;
            user = ShowProfileLogic.getProfile(login);
            request.setAttribute("profileLogin", login);
            request.setAttribute("profileCard", user.getCardNumber());
            request.setAttribute("profileFirstName", user.getFirstName());
            request.setAttribute("profileLastName", user.getLastName());
            request.setAttribute("profileEmail", user.getEmail());
            request.setAttribute("profileBirthDate", user.getBirthdate());
            request.setAttribute("profileImg", user.getAvatar());
        } catch (CommandException e) {
           page = "/jsp/common/profileMain.jsp";
        }
        return page;
    }
}

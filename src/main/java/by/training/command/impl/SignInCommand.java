package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.User;
import by.training.exception.CommandException;
import by.training.logic.entry.RoleLogic;
import by.training.logic.entry.SignInLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by angelina on 23.02.2017.
 */
public class SignInCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    public static  final Logger LOGGER = Logger.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        try {
                User user = SignInLogic.checkLogin(login, pass);
                LOGGER.info("correct sign in params");
                page = "/jsp/common/main.jsp";
                HttpSession session = request.getSession(true);
                session.setAttribute("username", login);
                session.setAttribute("visitor", RoleLogic.checkRole(login));
                session.setAttribute("profileImg", user.getAvatar());
                session.setAttribute("userScore", user.getScore());
                session.setAttribute("userId", user.getUserId());
        } catch (CommandException e) {
            request.setAttribute("errorLoginPassMessage", e.getMessage());
            page = "/jsp/guest/registration.jsp";
        }
        return page;
    }
}

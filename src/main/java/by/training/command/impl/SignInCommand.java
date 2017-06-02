package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.User;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
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
    private static final String PARAM_NAME_USERNAME = "username";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_USER_SCORE = "userScore";
    private static final String PARAM_NAME_PROFILE_IMAGE = "profileImg";
    private static final String PARAM_NAME_ROLE = "visitor";
    private static final String SUCCESS_PAGE = "/jsp/common/main.jsp";
    private static final String FAIL_PAGE = "/jsp/guest/registration.jsp";
    public static final Logger LOGGER = Logger.getLogger(SignInCommand.class);

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setType(ResponseType.FORWARD);
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            User user = SignInLogic.checkLogin(login, pass);
            HttpSession session = request.getSession(true);
            session.setAttribute(PARAM_NAME_USERNAME, login);
            session.setAttribute(PARAM_NAME_ROLE, RoleLogic.checkRole(login));
            session.setAttribute(PARAM_NAME_PROFILE_IMAGE, user.getAvatar());
            session.setAttribute(PARAM_NAME_USER_SCORE, user.getScore());
            session.setAttribute(PARAM_NAME_USER_ID, user.getUserId());
            responseInfo.setNextPage(SUCCESS_PAGE);
        } catch (CommandException e) {
            request.setAttribute("errorLoginPassMessage", e.getMessage());
            responseInfo.setNextPage(FAIL_PAGE);
        }
        return responseInfo;
    }
}

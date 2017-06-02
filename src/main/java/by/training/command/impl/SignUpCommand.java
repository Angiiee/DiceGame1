package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.RoleType;
import by.training.entity.profile.User;
import by.training.entity.response.ResponseInfo;
import by.training.exception.CommandException;
import by.training.logic.entry.SignUpLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * Created by angelina on 06.03.2017.
 */
public class SignUpCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_FIRST_NAME = "firstname";
    private static final String PARAM_NAME_lAST_NAME = "lastname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String PARAM_NAME_USERNAME = "username";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_USER_SCORE = "userScore";
    private static final String PARAM_NAME_PROFILE_IMAGE = "profileImg";
    private static final String PARAM_NAME_ROLE = "visitor";
    private static final String SUCCESS_PAGE = "/jsp/common/main.jsp";
    private static final String FAIL_PAGE = "/jsp/guest/registration.jsp";
    public static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        User user = new User();
        user.setLogin(request.getParameter(PARAM_NAME_LOGIN));
        user.setRegistrationDate(LocalDate.now());
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        user.setFirstName(request.getParameter(PARAM_NAME_FIRST_NAME));
        user.setLastName(request.getParameter(PARAM_NAME_lAST_NAME));
        user.setBirthdate(LocalDate.parse(request.getParameter(PARAM_NAME_BIRTHDAY)));
        user.setEmail(request.getParameter(PARAM_NAME_EMAIL));
        try {
            int userId = SignUpLogic.addNewUser(user, pass);
            HttpSession session = request.getSession(true);
            session.setAttribute(PARAM_NAME_USERNAME, user.getLogin());
            session.setAttribute(PARAM_NAME_ROLE, RoleType.USER);
            session.setAttribute(PARAM_NAME_PROFILE_IMAGE, user.getAvatar());
            session.setAttribute(PARAM_NAME_USER_SCORE, user.getScore());
            session.setAttribute(PARAM_NAME_USER_ID, userId);
            responseInfo.setNextPage(SUCCESS_PAGE);
        } catch (CommandException e) {
            request.setAttribute("errorLoginPassMessage", e.getMessage());
            responseInfo.setNextPage(FAIL_PAGE);
        }
        return responseInfo;
    }
}

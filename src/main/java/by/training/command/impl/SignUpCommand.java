package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.RoleType;
import by.training.entity.profile.User;
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
    private static final String PARAM_NAME_SCORE = "score";
    private static final String PARAM_NAME_REGISTRATION_DATE = "registrationDate";
    private static final String PARAM_NAME_CARD_NUMBER = "cardNumber";
    private static final String PARAM_NAME_FIRST_NAME = "firstname";
    private static final String PARAM_NAME_lAST_NAME = "lastname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";

    public static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        User user = new User();
        user.setLogin(request.getParameter(PARAM_NAME_LOGIN));
        user.setRegistrationDate(LocalDate.now());
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        user.setFirstName(request.getParameter(PARAM_NAME_FIRST_NAME));
        user.setLastName(request.getParameter(PARAM_NAME_lAST_NAME));
        user.setBirthdate(LocalDate.parse(request.getParameter(PARAM_NAME_BIRTHDAY)));
        user.setEmail(request.getParameter(PARAM_NAME_EMAIL));
        try{
            int userId = SignUpLogic.addNewUser(user, pass);
            page = "/jsp/common/main.jsp";
            HttpSession session = request.getSession(true);
            session.setAttribute("username", user.getLogin());
            session.setAttribute("visitor", RoleType.USER);
            session.setAttribute("profileImg", user.getAvatar());
            session.setAttribute("userScore", user.getScore());
            session.setAttribute("userId", userId);
        }catch (CommandException e){
            request.setAttribute("errorLoginPassMessage", e.getMessage());
            page = "/jsp/guest/registration.jsp";
        }
        return page;
    }
}

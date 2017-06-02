package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.User;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.profile.ShowProfileLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 19.03.2017.
 */
public class ShowProfileCommand implements ActionCommand {
    private static final String PARAM_NAME_PROFILE_LOGIN = "profileLogin";
    private static final String PARAM_NAME_PROFILE_CARD = "profileCard";
    private static final String PARAM_NAME_PROFILE_FIRST_NAME = "profileFirstName";
    private static final String PARAM_NAME_PROFILE_LAST_NAME = "profileLastName";
    private static final String PARAM_NAME_PROFILE_EMAIL = "profileEmail";
    private static final String PARAM_NAME_PROFILE_BIRTHDATE = "profileBirthDate";
    private static final String PARAM_NAME_PROFILE_IMAGE = "profileImg";
    private static final String PARAM_NAME_LOGIN = "username";
    private static final String SUCCESS_PAGE = "/jsp/common/profilePersonalInfo.jsp";
    private static final String FAIL_PAGE = "/jsp/common/profileMain.jsp";
    public static final Logger LOGGER = Logger.getLogger(ShowProfileCommand.class);

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        responseInfo.setType(ResponseType.FORWARD);
        try {
            User user;
            user = ShowProfileLogic.getProfile(login);
            request.setAttribute(PARAM_NAME_PROFILE_LOGIN, login);
            request.setAttribute(PARAM_NAME_PROFILE_CARD, user.getCardNumber());
            request.setAttribute(PARAM_NAME_PROFILE_FIRST_NAME, user.getFirstName());
            request.setAttribute(PARAM_NAME_PROFILE_LAST_NAME, user.getLastName());
            request.setAttribute(PARAM_NAME_PROFILE_EMAIL, user.getEmail());
            request.setAttribute(PARAM_NAME_PROFILE_BIRTHDATE, user.getBirthdate());
            request.setAttribute(PARAM_NAME_PROFILE_IMAGE, user.getAvatar());
            responseInfo.setNextPage(SUCCESS_PAGE);
        } catch (CommandException e) {
            responseInfo.setNextPage(FAIL_PAGE);
            LOGGER.error("TODO" + e);
        }
        return responseInfo;
    }
}

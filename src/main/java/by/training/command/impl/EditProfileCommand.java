package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.User;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.profile.EditProfileLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by angelina on 19.03.2017.
 */
public class EditProfileCommand implements ActionCommand {
    private static final String PARAM_NAME_CARD_NUMBER = "crCard";
    private static final String PARAM_NAME_FIRST_NAME = "fnameEd";
    private static final String PARAM_NAME_lAST_NAME = "lnameEd";
    private static final String PARAM_NAME_LOGIN = "username";
    private static final String SUCCESS_PAGE = "/jsp/common/profileMain.jsp";
    private static final String FAIL_PAGE = "/jsp/common/profileMain.jsp";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        HttpSession session = request.getSession(true);
        User user = new User();
        user.setFirstName(request.getParameter(PARAM_NAME_FIRST_NAME));
        user.setLastName(request.getParameter(PARAM_NAME_lAST_NAME));
        user.setCardNumber(request.getParameter(PARAM_NAME_CARD_NUMBER));
        user.setLogin((String) session.getAttribute(PARAM_NAME_LOGIN));
        try{
            EditProfileLogic.editProfile(user);
            responseInfo.setNextPage(SUCCESS_PAGE);
            responseInfo.setType(ResponseType.REDIRECT);
        }catch (CommandException e){
            responseInfo.setNextPage(FAIL_PAGE);
            responseInfo.setType(ResponseType.FORWARD);
        }
        return responseInfo;
    }
}

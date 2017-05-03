package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.User;
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

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        User user = new User();
        user.setFirstName(request.getParameter(PARAM_NAME_FIRST_NAME));
        user.setLastName(request.getParameter(PARAM_NAME_lAST_NAME));
        user.setCardNumber(request.getParameter(PARAM_NAME_CARD_NUMBER));
        user.setLogin((String) session.getAttribute("username"));
        try{
            EditProfileLogic.editProfile(user);
            page = "/jsp/common/profileMain.jsp";
        }catch (CommandException e){
            request.setAttribute("errorEditMessage", e.getMessage());
            page = "/jsp/common/profileMain.jsp";
        }
        return page;
    }
}

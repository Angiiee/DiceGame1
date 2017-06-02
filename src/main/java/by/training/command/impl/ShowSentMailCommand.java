package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.mail.Message;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.mail.ShowSentMailLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by angelina on 12.04.2017.
 */
public class ShowSentMailCommand implements ActionCommand{
    private static final String PARAM_NAME_SENT_MAIL = "sentMailList";
    private static final String PARAM_NAME_LOGIN = "username";
    private static final String NEXT_PAGE = "/jsp/common/mail/sentMail.jsp";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo(NEXT_PAGE, ResponseType.FORWARD);
        String login = (String) request.getSession().getAttribute(PARAM_NAME_LOGIN);
        try {
            ArrayList<Message> messageList = ShowSentMailLogic.showSentMail(login);
            request.setAttribute(PARAM_NAME_SENT_MAIL, messageList);
        } catch (CommandException e) {
//            page = "/jsp/common/profileMain.jsp";
        }
        return responseInfo;
    }
}

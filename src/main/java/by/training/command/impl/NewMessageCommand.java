package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.mail.Message;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.mail.NewMessageLogic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 12.04.2017.
 */
public class NewMessageCommand implements ActionCommand {
    private static final String PARAM_NAME_RECIPIENT_LOGIN = "recipientLogin";
    private static final String PARAM_NAME_THEME = "theme";
    private static final String PARAM_NAME_LOGIN = "username";
    private static final String PARAM_NAME_TEXT_MESSAGE = "textMessage";
    private static final String NEXT_PAGE = "/jsp/common/mail/newMail.jsp";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        Message message = new Message();
        message.setRecipientLogin(request.getParameter(PARAM_NAME_RECIPIENT_LOGIN));
        message.setTheme(request.getParameter(PARAM_NAME_THEME));
        message.setText(request.getParameter(PARAM_NAME_TEXT_MESSAGE));
        message.setSenderLogin((String) request.getSession(true).getAttribute(PARAM_NAME_LOGIN));
        responseInfo.setNextPage(NEXT_PAGE);
        try {
            NewMessageLogic.sendMessage(message);
            responseInfo.setType(ResponseType.REDIRECT);
        } catch (CommandException e) {
            request.setAttribute("errorSentMessage", e.getMessage());
            responseInfo.setType(ResponseType.FORWARD);
        }
        return responseInfo;
    }
}

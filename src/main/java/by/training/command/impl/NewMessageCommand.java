package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.mail.Message;
import by.training.exception.CommandException;
import by.training.logic.mail.NewMessageLogic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 12.04.2017.
 */
public class NewMessageCommand implements ActionCommand{
    private static final String PARAM_NAME_RECIPIENT_LOGIN = "recipientLogin";
    private static final String PARAM_NAME_THEME = "theme";
    private static final String PARAM_NAME_TEXT_MESSAGE = "textMessage";

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/common/mail/newMail.jsp";
        Message message = new Message();
        message.setRecipientLogin(request.getParameter(PARAM_NAME_RECIPIENT_LOGIN));
        message.setTheme(request.getParameter(PARAM_NAME_THEME));
        message.setText(request.getParameter(PARAM_NAME_TEXT_MESSAGE));
        message.setSenderLogin((String) request.getSession(true).getAttribute("username"));
        try {
            NewMessageLogic.sendMessage(message);
        } catch (CommandException e) {
            request.setAttribute("errorSentMessage", e.getMessage());
        }
        return page;
    }
}

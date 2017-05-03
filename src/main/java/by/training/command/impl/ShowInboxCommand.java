package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.mail.Message;
import by.training.exception.CommandException;
import by.training.logic.mail.ShowInboxLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by angelina on 12.04.2017.
 */
public class ShowInboxCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/common/mail/inboxMail.jsp";
        String login = (String) request.getSession().getAttribute("username");
        try {
            ArrayList<Message> messageList = ShowInboxLogic.showInboxMail(login);
            request.setAttribute("inboxMessageList", messageList);
        } catch (CommandException e) {
//            page = "/jsp/common/profileMain.jsp";
        }
        return page;
    }
}

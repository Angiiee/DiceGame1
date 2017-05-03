package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.mail.Message;
import by.training.exception.CommandException;
import by.training.logic.mail.ShowSentMailLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by angelina on 12.04.2017.
 */
public class ShowSentMailCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/common/mail/sentMail.jsp";
        String login = (String) request.getSession().getAttribute("username");
        try {
            ArrayList<Message> messageList = ShowSentMailLogic.showSentMail(login);
            request.setAttribute("sentMailList", messageList);
        } catch (CommandException e) {
//            page = "/jsp/common/profileMain.jsp";
        }
        return page;
    }
}

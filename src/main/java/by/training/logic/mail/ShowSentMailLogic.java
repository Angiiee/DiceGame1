package by.training.logic.mail;

import by.training.dao.MessageDAO;
import by.training.entity.mail.Message;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by angelina on 12.04.2017.
 */
public class ShowSentMailLogic {
    public static final Logger LOGGER = Logger.getLogger(ShowSentMailLogic.class);

    public static ArrayList<Message> showSentMail (String login) throws CommandException {
        try {
            MessageDAO messageDAO = new MessageDAO();
            ArrayList<Message> messageList = messageDAO.findSentMail(login);
            if (messageList.isEmpty()){
                throw new CommandException("No message");
            }
            return messageList;
        } catch (DAOException e) {
            throw new CommandException(e.getMessage());
        }
    }
}

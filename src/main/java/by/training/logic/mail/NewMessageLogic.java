package by.training.logic.mail;

import by.training.dao.MessageDAO;
import by.training.entity.mail.Message;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 12.04.2017.
 */
public class NewMessageLogic {
    public static final Logger LOGGER = Logger.getLogger(NewMessageLogic.class);

    public static void sendMessage (Message message) throws CommandException {
        try {
            MessageDAO messageDAO = new MessageDAO();
            messageDAO.sendMessage(message);
        } catch (DAOException e) {
           throw new CommandException("Can not send mail.");
        }

    }
}




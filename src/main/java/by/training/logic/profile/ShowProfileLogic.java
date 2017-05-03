package by.training.logic.profile;

import by.training.dao.UserDAO;
import by.training.entity.profile.User;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 19.03.2017.
 */
public class ShowProfileLogic {

    public static final Logger LOGGER = Logger.getLogger(ShowProfileLogic.class);

    public static User getProfile (String login) throws CommandException {
        try {
            UserDAO userDAO = new UserDAO();
            return userDAO.findUserByLogin(login);
        } catch (DAOException e) {
           throw new CommandException(e.getMessage());
        }
    }
}

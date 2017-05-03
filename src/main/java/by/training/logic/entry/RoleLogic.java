package by.training.logic.entry;

import by.training.dao.UserDAO;
import by.training.entity.profile.RoleType;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 18.03.2017.
 */
public class RoleLogic {
    public static final Logger LOGGER = Logger.getLogger(RoleLogic.class);

    public static RoleType checkRole(String login) throws CommandException {
        try {
            UserDAO userDAO = new UserDAO();
            return userDAO.checkRole(login);
        } catch (DAOException e) {
            throw new CommandException("Can't check user role;");
        }
    }
}

package by.training.logic.entry;

import by.training.dao.UserDAO;
import by.training.entity.profile.User;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 28.02.2017.
 */
public class SignInLogic {
    public static final Logger LOGGER = Logger.getLogger(SignInLogic.class);

    public static User checkLogin(String login, String password) throws CommandException {
        UserDAO userDAO = new UserDAO();
        try {
            String pswd = userDAO.verifyPassword(login);
            if (DigestUtils.md5Hex(password).equals(pswd)) {
                return userDAO.findUserByLogin(login);
            }
        } catch (DAOException e) {
            throw new CommandException("Sign up, please.");
        }
        throw new CommandException("Check your password");
    }
}

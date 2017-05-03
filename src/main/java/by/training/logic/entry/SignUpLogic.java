package by.training.logic.entry;

import by.training.dao.UserDAO;
import by.training.entity.profile.User;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import by.training.validation.CreditCardValidation;
import by.training.validation.LoginValidation;
import by.training.validation.NameValidation;
import by.training.validation.PasswordValidation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 09.03.2017.
 */
public class SignUpLogic {
    public static final Logger LOGGER = Logger.getLogger(SignUpLogic.class);

    public static int addNewUser(User user, String password) throws CommandException {
        LoginValidation loginValidation = new LoginValidation();
        PasswordValidation passwordValidation = new PasswordValidation();
        NameValidation nameValidation = new NameValidation();
        CreditCardValidation creditCardValidation = new CreditCardValidation();
        try {
            if (loginValidation.validate(user.getLogin())) {
                if (passwordValidation.validate(password)) {
                    if (nameValidation.validate(user.getFirstName()) && nameValidation.validate(user.getLastName())) {
                        UserDAO userDAO = new UserDAO();
                        return userDAO.create(user, DigestUtils.md5Hex(password));
                    } else {
                        throw new CommandException("Incorrect first or last name.");
                    }
                } else {
                    throw new CommandException("Incorrect password.");
                }
            } else {
                throw new CommandException("Incorrect login.");
            }
        }catch (DAOException e){
            throw new CommandException("Login is already exist.");
        }
    }
}

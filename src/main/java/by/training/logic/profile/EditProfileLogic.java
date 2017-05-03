package by.training.logic.profile;

import by.training.dao.UserDAO;
import by.training.entity.profile.User;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import by.training.validation.CreditCardValidation;
import by.training.validation.NameValidation;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 19.03.2017.
 */
public class EditProfileLogic {
    public static final Logger LOGGER = Logger.getLogger(EditProfileLogic.class);

    public static void editProfile(User user) throws CommandException {
        NameValidation nameValidation = new NameValidation();
        CreditCardValidation creditCardValidation = new CreditCardValidation();
        try {
            if (nameValidation.validate(user.getFirstName()) && nameValidation.validate(user.getLastName())) {
                if (creditCardValidation.validate(user.getCardNumber())) {
                    UserDAO userDAO = new UserDAO();
                    userDAO.update(user);
                } else {
                    throw new CommandException("Can't save changes. Incorrect credit card number.");
                }
            } else {
                throw new CommandException("Can't save changes. Incorrect first or last name.");
            }
        } catch (DAOException e) {
            throw new CommandException("Can't save changes. Try later.");
        }
    }
}

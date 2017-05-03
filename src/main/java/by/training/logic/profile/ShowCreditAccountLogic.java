package by.training.logic.profile;

import by.training.dao.MoneyDAO;
import by.training.entity.profile.MoneyAccount;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 12.04.2017.
 */
public class ShowCreditAccountLogic {
    public static final Logger LOGGER = Logger.getLogger(ShowProfileLogic.class);

    public static MoneyAccount getExchangeRate () throws CommandException {
        try {
            MoneyDAO moneyDAO = new MoneyDAO();
            return moneyDAO.findMoneyAccount();
        } catch (DAOException e) {
            throw new CommandException(e.getMessage());
        }
    }
}

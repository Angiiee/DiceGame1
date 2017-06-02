package by.training.logic.service;

import by.training.dao.MoneyDAO;
import by.training.entity.profile.MoneyAccount;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

/**
 * Created by angelina on 02.06.2017.
 */
public class MoneySettingLogic {
    public static final Logger LOGGER = Logger.getLogger(MoneySettingLogic.class);

    public static void fixMoney (MoneyAccount moneyAccount) throws CommandException {
        try {
            MoneyDAO moneyDAO = new MoneyDAO();
            moneyDAO.changeMoneyAccount(moneyAccount);
        } catch (DAOException e) {
            throw new CommandException("Can not update money settings.");
        }
    }
}

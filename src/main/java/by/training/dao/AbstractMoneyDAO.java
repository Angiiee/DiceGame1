package by.training.dao;

import by.training.entity.profile.MoneyAccount;
import by.training.exception.DAOException;

/**
 * Created by angelina on 12.04.2017.
 */
public abstract class AbstractMoneyDAO {
    public abstract MoneyAccount findMoneyAccount() throws DAOException;

    public abstract void changeMoneyAccount(MoneyAccount moneyAccount) throws DAOException;
}

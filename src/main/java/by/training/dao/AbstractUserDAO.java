package by.training.dao;

import by.training.entity.profile.RoleType;
import by.training.entity.profile.User;
import by.training.exception.DAOException;

import java.math.BigDecimal;

/**
 * Created by angelina on 15.03.2017.
 */
public abstract class AbstractUserDAO {

    public abstract User findUserByLogin(String name) throws DAOException;

    public abstract int create(User user, String password) throws DAOException;

    public abstract String verifyPassword(String login) throws DAOException;

    public abstract RoleType checkRole(String login) throws DAOException;

    public abstract void saveAvatar(String fileName, String login) throws DAOException;

    public abstract void changeScore(String login, BigDecimal score) throws DAOException;

    public abstract User verifyCreditCard(String login) throws DAOException;

//    public abstract BigDecimal checkBalance(String login) throws DAOException;
}

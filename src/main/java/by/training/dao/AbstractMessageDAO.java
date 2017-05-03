package by.training.dao;

import by.training.entity.mail.Message;
import by.training.exception.DAOException;

import java.util.ArrayList;

/**
 * Created by angelina on 26.03.2017.
 */
public abstract class AbstractMessageDAO {

    public abstract void sendMessage(Message message) throws DAOException;

    public abstract ArrayList<Message> findInbox(String login) throws DAOException;

    public abstract ArrayList<Message> findSentMail(String login) throws DAOException;
}

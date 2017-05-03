package by.training.dao;

import by.training.connection.ConnectionPool;
import by.training.connection.ProxyConnection;
import by.training.entity.mail.Message;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelina on 12.04.2017.
 */
public class MessageDAO extends AbstractMessageDAO implements EntityDAO<Message>{
    private static final String PARAM_NAME_RECIPIENT_LOGIN = "recipientLogin";
    private static final String PARAM_NAME_THEME = "theme";
    private static final String PARAM_NAME_TEXT_MESSAGE = "textMessage";
    private static final String PARAM_NAME_SENDER_LOGIN = "senderLogin";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_USER_LOGIN = "login";
    private static final String PARAM_NAME_MESSAGE = "message";
    public static final Logger LOGGER = Logger.getLogger(MessageDAO.class);

    private static final String SQL_SELECT_MESSAGE_BY_RECIPIENT_ID =
            "SELECT user.login, message.theme, message.message FROM message LEFT JOIN user ON message.senderId = user.userId WHERE  message.recipientId = ?";
    private static final String SQL_SELECT_USER_ID_BY_LOGIN =
            "SELECT userId FROM user WHERE user.login = ?";
    private static final String SQL_SELECT_MESSAGE_BY_SENDER_ID =
            "SELECT user.login, message.theme, message.message FROM message LEFT JOIN user ON message.recipientId = user.userId WHERE message.senderId = ?";
    private static final String SQL_INSERT_NEW_MESSAGE =
            "INSERT INTO message (messageId, senderId, recipientId, theme, message)" +
                    "VALUES(NULL, (SELECT userId FROM user WHERE login = ?), (SELECT userId FROM user WHERE login = ?), ?, ?)";

    @Override
    public void sendMessage(Message message) throws DAOException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_MESSAGE)) {
            connection.setAutoCommit(false);
            statement.setString(1, message.getSenderLogin());
            statement.setString(2, message.getRecipientLogin());
            statement.setString(3, message.getTheme());
            statement.setString(4, message.getText());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DAOException("SQL exception - can't rollback: " + e1);
            }
            throw new DAOException("SQL exception - request or table failed: " + e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public ArrayList<Message> findInbox(String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getConnection();
             PreparedStatement statementUser = connection.prepareStatement(SQL_SELECT_USER_ID_BY_LOGIN);
             PreparedStatement statementMessage = connection.prepareStatement(SQL_SELECT_MESSAGE_BY_RECIPIENT_ID)) {
            ArrayList<Message> messageList = new ArrayList<>();
            statementUser.setString(1, login);
            ResultSet resultSet = statementUser.executeQuery();
            if(resultSet.next()){
                int userId = resultSet.getInt(PARAM_NAME_USER_ID);
                statementMessage.setInt(1, userId);
                ResultSet resSet = statementMessage.executeQuery();
                while (resSet.next()){
                    Message message = new Message();
                    message.setSenderLogin(resSet.getString(PARAM_NAME_USER_LOGIN));
                    message.setTheme(resSet.getString(PARAM_NAME_THEME));
                    message.setText(resSet.getString(PARAM_NAME_MESSAGE));
                    messageList.add(message);
                }
                return messageList;
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't find user by login");
    }

    @Override
    public ArrayList<Message> findSentMail(String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getConnection();
             PreparedStatement statementUser = connection.prepareStatement(SQL_SELECT_USER_ID_BY_LOGIN);
             PreparedStatement statementMessage = connection.prepareStatement(SQL_SELECT_MESSAGE_BY_SENDER_ID)) {
            ArrayList<Message> sentMessageList = new ArrayList<>();
            statementUser.setString(1, login);
            ResultSet resultSet = statementUser.executeQuery();
            if(resultSet.next()){
                int userId = resultSet.getInt(PARAM_NAME_USER_ID);
                statementMessage.setInt(1, userId);
                ResultSet resSet = statementMessage.executeQuery();
                while (resSet.next()){
                    Message message = new Message();
                    message.setRecipientLogin(resSet.getString(PARAM_NAME_USER_LOGIN));
                    message.setTheme(resSet.getString(PARAM_NAME_THEME));
                    message.setText(resSet.getString(PARAM_NAME_MESSAGE));
                    sentMessageList.add(message);
                }
                return sentMessageList;
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't find user by login");
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public Message findEntityById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Message entity) throws DAOException {

    }
}

package by.training.dao;

import by.training.connection.ConnectionPool;
import by.training.connection.ProxyConnection;
import by.training.entity.profile.RoleType;
import by.training.entity.profile.User;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

/**
 * Created by angelina on 28.02.2017.
 */
public class UserDAO extends AbstractUserDAO implements EntityDAO<User> {
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ROLE = "role";
    private static final String PARAM_NAME_SCORE = "score";
    private static final String PARAM_NAME_BAN = "ban";
    private static final String PARAM_NAME_AVATAR_URL = "avatarURL";
    private static final String PARAM_NAME_REGISTRATION_DATE = "registrationDate";
    private static final String PARAM_NAME_CARD_NUMBER = "cardNumber";
    private static final String PARAM_NAME_FIRST_NAME = "firstname";
    private static final String PARAM_NAME_lAST_NAME = "lastname";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    public static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT userId, login, password, score, role, ban, avatarURL, cardNumber FROM user WHERE login=?";
    private static final String SQL_SELECT_USER_INFO_BY_ID =
            "SELECT userId, firstname, lastname, email, birthday FROM user_info WHERE userId=?";
    private static final String SQL_INSERT_NEW_USER =
            "INSERT INTO user (userId, login, password, score, role, ban, avatarURL, registrationDate, cardNumber)" +
                    " VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_NEW_USER_INFO =
            "INSERT INTO user_info (userId, firstname, lastname, email, birthday)" +
                    " VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ROLE_BY_LOGIN =
            "SELECT role FROM user WHERE login = ?";
    private static final String SQL_UPDATE_USER_BY_LOGIN =
            "UPDATE user SET cardNumber = ? WHERE login = ?";
    private static final String SQL_UPDATE_USER_INFO_BY_ID =
            "UPDATE user_info SET firstname = ?, lastname = ? WHERE userId = ?";
    private static final String SQL_UPDATE_AVATAR_BY_LOGIN =
            "UPDATE user SET avatarURL = ? WHERE login = ?";
    private static final String SQL_UPDATE_SCORE_BY_LOGIN =
            "UPDATE user SET score = ? WHERE login = ?";

    @Override
    public User findUserByLogin(String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getConnection();
             PreparedStatement statementUser = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
             PreparedStatement statementUserInfo = connection.prepareStatement(SQL_SELECT_USER_INFO_BY_ID)) {
            User user = new User();
            statementUser.setString(1, login);
            ResultSet resultSet = statementUser.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt(PARAM_NAME_USER_ID);
            user.setUserId(userId);
            user.setLogin(resultSet.getString(PARAM_NAME_LOGIN));
            user.setCardNumber(resultSet.getString(PARAM_NAME_CARD_NUMBER));
            user.setAvatar(resultSet.getString(PARAM_NAME_AVATAR_URL));
            user.setScore(resultSet.getBigDecimal(PARAM_NAME_SCORE));
            user.setBan(resultSet.getBoolean(PARAM_NAME_BAN));
            user.setRoleType(RoleType.valueOf(resultSet.getString(PARAM_NAME_ROLE)));
            statementUserInfo.setInt(1, userId);
            ResultSet resSet = statementUserInfo.executeQuery();
            resSet.next();
            user.setFirstName(resSet.getString(PARAM_NAME_FIRST_NAME));
            user.setLastName(resSet.getString(PARAM_NAME_lAST_NAME));
            user.setBirthdate(resSet.getDate(PARAM_NAME_BIRTHDAY).toLocalDate());
            user.setEmail(resSet.getString(PARAM_NAME_EMAIL));
            return user;
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't find user by login");
    }

    @Override
    public int create(User user, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement statementUser = connection.prepareStatement(SQL_INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementUserInfo = connection.prepareStatement(SQL_INSERT_NEW_USER_INFO)) {
            connection.setAutoCommit(false);
            statementUser.setString(1, user.getLogin());
            statementUser.setString(2, password);
            statementUser.setBigDecimal(3, user.getScore());
            statementUser.setString(4, user.getRoleType().name());
            statementUser.setBoolean(5, user.isBan());
            statementUser.setString(6, user.getAvatar());
            statementUser.setDate(7, Date.valueOf(user.getRegistrationDate()));
            statementUser.setString(8, user.getCardNumber());
            statementUser.executeUpdate();
            ResultSet resultSet = statementUser.getGeneratedKeys();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                statementUserInfo.setInt(1, userId);
                statementUserInfo.setString(2, user.getFirstName());
                statementUserInfo.setString(3, user.getLastName());
                statementUserInfo.setString(4, user.getEmail());
                statementUserInfo.setDate(5, Date.valueOf(user.getBirthdate()));
                statementUserInfo.executeUpdate();
                connection.commit();
                return userId;
            }
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
        throw new DAOException("can't execute transaction.");
    }

    @Override
    public String verifyPassword(String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getConnection();
             PreparedStatement statementUser = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            statementUser.setString(1, login);
            ResultSet resultSet = statementUser.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(PARAM_NAME_PASSWORD);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't verify password: no user registered");
    }

    @Override
    public RoleType checkRole(String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getConnection();
             PreparedStatement statementUser = connection.prepareStatement(SQL_SELECT_ROLE_BY_LOGIN)) {
            statementUser.setString(1, login);
            ResultSet resultSet = statementUser.executeQuery();
            if (resultSet.next()) {
                return RoleType.valueOf(resultSet.getString(PARAM_NAME_ROLE));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't find user role by login");
    }

    @Override
    public void saveAvatar(String fileName, String login) throws DAOException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement statementUser = connection.prepareStatement(SQL_UPDATE_AVATAR_BY_LOGIN)) {
            connection.setAutoCommit(false);
            statementUser.setString(1, fileName);
            statementUser.setString(2, login);
            statementUser.executeUpdate();
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
    public void changeScore(String login, BigDecimal score) throws DAOException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SCORE_BY_LOGIN)) {
            connection.setAutoCommit(false);
            statement.setBigDecimal(1, score);
            statement.setString(2, login);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("SQL exception - can't rollback: " + ex);
            }
            throw new DAOException("SQL exception - request or table failed: " + e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public User verifyCreditCard(String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resSet = statement.executeQuery();
            if (resSet.next()) {
                User user = new User();
                user.setCardNumber(resSet.getString(PARAM_NAME_CARD_NUMBER));
                user.setScore(resSet.getBigDecimal(PARAM_NAME_SCORE));
                return user;
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't verify card number: no user registered");
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findEntityById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void update(User entity) throws DAOException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement statementUser = connection.prepareStatement(SQL_UPDATE_USER_BY_LOGIN, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementUserInfo = connection.prepareStatement(SQL_UPDATE_USER_INFO_BY_ID)) {
            connection.setAutoCommit(false);
            statementUser.setString(1, entity.getCardNumber());
            statementUser.setString(2, entity.getLogin());
            statementUser.executeUpdate();
            ResultSet resultSet = statementUser.getGeneratedKeys();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                statementUserInfo.setString(1, entity.getFirstName());
                statementUserInfo.setString(2, entity.getLastName());
                statementUserInfo.setInt(3, userId);
                statementUserInfo.executeUpdate();
                connection.commit();
            }else {
                throw new DAOException("can't update all info");
            }
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
}

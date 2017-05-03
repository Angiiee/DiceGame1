package by.training.dao;

import by.training.connection.ConnectionPool;
import by.training.connection.ProxyConnection;
import by.training.entity.profile.MoneyAccount;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by angelina on 12.04.2017.
 */
public class MoneyDAO extends AbstractMoneyDAO implements EntityDAO<MoneyAccount> {
    private static final String PARAM_NAME_MONEY_ID = "moneyID";
    private static final String PARAM_NAME_DATE = "date";
    private static final String PARAM_NAME_USD = "USD";
    private static final String PARAM_NAME_EUR = "EUR";
    private static final String PARAM_NAME_BYN = "BYN";
    private static final String PARAM_NAME_STANDART_RATE = "standartRate";
    public static final Logger LOGGER = Logger.getLogger(MoneyDAO.class);

    private static final String SQL_SELECT_MONEY_BY_ID =
            "SELECT date, USD, EUR, BYN, standartRate FROM money WHERE moneyId=(SELECT MAX(moneyId) FROM money)";
    private static final String SQL_INSERT_NEW_MONEY =
            "INSERT INTO money (moneyId, date, USD, EUR, BYN, standartRate)" +
                    " VALUES(NULL, CURRENT_DATE, ?, ?, ?, ?)";

    @Override
    public MoneyAccount findMoneyAccount() throws DAOException {
        try (ProxyConnection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MONEY_BY_ID)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                MoneyAccount moneyAccount = new MoneyAccount();
                moneyAccount.setDollar(new BigDecimal(resultSet.getString(PARAM_NAME_USD)));
                moneyAccount.setEuro(new BigDecimal(resultSet.getString(PARAM_NAME_EUR)));
                moneyAccount.setByn(new BigDecimal(resultSet.getString(PARAM_NAME_BYN)));
                moneyAccount.setStandartRate(new BigDecimal(resultSet.getString(PARAM_NAME_STANDART_RATE)));
                return moneyAccount;
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't get money info");
    }

    @Override
    public void changeMoneyAccount(MoneyAccount moneyAccount) throws DAOException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_MONEY)) {
            connection.setAutoCommit(false);
            statement.setBigDecimal(1, moneyAccount.getDollar());
            statement.setBigDecimal(2, moneyAccount.getEuro());
            statement.setBigDecimal(3, moneyAccount.getByn());
            statement.setBigDecimal(4, moneyAccount.getStandartRate());
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
    public List<MoneyAccount> findAll() {
        return null;
    }

    @Override
    public MoneyAccount findEntityById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(MoneyAccount entity) throws DAOException {

    }
}

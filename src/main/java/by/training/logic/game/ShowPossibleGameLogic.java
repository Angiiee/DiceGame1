package by.training.logic.game;

import by.training.connection.ConnectionPool;
import by.training.connection.ProxyConnection;
import by.training.dao.GameDAO;
import by.training.entity.game.GameAccount;
import by.training.exception.CommandException;
import by.training.exception.DAOException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by angelina on 25.04.2017.
 */
public class ShowPossibleGameLogic {
    public static ArrayList<GameAccount> show(int userId) throws CommandException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            GameDAO gameDAO = new GameDAO(connection);
            ArrayList<GameAccount> list = gameDAO.incompleteGameList(userId);
            connection.commit();
            return list;
        } catch (DAOException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //log
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
        throw new CommandException("");
    }
}

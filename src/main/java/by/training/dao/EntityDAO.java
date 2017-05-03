package by.training.dao;

import by.training.connection.ProxyConnection;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by angelina on 14.03.2017.
 */
public interface EntityDAO<T> {
    public static final Logger LOGGER = Logger.getLogger(EntityDAO.class);

    List<T> findAll();

    T findEntityById(int id);

    void delete(int id);

    void update(T entity) throws DAOException;

    default void closeConnection(ProxyConnection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("SQL exception - can't close connection: " + e);
        }
    }
}

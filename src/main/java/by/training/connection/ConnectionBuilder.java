package by.training.connection;

import by.training.exception.DBException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by angelina on 14.03.2017.
 */
class ConnectionBuilder {
    private static String DATABASE_BUNDLE = "database";

    static ProxyConnection getConnection() throws DBException {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DATABASE_BUNDLE);
            String url = resource.getString("db.url");
            String user = resource.getString("db.user");
            String pass = resource.getString("db.password");
            return new ProxyConnection(DriverManager.getConnection(url, user, pass));
        } catch (SQLException e) {
            throw new DBException(" can't create connection." + e);
        }catch (MissingResourceException e){
            throw new DBException(" can't find required properties." + e);
        }
    }
}

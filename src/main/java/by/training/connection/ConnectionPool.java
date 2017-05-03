package by.training.connection;

import by.training.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by angelina on 01.03.2017.
 */
public class ConnectionPool {
    private static final int POOL_SIZE = 10;
    private static BlockingQueue<ProxyConnection> instance;
    private static AtomicBoolean flag = new AtomicBoolean();
    private static Lock locker = new ReentrantLock();
    public static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        instance = new LinkedBlockingQueue<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                instance.add(ConnectionBuilder.getConnection());
            } catch (DBException e) {
                LOGGER.error("Can't create connection: " + e.getMessage());
            }
        }
    }

    private static void getInstance() {
        if (!flag.get()) {
            locker.lock();
            if (instance == null) {
                try {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    new ConnectionPool();
                    flag.set(true);
                } catch (SQLException e) {
                    LOGGER.fatal("Can't create connection pool: can't find database driver" + e.getMessage());
                    throw new RuntimeException();
                }
            }
            locker.unlock();
        }
    }

    public static ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = instance.take();
        } catch (InterruptedException e) {
            LOGGER.error("Can't take connection from the pool.");
        }
        return proxyConnection;
    }

    public static void close(ProxyConnection proxyConnection) {
        instance.add(proxyConnection);
    }

    public static void openPool() {
        ConnectionPool.getInstance();
    }

    public static void closePool() {
        for (int i = 0; i < instance.size(); i++) {
            try {
                instance.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                LOGGER.error("Can't close connection pool.");
            }
        }
    }

}
package by.training.exception;

/**
 * Created by angelina on 14.03.2017.
 */
public class DAOException extends Exception{
    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}

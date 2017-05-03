package by.training.exception;

/**
 * Created by angelina on 14.03.2017.
 */
public class DBException extends Exception{
    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }
}

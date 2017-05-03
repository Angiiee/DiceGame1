package by.training.exception;

/**
 * Created by angelina on 30.03.2017.
 */
public class CommandException extends Exception{
    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}

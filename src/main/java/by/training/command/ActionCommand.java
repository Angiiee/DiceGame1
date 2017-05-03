package by.training.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 23.02.2017.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request);
}

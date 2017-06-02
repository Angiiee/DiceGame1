package by.training.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 23.02.2017.
 */
public class ActionFactory {

    public static final Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private static final String PARAM_COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request) {
        String action;
        action = request.getParameter(PARAM_COMMAND);
        LOGGER.info(PARAM_COMMAND + action);
        CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
        return currentEnum.getCurrentCommand();
    }
}

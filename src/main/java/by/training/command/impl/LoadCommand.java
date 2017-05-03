package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.exception.CommandException;
import by.training.logic.profile.LoadLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 24.02.2017.
 */
public class LoadCommand implements ActionCommand {

    public static final Logger LOGGER = Logger.getLogger(LoadCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/common/profileMain.jsp";
        try {
            String fileName = LoadLogic.saveAvatar(request);
            request.getSession(true).setAttribute("profileImg", fileName);
        } catch (CommandException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("errorSaveAvatarMessage", "Can't save new avatar");
        }
        return page;
    }
}
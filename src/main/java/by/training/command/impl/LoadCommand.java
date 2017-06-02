package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.profile.LoadLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 24.02.2017.
 */
public class LoadCommand implements ActionCommand {
    public static final Logger LOGGER = Logger.getLogger(LoadCommand.class);
    private static final String NEXT_PAGE = "/jsp/common/profileMain.jsp";
    private static final String PARAM_NAME_PROFILE_IMAGE = "profileImg";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setNextPage(NEXT_PAGE);
        try {
            String fileName = LoadLogic.saveAvatar(request);
            request.getSession(true).setAttribute(PARAM_NAME_PROFILE_IMAGE, fileName);
            responseInfo.setType(ResponseType.REDIRECT);
        } catch (CommandException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("errorSaveAvatarMessage", "Can't save new avatar");
            responseInfo.setType(ResponseType.FORWARD);
        }
        return responseInfo;
    }
}
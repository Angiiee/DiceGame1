package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 23.02.2017.
 */
public class LogoutCommand implements ActionCommand {
    private static final String NEXT_PAGE = "/index.jsp";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo(NEXT_PAGE, ResponseType.REDIRECT);
        request.getSession().invalidate();
        return responseInfo;
    }
}

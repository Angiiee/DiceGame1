package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 24.03.2017.
 */
public class LocaleCommand implements ActionCommand{
    private static final String LOCALE = "locale";
    private static final String NEXT_PAGE = "/index.jsp";
    private static final String PARAM_NAME_USER_LOCALE = "userLocale";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
      ResponseInfo responseInfo = new ResponseInfo(NEXT_PAGE, ResponseType.REDIRECT);
       String locale = request.getParameter(LOCALE);
       request.getSession().setAttribute(PARAM_NAME_USER_LOCALE, locale);
       return responseInfo;
    }
}

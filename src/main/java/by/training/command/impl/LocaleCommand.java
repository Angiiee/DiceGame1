package by.training.command.impl;

import by.training.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 24.03.2017.
 */
public class LocaleCommand implements ActionCommand{
    public static final String LOCALE = "locale";

    @Override
    public String execute(HttpServletRequest request) {
       String page = "/index.jsp";
       String locale = request.getParameter(LOCALE);
       request.getSession().setAttribute("userLocale", locale);
       return page;
    }
}

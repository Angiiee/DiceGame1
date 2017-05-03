package by.training.command.impl;

import by.training.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 23.02.2017.
 */
public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
//        request.getSession().setAttribute("visitor", null);
        request.getSession().invalidate();
        return "/index.jsp";
    }
}

package by.training.command;

import by.training.entity.response.ResponseInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 23.02.2017.
 */
public interface ActionCommand {
    ResponseInfo execute(HttpServletRequest request);
}

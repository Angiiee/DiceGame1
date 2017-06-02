package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.profile.ReplenishAccountLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Created by angelina on 11.04.2017.
 */
public class ReplenishAccountCommand implements ActionCommand {
    private static final String PARAM_NAME_SELECT_CURRENCY = "selectCurrency";
    private static final String PARAM_NAME_ADD_AMOUNT = "addAmount";
    private static final String PARAM_NAME_CARD_NUMBER = "cardNumber";
    private static final String PARAM_NAME_USER_SCORE = "userScore";
    private static final String PARAM_NAME_LOGIN = "username";
    private static final String NEXT_PAGE = "/jsp/common/profileMain.jsp";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        HttpSession session = request.getSession(true);
        String currency = request.getParameter(PARAM_NAME_SELECT_CURRENCY);
        String cardNumber = request.getParameter(PARAM_NAME_CARD_NUMBER);
        BigDecimal amount = new BigDecimal(request.getParameter(PARAM_NAME_ADD_AMOUNT));
        String login = (String) session.getAttribute(PARAM_NAME_LOGIN);
        responseInfo.setNextPage(NEXT_PAGE);
        try {
            BigDecimal score = ReplenishAccountLogic.replelishAccount(currency, cardNumber, amount, login);
            request.getSession(true).setAttribute(PARAM_NAME_USER_SCORE, score);
            responseInfo.setType(ResponseType.REDIRECT);
        } catch (CommandException e) {
            request.setAttribute("errorEditMessage", e.getMessage());
            responseInfo.setType(ResponseType.FORWARD);
        }
        return responseInfo;
    }
}

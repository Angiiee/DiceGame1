package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.exception.CommandException;
import by.training.logic.profile.ReplenishAccountLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Created by angelina on 11.04.2017.
 */
public class ReplenishAccountCommand implements ActionCommand {
    private static final String PARAM_NAME_USD = "usd";
    private static final String PARAM_NAME_EUR = "eur";
    private static final String PARAM_NAME_BYN = "byn";
    private static final String PARAM_NAME_SELECT_CURRENCY = "selectCurrency";
    private static final String PARAM_NAME_ADD_AMOUNT = "addAmount";
    private static final String PARAM_NAME_CARD_NUMBER = "cardNumber";


    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/common/profileMain.jsp";
        HttpSession session = request.getSession(true);
        String currency = request.getParameter(PARAM_NAME_SELECT_CURRENCY);
        String cardNumber = request.getParameter(PARAM_NAME_CARD_NUMBER);
        BigDecimal amount = new BigDecimal(request.getParameter(PARAM_NAME_ADD_AMOUNT));
        String login = (String) request.getSession().getAttribute("username");
        try{
            BigDecimal score = ReplenishAccountLogic.replelishAccount(currency, cardNumber, amount, login);
            request.getSession(true).setAttribute("userScore", score);
        }catch (CommandException e){
            request.setAttribute("errorEditMessage", e.getMessage());
        }
        return page;
    }
}

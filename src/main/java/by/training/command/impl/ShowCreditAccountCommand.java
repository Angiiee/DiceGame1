package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.MoneyAccount;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.profile.ShowCreditAccountLogic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 12.04.2017.
 */
public class ShowCreditAccountCommand implements ActionCommand {
    private static final String PARAM_NAME_USD = "creditCardAccountUSD";
    private static final String PARAM_NAME_EUR = "creditCardAccountEUR";
    private static final String PARAM_NAME_BYN = "creditCardAccountBYN";
    private static final String PARAM_NAME_STANDART_RATE = "creditCardAccountStandartRate";
    private static final String SUCCESS_PAGE = "/jsp/common/profileCreditCard.jsp";
    private static final String FAIL_PAGE = "/jsp/common/profileMain.jsp";

    @Override
    public ResponseInfo execute(HttpServletRequest request) {
       ResponseInfo responseInfo = new ResponseInfo();
       responseInfo.setType(ResponseType.FORWARD);
        try {
            MoneyAccount moneyAccount = ShowCreditAccountLogic.getExchangeRate();
            request.setAttribute(PARAM_NAME_USD, moneyAccount.getDollar());
            request.setAttribute(PARAM_NAME_EUR, moneyAccount.getEuro());
            request.setAttribute(PARAM_NAME_BYN, moneyAccount.getByn());
            request.setAttribute(PARAM_NAME_STANDART_RATE, moneyAccount.getStandartRate());
            responseInfo.setNextPage(SUCCESS_PAGE);
        } catch (CommandException e) {
            responseInfo.setNextPage(FAIL_PAGE);
        }
        return responseInfo;
    }
}

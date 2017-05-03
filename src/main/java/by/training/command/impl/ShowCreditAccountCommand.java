package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.MoneyAccount;
import by.training.exception.CommandException;
import by.training.logic.profile.ShowCreditAccountLogic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by angelina on 12.04.2017.
 */
public class ShowCreditAccountCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/common/profileCreditCard.jsp";
        try {
            MoneyAccount moneyAccount = ShowCreditAccountLogic.getExchangeRate();
            request.setAttribute("creditCardAccountUSD", moneyAccount.getDollar());
            request.setAttribute("creditCardAccountEUR", moneyAccount.getEuro());
            request.setAttribute("creditCardAccountBYN", moneyAccount.getByn());
            request.setAttribute("creditCardAccountStandartRate", moneyAccount.getStandartRate());
        } catch (CommandException e) {
            page = "/jsp/common/profileMain.jsp";
        }
        return page;
    }
}

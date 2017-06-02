package by.training.command.impl;

import by.training.command.ActionCommand;
import by.training.entity.profile.MoneyAccount;
import by.training.entity.response.ResponseInfo;
import by.training.entity.response.ResponseType;
import by.training.exception.CommandException;
import by.training.logic.service.MoneySettingLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


public class MoneySettingCommand implements ActionCommand {
    private static final String PARAM_NAME_USD = "usd";
    private static final String PARAM_NAME_EUR = "eur";
    private static final String PARAM_NAME_BYN = "byn";
    private static final String PARAM_NAME_STANDART_RATE = "standartRate";
    private static final String SUCCESS_PAGE = "/jsp/common/main.jsp";
    private static final String FAIL_PAGE = "/jsp/admin/creditCardSetting.jsp";
    public static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);


    @Override
    public ResponseInfo execute(HttpServletRequest request) {
        ResponseInfo responseInfo = new ResponseInfo();
        MoneyAccount moneyAccount = new MoneyAccount();
        moneyAccount.setDollar(new BigDecimal(request.getParameter(PARAM_NAME_USD)));
        moneyAccount.setEuro(new BigDecimal(request.getParameter(PARAM_NAME_EUR)));
        moneyAccount.setByn(new BigDecimal(request.getParameter(PARAM_NAME_BYN)));
        moneyAccount.setStandartRate(new BigDecimal(request.getParameter(PARAM_NAME_STANDART_RATE)));
        try {
            MoneySettingLogic.fixMoney(moneyAccount);
            responseInfo.setType(ResponseType.REDIRECT);
            responseInfo.setNextPage(SUCCESS_PAGE);
        } catch (CommandException e) {
            request.setAttribute("errorEditMoney", e.getMessage());
            responseInfo.setType(ResponseType.FORWARD);
            responseInfo.setNextPage(FAIL_PAGE);
        }
        return responseInfo;
    }
}

package by.training.logic.profile;

import by.training.dao.MoneyDAO;
import by.training.dao.UserDAO;
import by.training.entity.profile.MoneyAccount;
import by.training.entity.profile.User;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Created by angelina on 12.04.2017.
 */
public class ReplenishAccountLogic {
    public static final Logger LOGGER = Logger.getLogger(ReplenishAccountLogic.class);

    public static BigDecimal replelishAccount (String currency, String cardNumber, BigDecimal amount, String login) throws CommandException {
        UserDAO userDAO = new UserDAO();
        try {
            User user = userDAO.verifyCreditCard(login);
            if (cardNumber.equals(user.getCardNumber())) {
                MoneyDAO moneyDAO = new MoneyDAO();
                MoneyAccount moneyAccount = moneyDAO.findMoneyAccount();
                BigDecimal score = user.getScore();
                switch (currency){
                    case "USD":
                        score = score.add(amount.multiply(moneyAccount.getDollar()));
                        break;
                    case "EUR":
                        score = score.add(amount.multiply(moneyAccount.getEuro()));
                        break;
                    case "BYN":
                        score = score.add(amount.multiply(moneyAccount.getByn()));
                        break;
                    default:
                        throw new CommandException("Unknown currency type.");
                }
                userDAO.changeScore(login, score);
                return score;
            }
        } catch (DAOException e) {
            throw new CommandException("Add credit card.");
        }
        throw new CommandException("Can't add money.");
    }
}

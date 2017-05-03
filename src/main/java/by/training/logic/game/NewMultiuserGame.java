package by.training.logic.game;

import by.training.action.CountMaxScore;
import by.training.action.GameMechanics;
import by.training.connection.ConnectionPool;
import by.training.connection.ProxyConnection;
import by.training.dao.GameDAO;
import by.training.entity.game.Game;
import by.training.entity.game.GameAccount;
import by.training.entity.game.GameRequest;
import by.training.entity.game.GameResponse;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import by.training.validation.RateValidation;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

/**
 * Created by angelina on 22.04.2017.
 */
public class NewMultiuserGame {
    private static final int PLAYER_NUMBER = 2;
    public static final Logger LOGGER = Logger.getLogger(NewMultiuserGame.class);

    public static GameResponse playNewGame(GameRequest gameRequest) throws CommandException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try {
            GameResponse gameResponse = new GameResponse();
            gameResponse.setConsignment(GameMechanics.findAllConsignment());
            gameResponse.setMaxScore(CountMaxScore.countScore(gameResponse.getConsignment()));

            connection.setAutoCommit(false);
            GameDAO gameDAO = new GameDAO(connection);
            BigDecimal balance = gameDAO.checkBalance(gameRequest.getUserId());
            if (RateValidation.checkInadmissibility(gameRequest.getRate(), balance)) {
                throw new CommandException("not enough money");
            }

            Game game = new Game();
            GameAccount gameAccount = new GameAccount();
            gameDAO.updateScore(gameRequest.getUserId(), gameRequest.getRate().negate());
            game.setBank(gameRequest.getRate().multiply(new BigDecimal(PLAYER_NUMBER)).setScale(2, RoundingMode.DOWN));
            game.setMaxScore(gameResponse.getMaxScore());
            int gameId = gameDAO.createGame(game);
            gameResponse.setGameId(gameId);

            gameAccount.setUserId(gameRequest.getUserId());
            gameAccount.setRate(gameRequest.getRate());
            gameAccount.setUserScore(gameResponse.getMaxScore());
            gameAccount.setGameId(gameId);
            gameDAO.addPlayer(gameAccount);

            connection.commit();
            return gameResponse;
        } catch (DAOException | SQLException e) {
            LOGGER.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
               LOGGER.error(e.getMessage());
            }
        }
        throw new CommandException("");
    }
}

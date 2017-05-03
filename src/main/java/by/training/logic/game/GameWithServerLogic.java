package by.training.logic.game;

import by.training.action.GameMechanics;
import by.training.connection.ConnectionPool;
import by.training.connection.ProxyConnection;
import by.training.dao.GameDAO;
import by.training.entity.game.Game;
import by.training.entity.game.GameAccount;
import by.training.entity.game.GameRequest;
import by.training.entity.game.GameServerResponse;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import by.training.validation.RateValidation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

/**
 * Created by angelina on 16.04.2017.
 */
public class GameWithServerLogic {

    private static final int PLAYER_NUMBER = 2;

    public static GameServerResponse play(GameRequest gameRequest) throws CommandException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try {
            GameServerResponse gameServerResponse = new GameServerResponse();
            gameServerResponse.setServerConsignment(GameMechanics.findAllConsignment());
            gameServerResponse.setConsignment(GameMechanics.findAllConsignment());
            gameServerResponse.setServerMaxScore(12);//CountMaxScore.countScore(gameServerResponse.getServerConsignment()));
            gameServerResponse.setMaxScore(12);//CountMaxScore.countScore(gameServerResponse.getConsignment()));
            connection.setAutoCommit(false);
            GameDAO gameDAO = new GameDAO(connection);
            BigDecimal balance = gameDAO.checkBalance(gameRequest.getUserId());
            if(RateValidation.checkInadmissibility(gameRequest.getRate(), balance)) {
                throw new CommandException("not enough money");
            }
            gameServerResponse.setUserScore(balance.subtract(gameRequest.getRate()));
//            gameDAO.updateScore(gameRequest.getUserId(), gameServerResponse.getUserScore());
            gameDAO.updateScore(gameRequest.getUserId(), gameRequest.getRate().negate());
            Game game = new Game();
            GameAccount gameAccount = new GameAccount();
            gameAccount.setUserId(gameRequest.getUserId());
            gameAccount.setRate(gameRequest.getRate());
            gameAccount.setUserScore(gameServerResponse.getMaxScore());
            game.setBank(gameRequest.getRate().multiply(new BigDecimal(PLAYER_NUMBER)).setScale(2, RoundingMode.DOWN));
            if (gameServerResponse.getMaxScore() >= gameServerResponse.getServerMaxScore()) {
                game.setMaxScore(gameServerResponse.getMaxScore());
                gameAccount.setWinner(true);
                if (gameServerResponse.getMaxScore() == gameServerResponse.getServerMaxScore()) {
                    game.setBank(gameRequest.getRate());
                }
            } else {
                game.setMaxScore(gameServerResponse.getServerMaxScore());
            }
            game.setComplete(true);
            int gameId = gameDAO.createGame(game);
            gameAccount.setGameId(gameId);
            gameDAO.addPlayer(gameAccount);
            gameServerResponse.setGameId(gameId);
            if(gameAccount.isWinner()){
                BigDecimal newBalance = gameServerResponse.getUserScore().add(game.getBank());
                gameDAO.updateScore(gameRequest.getUserId(), game.getBank());
                gameServerResponse.setUserScore(newBalance);
            }
            gameServerResponse.setWinner(gameServerResponse.getMaxScore() >= gameServerResponse.getServerMaxScore());
            connection.commit();
            return gameServerResponse;
        } catch (DAOException | SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //log
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
        throw new CommandException("");
    }
}
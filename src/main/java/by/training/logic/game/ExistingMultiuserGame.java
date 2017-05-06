package by.training.logic.game;

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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class ExistingMultiuserGame {
    private static final int PLAYER_NUMBER = 2;

    public static GameResponse playExistingGame(GameRequest gameRequest) throws CommandException {
        ProxyConnection connection = ConnectionPool.getConnection();
        try {
            GameResponse gameResponse = new GameResponse();
            gameResponse.setConsignment(GameMechanics.findAllConsignment());
            gameResponse.setMaxScore(12);//CountMaxScore.countScore(gameResponse.getConsignment()));
            gameResponse.setGameId(gameRequest.getGameId());

            connection.setAutoCommit(false);
            GameAccount gameAccount = new GameAccount();
            GameDAO gameDAO = new GameDAO(connection);
            Game game = gameDAO.findGame(gameRequest.getGameId());

            if (game.isComplete()) {
                throw new CommandException("game is complete yet");
            }
            gameAccount.setRate(game.getBank().divide(new BigDecimal(PLAYER_NUMBER), RoundingMode.UP));
            BigDecimal balance = gameDAO.checkBalance(gameRequest.getUserId());
            if (RateValidation.checkInadmissibility(gameAccount.getRate(), balance)) {
                throw new CommandException("not enough money");
            }

            GameAccount competitorAccount = gameDAO.findPlayer(gameResponse.getGameId());

            gameAccount.setUserId(gameRequest.getUserId());
            gameAccount.setUserScore(gameResponse.getMaxScore());
            gameAccount.setGameId(game.getGameId());
            if (gameResponse.getMaxScore() > game.getMaxScore()) {
                //второй выиграл
                gameAccount.setWinner(true);
                gameDAO.updateGame(game.getGameId(), gameResponse.getMaxScore(), true);
                gameDAO.updateScore(gameRequest.getUserId(), gameAccount.getRate());
                gameDAO.addPlayer(gameAccount);
                //оповестить первого
            } else {
                if (gameResponse.getMaxScore() == game.getMaxScore()) {
                    //ничья
                    gameAccount.setWinner(true);
                    gameDAO.updateScore(competitorAccount.getUserId(), gameAccount.getRate());

                } else {
                    //второй проиграл
//                finalMaxScore = game.getMaxScore();
                    gameDAO.updateScore(gameRequest.getUserId(), gameAccount.getRate().negate());
                    gameDAO.updateScore(competitorAccount.getUserId(), game.getBank());
                }
                gameDAO.updateGame(game.getGameId(), competitorAccount.getUserScore(), true);
                gameDAO.addPlayer(gameAccount);
            }


//            gameDAO.updateGame(game.getGameId(), finalMaxScore, true);


            gameDAO.markWinner(game.getGameId());


//            gameServerResponse.setGameId(gameId);
//            if(gameAccount.isWinner()){
//                gameDAO.updateScore(gameRequest.getUserId(), balance.subtract(gameRequest.getRate()).add(game.getBank()));
//            }
            connection.commit();
            return gameResponse;
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

package by.training.dao;

import by.training.entity.game.Game;
import by.training.entity.game.GameAccount;
import by.training.exception.DAOException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by angelina on 18.04.2017.
 */
public abstract class AbstractGameDAO {

    public abstract int createGame(Game game) throws DAOException;

    public abstract void addPlayer(GameAccount gameAccount) throws DAOException;

    public abstract Game findGame(int gameId) throws DAOException;

    public abstract GameAccount findPlayer(int gameId) throws DAOException;

    public abstract BigDecimal checkBalance(int userId) throws DAOException;

    public abstract void updateScore(int userId, BigDecimal score) throws DAOException;

    public abstract void updateGame(int gameId, int maxScore, boolean complete) throws DAOException;

    public abstract void markWinner(int gameId) throws DAOException;

    public abstract ArrayList<GameAccount> incompleteGameList(int userId) throws DAOException;
}

package by.training.dao;

import by.training.connection.ProxyConnection;
import by.training.entity.game.Game;
import by.training.entity.game.GameAccount;
import by.training.exception.DAOException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelina on 18.04.2017.
 */
public class GameDAO extends AbstractGameDAO implements EntityDAO {
    private static final String PARAM_NAME_GAME_ID = "gameId";
    private static final String PARAM_NAME_DATE = "date";
    private static final String PARAM_NAME_MAX_SCORE = "maxScore";
    private static final String PARAM_NAME_BANK = "bank";
    private static final String PARAM_NAME_USER_ID = "userId";
    private static final String PARAM_NAME_RATE = "rate";
    private static final String PARAM_NAME_USER_SCORE = "userScore";
    private static final String PARAM_NAME_COMPLETE = "complete";
    private static final String PARAM_NAME_IS_WINNER = "isWinner";
    private static final String PARAM_NAME_SCORE = "score";
    private static final String PARAM_NAME_LOGIN = "login";


    private static final String SQL_INSERT_NEW_GAME =
            "INSERT INTO game (gameId, date, maxScore, bank, complete) VALUES(NULL, NOW(), ?, ?, ?)";
    private static final String SQL_INSERT_NEW_PLAYER =
            "INSERT INTO game_m2m_user (gameId, userId, rate, userScore, isWinner)" +
                    " VALUES(?, ?, ?, ?, ?);";
    private static final String SQL_SELECT_SCORE_BY_ID =
            "SELECT score FROM user WHERE userId = ?";
    private static final String SQL_SELECT_GAME_BY_ID =
            "SELECT * FROM game WHERE gameId = ?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * FROM game_m2m_user WHERE gameId = ?";
    private static final String SQL_SELECT_GAME_BY_COMPLETE =
            "SELECT gameId, login, rate FROM (game_m2m_user JOIN user ON game_m2m_user.userId = user.userId) WHERE game_m2m_user.userId <> ? AND gameId IN (SELECT gameId FROM game WHERE complete = FALSE)";
    private static final String SQL_UPDATE_SCORE_BY_ID =
//            "UPDATE user SET score = ? WHERE userId = ?";
            "UPDATE user SET score = score + ? WHERE userId = ?";
    private static final String SQL_UPDATE_GAME_BY_ID =
            "UPDATE game SET maxScore = ?, complete = ? WHERE gameId = ?";
    private static final String SQL_UPDATE_USER_BY_ID =
            "UPDATE game_m2m_user SET isWinner = TRUE WHERE userScore = (SELECT maxScore FROM game WHERE game.gameId = ?)";


    private ProxyConnection connection;

    public GameDAO(ProxyConnection connection) {
        this.connection = connection;
    }

    public ProxyConnection getConnection() {
        return connection;
    }

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object findEntityById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Object entity) throws DAOException {

    }


    @Override
    public int createGame(Game game) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_GAME, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, game.getMaxScore());
            statement.setBigDecimal(2, game.getBank());
            statement.setBoolean(3, game.isComplete());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("can't create game.");
    }

    @Override
    public void addPlayer(GameAccount gameAccount) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_PLAYER)) {
            statement.setInt(1, gameAccount.getGameId());
            statement.setInt(2, gameAccount.getUserId());
            statement.setBigDecimal(3, gameAccount.getRate());
            statement.setInt(4, gameAccount.getUserScore());
            statement.setBoolean(5, gameAccount.isWinner());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception - request or table failed: " + e);
        }
    }

    @Override
    public Game findGame(int gameId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GAME_BY_ID)) {
            statement.setInt(1, gameId);
            ResultSet resSet = statement.executeQuery();
            if (resSet.next()) {
                Game game = new Game();
                game.setGameId(gameId);
                game.setDate(resSet.getDate(PARAM_NAME_DATE).toLocalDate());
                game.setMaxScore(resSet.getInt(PARAM_NAME_MAX_SCORE));
                game.setBank(resSet.getBigDecimal(PARAM_NAME_BANK));
                game.setComplete(resSet.getBoolean(PARAM_NAME_COMPLETE));
                return game;
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't check balance: no user registered");
    }

    @Override
    public GameAccount findPlayer(int gameId) throws DAOException {
        return null;
    }

    @Override
    public BigDecimal checkBalance(int userId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SCORE_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resSet = statement.executeQuery();
            if (resSet.next()) {
                return resSet.getBigDecimal(PARAM_NAME_SCORE);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("Can't check balance: no user registered");
    }

    @Override
    public void updateScore(int userId, BigDecimal score) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SCORE_BY_ID)) {
            statement.setBigDecimal(1, score);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception - request or table failed: " + e);
        }
    }

    @Override
    public void updateGame(int gameId, int maxScore, boolean complete) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_GAME_BY_ID)) {
            statement.setInt(1, maxScore);
            statement.setBoolean(2, complete);
            statement.setInt(3, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception - request or table failed: " + e);
        }
    }

    @Override
    public void markWinner(int gameId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID)) {
            statement.setInt(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL exception - request or table failed: " + e);
        }
    }

    @Override
    public ArrayList<GameAccount> incompleteGameList(int userId) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GAME_BY_COMPLETE)) {
            ArrayList<GameAccount> gameList = new ArrayList<>();
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                GameAccount gameAccount = new GameAccount();
                gameAccount.setGameId(resultSet.getInt(PARAM_NAME_GAME_ID));
                gameAccount.setUserLogin(resultSet.getString(PARAM_NAME_LOGIN));
                gameAccount.setRate(resultSet.getBigDecimal(PARAM_NAME_RATE));
                gameList.add(gameAccount);
            }
            return gameList;

        } catch (SQLException e) {
            LOGGER.error("SQL exception - request or table failed: " + e);
        }
        throw new DAOException("no incomplete games");
    }


}

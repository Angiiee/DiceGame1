package by.training.entity.game;

import by.training.entity.Entity;
import by.training.entity.game.type.GameType;
import by.training.entity.game.type.MultiuserGameType;
import by.training.entity.game.type.RequestType;

import java.math.BigDecimal;

/**
 * Created by angelina on 16.04.2017.
 */
public class GameRequest extends Entity {

    private RequestType requestType;
    private GameType gameType;
    private MultiuserGameType multiuserGameType;
    private BigDecimal rate;
    private int userId;
    private int gameId;
    private String userLogin;
    private BigDecimal actualScore;

    public GameRequest() {
    }

    public GameRequest(RequestType requestType, GameType gameType,
                       MultiuserGameType multiuserGameType, BigDecimal rate,
                       int userId, int gameId, String userLogin, BigDecimal actualScore) {
        this.requestType = requestType;
        this.gameType = gameType;
        this.multiuserGameType = multiuserGameType;
        this.rate = rate;
        this.userId = userId;
        this.gameId = gameId;
        this.userLogin = userLogin;
        this.actualScore = actualScore;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public MultiuserGameType getMultiuserGameType() {
        return multiuserGameType;
    }

    public void setMultiuserGameType(MultiuserGameType multiuserGameType) {
        this.multiuserGameType = multiuserGameType;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public BigDecimal getActualScore() {
        return actualScore;
    }

    public void setActualScore(BigDecimal actualScore) {
        this.actualScore = actualScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameRequest)) return false;

        GameRequest that = (GameRequest) o;

        if (getUserId() != that.getUserId()) return false;
        if (getGameId() != that.getGameId()) return false;
        if (getRequestType() != that.getRequestType()) return false;
        if (getGameType() != that.getGameType()) return false;
        if (getMultiuserGameType() != that.getMultiuserGameType()) return false;
        if (getRate() != null ? !getRate().equals(that.getRate()) : that.getRate() != null) return false;
        if (getUserLogin() != null ? !getUserLogin().equals(that.getUserLogin()) : that.getUserLogin() != null)
            return false;
        return getActualScore() != null ? getActualScore().equals(that.getActualScore()) : that.getActualScore() == null;
    }

    @Override
    public int hashCode() {
        int result = getRequestType() != null ? getRequestType().hashCode() : 0;
        result = 31 * result + (getGameType() != null ? getGameType().hashCode() : 0);
        result = 31 * result + (getMultiuserGameType() != null ? getMultiuserGameType().hashCode() : 0);
        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + getGameId();
        result = 31 * result + (getUserLogin() != null ? getUserLogin().hashCode() : 0);
        result = 31 * result + (getActualScore() != null ? getActualScore().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameRequest{" +
                "requestType=" + requestType +
                ", gameType=" + gameType +
                ", multiuserGameType=" + multiuserGameType +
                ", rate=" + rate +
                ", userId=" + userId +
                ", gameId=" + gameId +
                ", userLogin='" + userLogin + '\'' +
                ", actualScore=" + actualScore +
                '}';
    }
}
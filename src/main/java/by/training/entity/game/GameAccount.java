package by.training.entity.game;

import by.training.entity.Entity;

import java.math.BigDecimal;

/**
 * Created by angelina on 18.04.2017.
 */
public class GameAccount extends Entity {
    private int gameId;
    private int userId;
    private BigDecimal rate;
    private int userScore;
    private boolean winner;
    private String userLogin;

    public GameAccount() {
    }

    public GameAccount(int gameId, int userId, BigDecimal rate,
                       int userScore, boolean winner, String userLogin) {
        this.gameId = gameId;
        this.userId = userId;
        this.rate = rate;
        this.userScore = userScore;
        this.winner = winner;
        this.userLogin = userLogin;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameAccount)) return false;

        GameAccount that = (GameAccount) o;

        if (getGameId() != that.getGameId()) return false;
        if (getUserId() != that.getUserId()) return false;
        if (getUserScore() != that.getUserScore()) return false;
        if (isWinner() != that.isWinner()) return false;
        if (getRate() != null ? !getRate().equals(that.getRate()) : that.getRate() != null) return false;
        return getUserLogin() != null ? getUserLogin().equals(that.getUserLogin()) : that.getUserLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getGameId();
        result = 31 * result + getUserId();
        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
        result = 31 * result + getUserScore();
        result = 31 * result + (isWinner() ? 1 : 0);
        result = 31 * result + (getUserLogin() != null ? getUserLogin().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameAccount{" +
                "gameId=" + gameId +
                ", userId=" + userId +
                ", rate=" + rate +
                ", userScore=" + userScore +
                ", winner=" + winner +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}

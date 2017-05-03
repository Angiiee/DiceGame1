package by.training.entity.game;

import by.training.entity.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelina on 15.04.2017.
 */
public class GameResponse extends Entity{
    private int maxScore;
    private ArrayList<List<Integer>> consignment;
    private int gameId;
    private BigDecimal userScore;
    private boolean winner;


    public GameResponse(){}

    public GameResponse(int maxScore, ArrayList<List<Integer>> consignment, int gameId, BigDecimal userScore, boolean winner) {
        this.maxScore = maxScore;
        this.consignment = consignment;
        this.gameId = gameId;
        this.userScore = userScore;
        this.winner = winner;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public ArrayList<List<Integer>> getConsignment() {
        return consignment;
    }

    public void setConsignment(ArrayList<List<Integer>> consignment) {
        this.consignment = consignment;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public BigDecimal getUserScore() {
        return userScore;
    }

    public void setUserScore(BigDecimal userScore) {
        this.userScore = userScore;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameResponse)) return false;

        GameResponse that = (GameResponse) o;

        if (getMaxScore() != that.getMaxScore()) return false;
        if (getGameId() != that.getGameId()) return false;
        if (isWinner() != that.isWinner()) return false;
        if (getConsignment() != null ? !getConsignment().equals(that.getConsignment()) : that.getConsignment() != null)
            return false;
        return getUserScore() != null ? getUserScore().equals(that.getUserScore()) : that.getUserScore() == null;
    }

    @Override
    public int hashCode() {
        int result = getMaxScore();
        result = 31 * result + (getConsignment() != null ? getConsignment().hashCode() : 0);
        result = 31 * result + getGameId();
        result = 31 * result + (getUserScore() != null ? getUserScore().hashCode() : 0);
        result = 31 * result + (isWinner() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "maxScore=" + maxScore +
                ", consignment=" + consignment +
                ", gameId=" + gameId +
                ", userScore=" + userScore +
                ", winner=" + winner +
                '}';
    }
}

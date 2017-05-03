package by.training.entity.game;

import by.training.entity.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by angelina on 19.04.2017.
 */
public class Game extends Entity{
    private int gameId;
    private LocalDate date;
    private int maxScore;
    private BigDecimal bank;
    private boolean complete;


    public Game() {
    }

    public Game(int gameId, LocalDate date, int maxScore, BigDecimal bank, boolean complete) {
        this.gameId = gameId;
        this.date = date;
        this.maxScore = maxScore;
        this.bank = bank;
        this.complete = complete;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public BigDecimal getBank() {
        return bank;
    }

    public void setBank(BigDecimal bank) {
        this.bank = bank;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        if (getGameId() != game.getGameId()) return false;
        if (getMaxScore() != game.getMaxScore()) return false;
        if (isComplete() != game.isComplete()) return false;
        if (getDate() != null ? !getDate().equals(game.getDate()) : game.getDate() != null) return false;
        return getBank() != null ? getBank().equals(game.getBank()) : game.getBank() == null;
    }

    @Override
    public int hashCode() {
        int result = getGameId();
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + getMaxScore();
        result = 31 * result + (getBank() != null ? getBank().hashCode() : 0);
        result = 31 * result + (isComplete() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", date=" + date +
                ", maxScore=" + maxScore +
                ", bank=" + bank +
                ", complete=" + complete +
                '}';
    }
}

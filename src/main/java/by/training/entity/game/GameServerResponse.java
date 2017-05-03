package by.training.entity.game;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelina on 16.04.2017.
 */
public class GameServerResponse extends GameResponse {

    private int serverMaxScore;
    private ArrayList<List<Integer>> serverConsignment;

    public GameServerResponse() {
    }

    public GameServerResponse(int maxScore, ArrayList<List<Integer>> consignment,
                              int gameId, BigDecimal userScore, boolean winner, int serverMaxScore,
                              ArrayList<List<Integer>> serverConsignment) {
        super(maxScore, consignment, gameId, userScore, winner);
        this.serverMaxScore = serverMaxScore;
        this.serverConsignment = serverConsignment;
    }

    public int getServerMaxScore() {
        return serverMaxScore;
    }

    public void setServerMaxScore(int serverMaxScore) {
        this.serverMaxScore = serverMaxScore;
    }

    public ArrayList<List<Integer>> getServerConsignment() {
        return serverConsignment;
    }

    public void setServerConsignment(ArrayList<List<Integer>> serverConsignment) {
        this.serverConsignment = serverConsignment;
    }
}
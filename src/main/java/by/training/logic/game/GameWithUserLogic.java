package by.training.logic.game;

import by.training.entity.game.GameRequest;
import by.training.entity.game.GameResponse;
import by.training.entity.game.type.MultiuserGameType;
import by.training.exception.CommandException;

/**
 * Created by angelina on 21.04.2017.
 */
public class GameWithUserLogic {
    private static final int PLAYER_NUMBER = 2;

    public static GameResponse play(GameRequest gameRequest) throws CommandException {
        GameResponse gameResponse = null;
        MultiuserGameType multiuserGameType = gameRequest.getMultiuserGameType();
        switch (multiuserGameType){
            case NEW:{
                gameResponse = NewMultiuserGame.playNewGame(gameRequest);
                break;
            }
            case EXISTING:{
                gameResponse = ExistingMultiuserGame.playExistingGame(gameRequest);
                break;
            }
            default:{
                throw new CommandException("Unsupported multiuser game type.");
            }
        }
        return gameResponse;
    }
}

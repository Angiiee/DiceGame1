package by.training.command.game;

import by.training.logic.game.GameWithServerLogic;
import by.training.entity.game.GameRequest;
import by.training.entity.game.GameResponse;
import by.training.entity.game.type.GameType;
import by.training.logic.game.GameWithUserLogic;
import by.training.exception.CommandException;

/**
 * Created by angelina on 19.04.2017.
 */
public class GameCommand {

    public GameResponse execute(GameRequest gameRequest) throws CommandException {
        GameResponse gameResponse = null;
        GameType gameType = gameRequest.getGameType();
        switch (gameType) {
            case SERVER: {
                gameResponse = GameWithServerLogic.play(gameRequest);
                break;
            }
            case MULTIUSER: {
                gameResponse= GameWithUserLogic.play(gameRequest);
                break;
            }
            //REFUSEGAME
        }
        return gameResponse;
    }
}

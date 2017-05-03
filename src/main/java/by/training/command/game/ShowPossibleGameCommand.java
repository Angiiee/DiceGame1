package by.training.command.game;

import by.training.entity.game.GameAccount;
import by.training.entity.game.GameRequest;
import by.training.exception.CommandException;
import by.training.logic.game.ShowPossibleGameLogic;

import java.util.ArrayList;

/**
 * Created by angelina on 25.04.2017.
 */
public class ShowPossibleGameCommand {

    public ArrayList<GameAccount> execute(GameRequest gameRequest) throws CommandException {
        ArrayList<GameAccount> list;
        list = ShowPossibleGameLogic.show(gameRequest.getUserId());
        return list;
    }
}

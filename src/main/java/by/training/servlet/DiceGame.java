package by.training.servlet;

import by.training.command.game.GameCommand;
import by.training.command.game.ShowPossibleGameCommand;
import by.training.entity.game.GameRequest;
import by.training.entity.game.GameResponse;
import by.training.exception.CommandException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by angelina on 14.04.2017.
 */
@WebServlet(name = "DiceGame", urlPatterns = {"/game"})
public class DiceGame extends HttpServlet {

    private static final int ERROR_NOT_ENOUGH_MONEY = 1;
    public static final Logger LOGGER = Logger.getLogger(DiceGame.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));) {
            String json = br.readLine();
            ObjectMapper mapper = new ObjectMapper();
            GameRequest gameRequest = mapper.readValue(json, GameRequest.class);
            gameRequest.setUserLogin((String) request.getSession(true).getAttribute("username"));
            gameRequest.setUserId((Integer) request.getSession(true).getAttribute("userId"));
            switch (gameRequest.getRequestType()) {
                case GAME: {
                    GameCommand gameCommand = new GameCommand();
                    GameResponse gameResponse = gameCommand.execute(gameRequest);
                    request.getSession(true).setAttribute("userScore", gameResponse.getUserScore());
                    mapper.writeValue(response.getOutputStream(), gameResponse);
                    break;
                }
                case SHOWPOSSIBLE: {
                    ShowPossibleGameCommand showPossibleGameCommand = new ShowPossibleGameCommand();
                    mapper.writeValue(response.getOutputStream(), showPossibleGameCommand.execute(gameRequest));
                    break;
                }
            }


        } catch (CommandException e) {
            //не достаточно денег
//            response.setStatus(500);
//            response.sendError(ERROR_NOT_ENOUGH_MONEY);
        }
    }
}

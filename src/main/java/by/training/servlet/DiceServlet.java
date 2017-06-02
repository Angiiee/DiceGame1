package by.training.servlet;

import by.training.command.ActionCommand;
import by.training.command.ActionFactory;
import by.training.entity.response.ResponseInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by angelina on 21.02.2017.
 */
@WebServlet(name = "DiceServlet", urlPatterns = {"/controller"})
@MultipartConfig
public class DiceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActionFactory client = new ActionFactory();
        request.setCharacterEncoding("UTF-8");
        ActionCommand command = client.defineCommand(request);
        ResponseInfo responseInfo = command.execute(request);
        switch (responseInfo.getType()) {
            case FORWARD: {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responseInfo.getNextPage());
                dispatcher.forward(request, response);
                break;
            }
            case REDIRECT: {
                response.sendRedirect(responseInfo.getNextPage());
                break;
            }
        }
    }
}
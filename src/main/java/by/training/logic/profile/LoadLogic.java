package by.training.logic.profile;

import by.training.dao.UserDAO;
import by.training.exception.CommandException;
import by.training.exception.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by angelina on 21.03.2017.
 */
public class LoadLogic {
    public static final Logger LOGGER = Logger.getLogger(LoadLogic.class);

    public static String saveAvatar(HttpServletRequest request) throws CommandException {
        try {
            UserDAO userDAO = new UserDAO();
            String path = request.getServletContext().getRealPath("") + "img\\avatar\\";
            Part filePart = request.getPart("avatarLoader");
            String login = (String) request.getSession().getAttribute("username");
            String fileName = Paths.get(filePart.getSubmittedFileName()).toString();
            if(fileName.isEmpty()){
                throw new CommandException("No file was chosen.");
            }
            String fileExpansion = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = login + fileExpansion;
            File file = new File(path + newFileName);
            InputStream fileContext = filePart.getInputStream();
            Files.copy(fileContext, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            userDAO.saveAvatar(newFileName, login);
            return newFileName;
        } catch (ServletException | IOException e) {
            LOGGER.error(e);
        } catch (DAOException e) {
            throw new CommandException("Can't save new avatar. Try later.");
        }
        throw new CommandException("Can't load new avatar");
    }
}

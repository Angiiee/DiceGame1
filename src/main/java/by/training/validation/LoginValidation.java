package by.training.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by angelina on 28.02.2017.
 */
public class LoginValidation implements Validation{

    public static String LOGIN_REGEX = "^[a-zA-Zа-яА-Я0-9_-]{5,}$";

    @Override
    public boolean validate(String login) {
        Pattern pattern = Pattern.compile(LOGIN_REGEX);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }
}

package by.training.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by angelina on 28.02.2017.
 */
public class PasswordValidation implements Validation{

    public static String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})";

    @Override
    public boolean validate(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

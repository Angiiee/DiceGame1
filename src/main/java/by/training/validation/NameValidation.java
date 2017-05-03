package by.training.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by angelina on 19.03.2017.
 */
public class NameValidation implements Validation {
    public static String NAME_REGEX = "((?!.*\\d)(?=.*[a-zA-Zа-яА-Я]).{2,})";

    @Override
    public boolean validate(String password) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

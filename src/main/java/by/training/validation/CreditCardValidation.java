package by.training.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by angelina on 12.03.2017.
 */
public class CreditCardValidation implements Validation{

    public static String CREDIT_CARD_REGEX = "(\\d){16}";

    @Override
    public boolean validate(String password) {
        Pattern pattern = Pattern.compile(CREDIT_CARD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

package by.training.validation;

import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Created by angelina on 20.04.2017.
 */
public class RateValidation {

    public static final Logger LOGGER = Logger.getLogger(RateValidation.class);

    public static boolean checkInadmissibility(BigDecimal rate, BigDecimal actualScore){
        return actualScore.subtract(rate).signum() < 0;
    }
}

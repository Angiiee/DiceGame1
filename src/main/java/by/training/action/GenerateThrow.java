package by.training.action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by angelina on 16.04.2017.
 */
public class GenerateThrow {

    private static final int MAX_NUMBER = 7;
    private static final int MIN_NUMBER = 1;

    public static List<Integer> generate(int k) {
        ArrayList<Integer> list;
        IntStream stream = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(MIN_NUMBER, MAX_NUMBER)).limit(k);
        return stream.boxed().collect(Collectors.toList());
    }
}

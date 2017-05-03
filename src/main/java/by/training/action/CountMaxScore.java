package by.training.action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelina on 16.04.2017.
 */
public class CountMaxScore {

    private static final int FIRST_WIHTDRAW_NUMBER = 2;
    private static final int SECOND_WIHTDRAW_NUMBER = 5;

    public static int countScore(ArrayList<List<Integer>> consignmentList){
        int score = 0;
        for(List<Integer> list : consignmentList){
            if(noWithdrawNumber(list)){
                score += list.stream().reduce(0, Integer::sum);
            }
        }
        return score;
    }

    private static boolean noWithdrawNumber(List<Integer> list){
        return list.stream().filter(o -> (o==FIRST_WIHTDRAW_NUMBER)||(o==SECOND_WIHTDRAW_NUMBER)).count() == 0;
    }
}

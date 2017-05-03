package by.training.action;

import by.training.action.GenerateThrow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelina on 16.04.2017.
 */
public class GameMechanics {

    private static final int INITIAL_DICE_QUANTITY = 5;
    private static final int FIRST_WIHTDRAW_NUMBER = 2;
    private static final int SECOND_WIHTDRAW_NUMBER = 5;

    public static ArrayList<List<Integer>> findAllConsignment(){
        ArrayList<List<Integer>> consignmentList = new ArrayList<>();
        List<Integer> list = GenerateThrow.generate(INITIAL_DICE_QUANTITY);
        consignmentList.add(list);
        int listSize = INITIAL_DICE_QUANTITY - withdrawCount(list);
        while(listSize!=0){
            list = GenerateThrow.generate(listSize);
            consignmentList.add(list);
            listSize -= withdrawCount(list);
        }
        return consignmentList;
    }

    private static int withdrawCount(List<Integer> list){
        return (int)list.stream().filter(o -> (o==FIRST_WIHTDRAW_NUMBER)||(o==SECOND_WIHTDRAW_NUMBER)).count();
    }
}

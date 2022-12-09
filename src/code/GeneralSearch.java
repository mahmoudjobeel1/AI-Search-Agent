package code;

import java.util.ArrayList;

public abstract class GeneralSearch {
    public Object initialState;
    public ArrayList<Object> actionList;
    public static boolean checkGoalTest(Object o){return false;}
    public static int pathCost(Object o){return 0;}
}

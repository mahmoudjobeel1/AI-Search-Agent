package code;

import code.Algorithms.Search;
import code.node.Node;
import code.grid.*;
import code.state.State;

import java.util.ArrayList;


public class CoastGuard extends GeneralSearch{


    public static String genGrid() {
        return GridGenerator.genGrid();
    }


    public static String solve (String grid, String strategy, boolean visualize) {

        CoastGuard coastGuard=new CoastGuard();
        coastGuard.genInitialState(grid);
        coastGuard.genActionList();

        Search search=new Search(strategy,(State) coastGuard.initialState,coastGuard.actionList,visualize);

        return search.getSolution();
    }
    public void genActionList(){
        actionList=new ArrayList<>();
        actionList.add("up");
        actionList.add("down");
        actionList.add("right");
        actionList.add("left");
        actionList.add("pickup");
        actionList.add("drop");
        actionList.add("retrieve");
    }
    public void genInitialState(String grid){
        Object [] objects=GridGenerator.buildGrid(grid);
        RescueBoat boat=(RescueBoat) objects[0];
        Grid gridObject=(Grid) objects[1];
        initialState=new State(boat,gridObject);
    }
    public static boolean checkGoalTest(Object o){
        Node node=(Node) o;
        State state=node.getState();
        return state.getGrid().isEmpty() && state.getBoat().isEmpty();
    }
    public static int pathCost(Object o){
        Node node=(Node) o;
        State state=node.getState();
        int maxDistance = (state.getGrid().getN()+state.getGrid().getM());
        return maxDistance*(2*state.getDeaths() + state.getDamagedBoxes()) ;
    }

    public static void main(String[] args) {

    }
}

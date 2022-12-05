package code;

import code.Algorithms.*;
import code.Algorithms.SearchAlgorithm;
import code.state.Node;
import code.grid.*;

import java.util.HashSet;


public class CoastGuard {


    public static String genGrid() {
        GridGenerator gridGenerator = new GridGenerator() ;
        return gridGenerator.generateNewGrid();
    }
    private static SearchAlgorithm getAlgorithm (String strategy){
        switch (strategy) {
            case "BF" : return new BFS();
            case "DF" : return new DF() ;
            case "ID" : return new ID() ;
            case "GR1" : return new GR1() ;
            case "GR2" : return new GR2() ;
            default: return new AS() ;
        }
    }


    public static String solve (String grid, String strategy, boolean visualize) {


        Object [] objects=GridGenerator.buildGrid(grid);
        RescueBoat boat=(RescueBoat) objects[0];
        Grid gridObject=(Grid) objects[1];
        SearchAlgorithm algorithm = CoastGuard.getAlgorithm(strategy);
        algorithm.previousStates=new HashSet<>();
        Node goalState = algorithm.search(boat,gridObject,visualize);

        StringBuilder ans=new StringBuilder(goalState.getGoalTestNodeString()).append(algorithm.expandedNodes);
        return ans.toString();

    }

    public static void main(String[] args) {
       // String grid = "5,6;50;0,1;0,4,3,3;1,1,90;";
        String grid="2,2;20;0,0;0,1;1,0,10;";
        // String grid=GridGenerator.genGrid();
        // String grid="9,13;65;6,4;3,8,6,2,2,2;1,12,88,7,4,45,2,9,41,5,8,12,7,12,96;";
        System.out.println(solve(grid,"DF",false));
    }
}

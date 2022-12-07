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
            case "AS1" : return new AS1() ;
            default: return new AS2() ;
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
        String grid = "5,6;50;0,1;0,4,3,3;1,1,90;";
       // String grid="7,5;40;2,3;3,6;1,1,10,4,5,90;";
        // String grid=GridGenerator.genGrid();
        // String grid="9,13;65;6,4;3,8,6,2,2,2;1,12,88,7,4,45,2,9,41,5,8,12,7,12,96;";
        String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
        String grid1 = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";
        String grid2 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";
        String grid3 = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";
        String grid4 = "5,7;63;4,2;6,2,6,3;0,0,17,0,2,73,3,0,30;";
        String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
        String grid6 = "7,5;86;0,0;1,3,1,5,4,2;1,1,42,2,5,99,3,5,89;";
        String grid7= "6,7;82;1,4;2,3;1,1,58,3,0,58,4,2,72;";
        String grid8 = "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;";
        String grid9 = "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,3,2,94,4,3,46;";
        String grid10= "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;";
        System.out.println(solve(grid10,"GR2",true));
    }
}

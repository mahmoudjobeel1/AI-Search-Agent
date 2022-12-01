package code;

import code.Algorithms.*;
import code.Algorithms.SearchAlgorithm;
import code.state.Node;
import code.grid.*;


import java.util.*;

public class CoastGuard {


    public static String genGrid() {
        GridGenerator gridGenerator = new GridGenerator() ;
        return gridGenerator.generateNewGrid();
    }
    private static SearchAlgorithm getAlgorithm (String strategy){
        switch (strategy) {
            case "BF" : return new BFS();
            case "DF" : return new DFS() ;
            case "ID" : return new ID() ;
            case "GR" : return new GR() ;
            default: return new AS() ;
        }
    }


    public static String solve (String grid, String strategy, boolean visualize) {

        GridGenerator gridGenerator = new GridGenerator(grid);
        Object [][] constructedGrid = gridGenerator.getGrid();
        GridObjectType [][] gridObjectTypes = new GridObjectType[constructedGrid.length][constructedGrid[0].length] ;

        RescueBoat boat = gridGenerator.getBoat() ;
        List<Ship> ships = new ArrayList<>() ;
        for (int i=0; i<constructedGrid.length; i++){
            for(int j=0; j<constructedGrid[0].length; j++) {
               if (constructedGrid[i][j] instanceof Ship){
                    ships.add((Ship) constructedGrid[i][j]) ;
                    gridObjectTypes[i][j] = GridObjectType.SHIP ;
                }
                else if (constructedGrid[i][j] instanceof Station) {
                    gridObjectTypes[i][j] = GridObjectType.STATION ;
                }
                else {
                    gridObjectTypes[i][j] = GridObjectType.EMPTY ;
                }
            }
        }

        if (visualize) {
            for (int i = 0; i < gridObjectTypes.length; i++) {
                for (int j = 0; j < gridObjectTypes[0].length; j++) {
                    if (gridObjectTypes[i][j] == GridObjectType.STATION)
                        System.out.print(gridObjectTypes[i][j] + "  ");
                    else if (gridObjectTypes[i][j] == GridObjectType.EMPTY)
                        System.out.print(gridObjectTypes[i][j] + "    ");
                    else if (gridObjectTypes[i][j] == GridObjectType.SHIP)
                        System.out.print(gridObjectTypes[i][j] + "     ");
                }
                System.out.println();
            }
        }

        SearchAlgorithm algorithm = CoastGuard.getAlgorithm(strategy);
        Node goalState = algorithm.search(ships, boat, gridObjectTypes, visualize);
        int deaths = goalState.getState().getDeaths() ;
        int retrieved = goalState.getState().getRetrieves();
        int steps = 0;
        List<String> actions = new LinkedList<>() ;

        while (goalState.getParent() != null ){
            actions.add(goalState.getLeadingAction().toString()) ;
            steps++;
            goalState = goalState.getParent();
        }
        Collections.reverse(actions);
        String path = String.join(",", actions);

        return path + ";"+deaths+ ";"+retrieved+ ";"+steps  ;
    }

    public static void main(String[] args) {
        //String grid = "11,11;95;4,2;1,0;9,3,51;";
        //String grid="2,2;20;0,0;0,1;1,0,10;";
        // String grid=GridGenerator.genGrid();
        String grid="2,1;50;1,0;1,0;0,0,90;";
        // String grid="9,13;65;6,4;3,8,6,2,2,2;1,12,88,7,4,45,2,9,41,5,8,12,7,12,96;";
        System.out.println(solve(grid,"BF",true));
    }
}

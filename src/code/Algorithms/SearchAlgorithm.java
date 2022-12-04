package code.Algorithms;

import code.grid.Grid;
import code.state.Node;
import code.grid.RescueBoat;


import java.util.HashSet;


public abstract class SearchAlgorithm {

    public HashSet<String> previousStates = new HashSet<>() ;

    public int expandedNodes;
    public abstract Node search (RescueBoat boat, Grid grid, boolean visualize)  ;


}

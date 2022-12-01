package code.Algorithms;

import code.state.Node;
import code.grid.GridObjectType;
import code.grid.RescueBoat;
import code.grid.Ship;

import java.util.HashSet;
import java.util.List;

public abstract class SearchAlgorithm {

    public HashSet<String> previousStates = new HashSet<>() ;
    public abstract Node search (List<Ship> ships, RescueBoat boat, GridObjectType [][] grid, boolean visualize)  ;


}

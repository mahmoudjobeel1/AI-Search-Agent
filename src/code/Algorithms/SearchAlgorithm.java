package code.Algorithms;

import code.grid.Grid;
import code.state.Node;
import code.grid.RescueBoat;


import java.util.Comparator;
import java.util.HashSet;


public abstract class SearchAlgorithm {

    public HashSet<String> previousStates = new HashSet<>() ;

    public int expandedNodes;

    public String strategy;
    public abstract Node search (RescueBoat boat, Grid grid, boolean visualize)  ;

    public Comparator<Node> getComparableFunction(){
        switch (strategy){
            case "GR1": return Comparator.comparingInt(Node::h1);
            case "GR2": return Comparator.comparingInt(Node::h3);
            case "AS1" : return Comparator.comparingInt(Node::aStar1);
            default: return Comparator.comparingInt(Node::aStar2);
        }
    }
}

package code.Algorithms;

import code.node.Node;
import code.state.State;


import java.util.Comparator;
import java.util.HashSet;


public abstract class SearchAlgorithm {

    public HashSet<String> previousStates = new HashSet<>() ;

    public int expandedNodes;

    public abstract Node search (State initialState,String strategy , boolean visualize)  ;

}

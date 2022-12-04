package code.Algorithms;

import code.grid.Grid;
import code.state.Node;
import code.state.State;
import code.grid.RescueBoat;


import java.util.HashSet;
import java.util.LinkedList;

import java.util.Queue;

public class BFS extends SearchAlgorithm {

    @Override
    public Node search(RescueBoat boat, Grid grid, boolean visualize) {

        this.previousStates = new HashSet<>();
        Queue<Node> queue = new LinkedList<>() ;
        State initialState = new State(boat,grid);
        Node rootNode = new Node(initialState, null) ;
        previousStates.add(rootNode.toString());

        queue.add(rootNode) ;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll() ;
            this.expandedNodes++;

            if (currentNode.getState().checkGoalTest()) return currentNode;

            if(visualize) currentNode.getState().gridVisualization();

            Node up = currentNode.up() ;
            Node down = currentNode.down() ;
            Node left = currentNode.left() ;
            Node right = currentNode.right();
            Node pickup = currentNode.pickup() ;
            Node drop = currentNode.drop() ;
            Node retrieve=currentNode.retrieve();

           if(up!=null && previousStates.add(up.toString())) queue.add(up);
           if(down!=null && previousStates.add(down.toString())) queue.add(down);
           if(left!=null && previousStates.add(left.toString())) queue.add(left);
           if(right!=null && previousStates.add(right.toString())) queue.add(right);
           if(pickup!=null && previousStates.add(pickup.toString())) queue.add(pickup);
           if(drop!=null && previousStates.add(drop.toString())) queue.add(drop);
           if(retrieve!=null && previousStates.add(retrieve.toString())) queue.add(retrieve);


        }
        return null;
    }


}

package code.Algorithms;

import code.grid.Grid;
import code.state.Node;
import code.grid.RescueBoat;
import code.state.State;

import java.util.*;

public class GR1 extends SearchAlgorithm{

    @Override
    public Node search(RescueBoat boat, Grid grid, boolean visualize) {
        Stack<Node> stack = new Stack<>() ;
        State initialState = new State(boat,grid);
        Node rootNode = new Node(initialState, null) ;
        previousStates.add(rootNode.toString());

        stack.add(rootNode) ;

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            expandedNodes++;

            if (currentNode.getState().checkGoalTest()) return currentNode;

            if(visualize) currentNode.getState().gridVisualization();

            Node up = currentNode.up() ;
            Node down = currentNode.down() ;
            Node left = currentNode.left() ;
            Node right = currentNode.right();
            Node pickup = currentNode.pickup() ;
            Node drop = currentNode.drop() ;
            Node retrieve=currentNode.retrieve();
            ArrayList<Node> moves = new ArrayList<>();

            if(retrieve!=null && previousStates.add(retrieve.toString())) moves.add(retrieve);
            if(drop!=null && previousStates.add(drop.toString())) moves.add(drop);
            if(pickup!=null && previousStates.add(pickup.toString())) moves.add(pickup);
            if(right!=null && previousStates.add(right.toString())) moves.add(right);
            if(left!=null && previousStates.add(left.toString())) moves.add(left);
            if(down!=null && previousStates.add(down.toString())) moves.add(down);
            if(up!=null && previousStates.add(up.toString())) moves.add(up);

            Collections.sort(moves,(a, b) -> grid.distance(b,1) - grid.distance(a,1));
            stack.addAll(moves);
        }
        return null;
    }

}

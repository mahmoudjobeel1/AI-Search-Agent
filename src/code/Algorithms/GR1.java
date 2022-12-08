package code.Algorithms;

import code.grid.Grid;
import code.state.Node;
import code.grid.RescueBoat;
import code.state.State;

import java.util.*;

public class GR1 extends SearchAlgorithm{

    @Override
    public Node search(RescueBoat boat, Grid grid, boolean visualize) {

        State initialState = new State(boat, grid);
        Node rootNode = new Node(initialState, null);
        previousStates.add(rootNode.toString());
        Node currentNode=rootNode;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::heuristicGR1));

        while (currentNode!=null) {
            expandedNodes++;

            if (currentNode.getState().checkGoalTest()) return currentNode;

            if (visualize) currentNode.getState().gridVisualization();

            Node up = currentNode.up();
            Node down = currentNode.down();
            Node left = currentNode.left();
            Node right = currentNode.right();
            Node pickup = currentNode.pickup();
            Node drop = currentNode.drop();
            Node retrieve = currentNode.retrieve();

            boolean f=true;
            if (retrieve != null && previousStates.add(retrieve.toString())){ currentNode=retrieve; f=false;}
            if (drop != null && previousStates.add(drop.toString())) {currentNode=drop; f=false;}
            if (pickup != null && previousStates.add(pickup.toString())) {currentNode=pickup; f=false;}

            if (right != null && previousStates.add(right.toString())) priorityQueue.add(right);
            if (left != null && previousStates.add(left.toString())) priorityQueue.add(left);
            if (down != null && previousStates.add(down.toString())) priorityQueue.add(down);
            if (up != null && previousStates.add(up.toString())) priorityQueue.add(up);

            if(f)
                currentNode= priorityQueue.poll();

        }
        return null;
    }
}

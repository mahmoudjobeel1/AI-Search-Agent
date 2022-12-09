package code.Algorithms;

import code.grid.Grid;
import code.grid.RescueBoat;
import code.state.Node;
import code.state.State;

import java.util.PriorityQueue;

public class BestFirstSearch extends SearchAlgorithm{
    @Override
    public Node search(RescueBoat boat, Grid grid, boolean visualize) {
        State initialState = new State(boat, grid);
        Node rootNode = new Node(initialState, null);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(getComparableFunction());
        priorityQueue.add(rootNode) ;
        previousStates.add(rootNode.toString());

        while (!priorityQueue.isEmpty()) {

            Node currentNode= priorityQueue.poll();
            expandedNodes++;

            if (currentNode.getState().checkGoalTest()) return currentNode;

            if (visualize){
                currentNode.getState().gridVisualization();
            }

            Node up = currentNode.up();
            Node down = currentNode.down();
            Node left = currentNode.left();
            Node right = currentNode.right();
            Node pickup = currentNode.pickup();
            Node drop = currentNode.drop();
            Node retrieve = currentNode.retrieve();



            if (retrieve != null && previousStates.add(retrieve.toString())) priorityQueue.add(retrieve);
            if (drop != null && previousStates.add(drop.toString())) priorityQueue.add(drop) ;
            if (pickup != null && previousStates.add(pickup.toString())) priorityQueue.add(pickup) ;
            if (right != null && previousStates.add(right.toString())) priorityQueue.add(right);
            if (left != null && previousStates.add(left.toString())) priorityQueue.add(left);
            if (down != null && previousStates.add(down.toString())) priorityQueue.add(down);
            if (up != null && previousStates.add(up.toString())) priorityQueue.add(up);

        }
        return null;
    }
}

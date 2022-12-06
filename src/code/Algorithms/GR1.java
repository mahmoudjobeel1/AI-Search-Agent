package code.Algorithms;

import code.grid.Grid;
import code.state.Node;
import code.grid.RescueBoat;
import code.state.State;

import java.util.*;

public class GR1 extends SearchAlgorithm{

    @Override
    public Node search(RescueBoat boat, Grid grid, boolean visualize) {
        Queue<Node> queue=new LinkedList<>();
        State initialState = new State(boat, grid);
        Node rootNode = new Node(initialState, null);
        previousStates.add(rootNode.toString());

        queue.add(rootNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            expandedNodes++;

            if (currentNode.getState().checkGoalTest()) return currentNode;

            if (visualize){
                currentNode.getState().gridVisualization();
                System.out.println(currentNode.getState().getBoat().toString());
            }

            Node up = currentNode.up();
            Node down = currentNode.down();
            Node left = currentNode.left();
            Node right = currentNode.right();
            Node pickup = currentNode.pickup();
            Node drop = currentNode.drop();
            Node retrieve = currentNode.retrieve();


            PriorityQueue<Node> stack = new PriorityQueue<>((a, b) -> currentNode.getState().getGrid().distance(a, 1) - currentNode.getState().getGrid().distance(b, 1));
            if (retrieve != null && previousStates.add(retrieve.toString())){ queue.add(retrieve); continue;}
            if (drop != null && previousStates.add(drop.toString())) {queue.add(drop); continue;}
            if (pickup != null && previousStates.add(pickup.toString())) {queue.add(pickup); continue;}
            if (right != null && previousStates.add(right.toString())) {
                stack.add(right);
            }
            if (left != null && previousStates.add(left.toString())) {stack.add(left);}
            if (down != null && previousStates.add(down.toString())) {stack.add(down);}
            if (up != null && previousStates.add(up.toString())) {stack.add(up);}

            if(!stack.isEmpty())
                queue.add(stack.poll());

        }
        return null;
    }
}

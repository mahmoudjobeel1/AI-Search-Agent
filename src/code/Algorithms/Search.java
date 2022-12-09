package code.Algorithms;

import code.node.Node;
import code.queue.SearchQueue;
import code.state.State;

import java.util.HashSet;

public class Search extends SearchAlgorithm{
    @Override
    public Node search(State initialState, String strategy, boolean visualize) {
        SearchQueue queue=new SearchQueue(strategy);
        previousStates=new HashSet<>();

        Node rootNode = new Node(initialState, null);

        queue.add(rootNode) ;
        previousStates.add(rootNode.toString());

        while (!queue.isEmpty()) {

            Node currentNode= queue.poll();
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


            if (right != null && previousStates.add(right.toString())) queue.add(right);
            if (left != null && previousStates.add(left.toString())) queue.add(left);
            if (down != null && previousStates.add(down.toString())) queue.add(down);
            if (up != null && previousStates.add(up.toString())) queue.add(up);
            if (retrieve != null && previousStates.add(retrieve.toString())) queue.add(retrieve);
            if (drop != null && previousStates.add(drop.toString())) queue.add(drop) ;
            if (pickup != null && previousStates.add(pickup.toString())) queue.add(pickup) ;

        }
        return null;
    }
}

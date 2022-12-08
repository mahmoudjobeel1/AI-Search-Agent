package code.Algorithms;

import code.grid.Grid;
import code.state.Node;
import code.grid.RescueBoat;
import code.state.State;

import java.util.HashSet;
import java.util.Stack;

public class ID extends SearchAlgorithm{

    @Override
    public Node search(RescueBoat boat, Grid grid, boolean visualize) {
        int maxDepth =0;
        while(true) {
            Stack<Node> stack = new Stack<>();
            Stack<Integer> depthStack=new Stack<>();
            State initialState = new State(boat, grid);
            Node rootNode = new Node(initialState, null);
            previousStates.add(rootNode.toString());

            stack.add(rootNode);
            depthStack.add(0);

            while (!stack.isEmpty()) {
                Node currentNode = stack.pop();
                int depth=depthStack.pop();

                expandedNodes++;

                if (currentNode.getState().checkGoalTest()) return currentNode;

                if (visualize) currentNode.getState().gridVisualization();

                if(depth< maxDepth) {
                    Node up = currentNode.up();
                    Node down = currentNode.down();
                    Node left = currentNode.left();
                    Node right = currentNode.right();
                    Node pickup = currentNode.pickup();
                    Node drop = currentNode.drop();
                    Node retrieve = currentNode.retrieve();

                    Node temp=null;
                    if (retrieve != null && previousStates.add(retrieve.toString())) {
                        depthStack.add(depth+1);
                        temp=retrieve;
                    }
                    if (drop != null && previousStates.add(drop.toString())) {
                        depthStack.add(depth+1);
                        temp=drop;
                    }
                    if (pickup != null && previousStates.add(pickup.toString())) {
                        depthStack.add(depth+1);
                        temp=pickup;
                    }
                    if (right != null && previousStates.add(right.toString())) {
                        depthStack.add(depth+1);
                        stack.add(right);
                    }
                    if (left != null && previousStates.add(left.toString())){
                        depthStack.add(depth+1);
                        stack.add(left);
                    }
                    if (down != null && previousStates.add(down.toString())) {
                        depthStack.add(depth+1);
                        stack.add(down);
                    }
                    if (up != null && previousStates.add(up.toString())){
                        depthStack.add(depth+1);
                        stack.add(up);
                    }
                    if(temp!=null) stack.push(temp);
                }
            }
            previousStates=new HashSet<>();
            maxDepth++;
        }
    }
}

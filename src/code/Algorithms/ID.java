package code.Algorithms;

import code.node.Node;
import code.state.State;

import java.util.HashSet;
import java.util.Stack;

public class ID extends SearchAlgorithm{

    @Override
    public Node search(State initialState, String strategy, boolean visualize) {
        int maxDepth =0;
        while(true) {
            SearchQueue queue=new SearchQueue(strategy);
            Stack<Integer> depthStack=new Stack<>();
            previousStates=new HashSet<>();

            Node rootNode = new Node(initialState, null);

            previousStates.add(rootNode.toString());
            queue.add(rootNode);
            depthStack.add(0);

            while (!queue.isEmpty()) {
                Node currentNode = queue.poll();
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


                    if (right != null && previousStates.add(right.toString())) {
                        depthStack.add(depth+1);
                        queue.add(right);
                    }
                    if (left != null && previousStates.add(left.toString())){
                        depthStack.add(depth+1);
                        queue.add(left);
                    }
                    if (down != null && previousStates.add(down.toString())) {
                        depthStack.add(depth+1);
                        queue.add(down);
                    }
                    if (up != null && previousStates.add(up.toString())){
                        depthStack.add(depth+1);
                        queue.add(up);
                    }
                    if (retrieve != null && previousStates.add(retrieve.toString())) {
                        depthStack.add(depth+1);
                        queue.add(retrieve);
                    }
                    if (drop != null && previousStates.add(drop.toString())) {
                        depthStack.add(depth+1);
                        queue.add(drop);
                    }
                    if (pickup != null && previousStates.add(pickup.toString())) {
                        depthStack.add(depth+1);
                        queue.add(pickup);
                    }
                }
            }
            maxDepth++;
        }
    }
}

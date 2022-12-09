package code.Algorithms;

import code.CoastGuard;
import code.node.Node;
import code.node.PerformAction;
import code.queue.SearchQueue;
import code.state.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Search {

    private String strategy;
    private State initialState;
    private ArrayList<Object> actionList;
    private HashSet<String> previousStates;
    private int expandedNodes;
    private boolean visualize;

    public Search(String strategy, State initialState, ArrayList<Object> actionList,boolean visualize) {
        this.strategy = strategy;
        this.initialState = initialState;
        this.actionList = actionList;
        this.visualize=visualize;
        expandedNodes=0;
        previousStates=new HashSet<>();
    }

    public String getSolution(){
        Node target=strategy.equals("ID")? ID():search();
        StringBuilder ans=new StringBuilder(target.getGoalTestNodeString()).append(expandedNodes);
        return ans.toString();
    }

    // Perform any search strategy except ID
    public Node search() {
        SearchQueue queue=new SearchQueue(strategy);
        Node rootNode = new Node(initialState, null);
        queue.add(rootNode) ;
        previousStates.add(rootNode.toString());
        while (!queue.isEmpty()) {

            Node currentNode= queue.poll();
            expandedNodes++;

            if (CoastGuard.checkGoalTest(currentNode)) return currentNode;

            if (visualize) currentNode.getState().gridVisualization();

            for(Object action :actionList){
                Node node= PerformAction.perform(currentNode,(String) action);
                if (node != null && previousStates.add(node.toString())) queue.add(node);
            }

        }
        return null;
    }

    public Node ID() {
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

                if (CoastGuard.checkGoalTest(currentNode)) return currentNode;

                if (visualize) currentNode.getState().gridVisualization();

                if(depth< maxDepth) {
                    for(Object action :actionList){
                        Node node= PerformAction.perform(currentNode,(String) action);
                        if (node != null && previousStates.add(node.toString())) {queue.add(node); depthStack.add(depth+1);}
                    }
                }
            }
            maxDepth++;
        }
    }
}

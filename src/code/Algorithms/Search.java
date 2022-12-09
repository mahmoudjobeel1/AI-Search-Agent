package code.Algorithms;

import code.CoastGuard;
import code.node.Node;
import code.node.PerformAction;
import code.queue.SearchQueue;
import code.state.State;

import java.util.ArrayList;
import java.util.HashSet;


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
        Node target=search();
        StringBuilder ans=new StringBuilder(target.getGoalTestNodeString()).append(expandedNodes);
        return ans.toString();
    }

    public Node search() {
        SearchQueue queue=new SearchQueue(strategy);
        Node rootNode = new Node(initialState.clone(), null);
        queue.add(rootNode) ;
        previousStates.add(rootNode.toString());
        int maxDepth=0;
        while (!queue.isEmpty()) {

            Node currentNode= queue.poll();
            expandedNodes++;

            if (CoastGuard.checkGoalTest(currentNode)) return currentNode;

            if (visualize) currentNode.getState().gridVisualization();


            if(!(strategy.equals("ID") && currentNode.getDepth()>= maxDepth)){
                for(Object action :actionList){
                    Node node= PerformAction.perform(currentNode,(String) action);
                    if (node != null && previousStates.add(node.toString())) {queue.add(node);  node.setDepth(currentNode.getDepth()+1);}
                }
            }

            if(strategy.equals("ID") && queue.isEmpty()){
                queue=new SearchQueue(strategy);
                rootNode= new Node(initialState.clone(), null);
                queue.add(rootNode) ;
                previousStates=new HashSet<>();
                previousStates.add(rootNode.toString());
                maxDepth++;
            }
        }
        return null;
    }

}

package code.queue;

import code.node.Node;

import java.util.*;

public class SearchQueue {
    private Queue<Node> queue;
    private String strategy;
    public SearchQueue(String strategy){
        this.strategy=strategy;
        switch (strategy){
            case "BFS": {queue=new LinkedList<>(); break;}
            case "ID":
            case "DF": {queue=new ArrayDeque<>(); break;}
            default: queue=new PriorityQueue<>(getComparableFunction());
        }
    }
    public Comparator<Node> getComparableFunction(){
        switch (strategy){
            case "GR1": return Comparator.comparingInt(Node::h1);
            case "GR2": return Comparator.comparingInt(Node::h3);
            case "AS1" : return Comparator.comparingInt(Node::aStar1);
            default: return Comparator.comparingInt(Node::aStar2);
        }
    }
    public void add(Node node){
        if(strategy.equals("ID") || strategy.equals("DF")){
            ((ArrayDeque)queue).addFirst(node);
            return;
        }
        queue.add(node);
    }
    public Node poll(){
        if(strategy.equals("ID") || strategy.equals("DF")){
            return (Node) ((ArrayDeque)queue).pop();
        }
        return queue.poll();
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    public String toString(){return queue.toString();}
}

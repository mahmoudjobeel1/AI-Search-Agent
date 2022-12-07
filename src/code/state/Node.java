package code.state;

import code.grid.Grid;
import code.grid.RescueBoat;
import code.grid.Ship;
import code.grid.Station;

import java.util.LinkedList;

public class Node {
    private State state ;
    private Node parent;
    private ActionType leadingAction;


    public Node() {}

    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent;
    }

    public Node right () {
        RescueBoat rescueBoat=state.getBoat();
        Grid grid=state.getGrid();

        if (rescueBoat.getY()==grid.getN()-1)
            return null;

        Node clone= new Node(state.clone(),this);
        clone.leadingAction=ActionType.right;
        clone.getState().getBoat().setY(rescueBoat.getY()+1);
        clone.getState().update();

        return clone;
    }

    public Node left() {
        RescueBoat rescueBoat=state.getBoat();

        if (rescueBoat.getY()==0)
            return null;

        Node clone= new Node(state.clone(),this);
        clone.leadingAction=ActionType.left;
        clone.getState().getBoat().setY(rescueBoat.getY()-1);
        clone.getState().update();

        return clone;
    }

    public Node up() {
        RescueBoat rescueBoat=state.getBoat();

        if (rescueBoat.getX()==0)
            return null;

        Node clone= new Node(state.clone(),this);
        clone.leadingAction=ActionType.up;
        clone.getState().getBoat().setX(rescueBoat.getX()-1);
        clone.getState().update();

        return clone;
    }

    public Node down() {
        RescueBoat rescueBoat=state.getBoat();
        Grid grid=state.getGrid();

        if (rescueBoat.getX()==grid.getM()-1)
            return null;

        Node clone= new Node(state.clone(),this);
        clone.leadingAction=ActionType.down;
        clone.getState().getBoat().setX(rescueBoat.getX()+1);
        clone.getState().update();

        return clone;

    }

    public Node pickup() {
        Node clone= new Node(state.clone(),this);
        RescueBoat rescueBoat=clone.state.getBoat();
        Grid grid=clone.state.getGrid();
        Ship ship=grid.getShip(rescueBoat.getX(),rescueBoat.getY());

        if(rescueBoat.isFull() || ship==null || ship.isWrecked())
            return null;

        clone.leadingAction=ActionType.pickup;
        int savedPeople=Math.min(rescueBoat.getAvailableCapacity(),ship.getPassengers());
        ship.setPassengers(ship.getPassengers()-savedPeople);
        rescueBoat.setAvailableCapacity(rescueBoat.getAvailableCapacity()-savedPeople);
        clone.getState().update();
        return clone;
    }

    public Node retrieve(){
        Node clone= new Node(state.clone(),this);
        RescueBoat rescueBoat=clone.state.getBoat();
        Grid grid=clone.state.getGrid();
        Ship ship=grid.getShip(rescueBoat.getX(),rescueBoat.getY());

        if(ship==null || !ship.isWrecked() || ship.getBlackBoxHealth()>=19)
            return null;
        clone.leadingAction=ActionType.retrieve;
        clone.state.setRetrieves(clone.state.getRetrieves()+1);
        grid.removeShip(ship);
        clone.getState().update();
        return clone;
    }

    public Node drop() {
        Node clone= new Node(state.clone(),this);
        RescueBoat rescueBoat=clone.state.getBoat();
        Grid grid=clone.state.getGrid();
        Station station=grid.getStation(rescueBoat.getX(),rescueBoat.getY());
        if(rescueBoat.isEmpty() || station==null) return null;

        clone.leadingAction=ActionType.drop;
        rescueBoat.emptyRescueBoat();
        clone.getState().update();
        return clone;
    }



    public String getActionsPath(){
        LinkedList<String> actionsList=new LinkedList<>();
        Node current=this;
        while(current.parent!=null){
            actionsList.addFirst(current.getLeadingAction().toString());
            current=current.parent;
        }
        StringBuilder path=new StringBuilder();
        while(!actionsList.isEmpty()) path.append(actionsList.pollFirst()).append(",");
        if(!path.isEmpty()) path.deleteCharAt(path.length()-1);
        return path.toString();
    }

    public String getGoalTestNodeString(){
        StringBuilder ans=new StringBuilder();
        ans.append(getActionsPath()).append(";");
        ans.append(state.getDeaths()).append(";");
        ans.append(state.getRetrieves()).append(";");

        return ans.toString();
    }
    public int heuristicGR1() {
        Grid grid = getState().getGrid();
        RescueBoat rescueBoat = getState().getBoat();
        int distance=grid.calculateMinDistanceShip(rescueBoat);
        if(rescueBoat.isFull() || distance==Integer.MAX_VALUE) return grid.calculateMinDistanceStation(rescueBoat);
        return distance;
    }


    public State getState() {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ActionType getLeadingAction() {
        return leadingAction;
    }

    public void setLeadingAction(ActionType leadingAction) {
        this.leadingAction = leadingAction;
    }

    public String toString () {
        return state.toString() ;
    }
}

package code.state;

import code.grid.RescueBoat;

public class Node {
    private State state ;
    private Node parent;
    private ActionType leadingAction;

    public Node() {
    }

    public Node(State state, Node parent) {
        this.state = state;
        this.parent = parent;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
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



    public Node cloneNode () {
        Node clone = new Node();
        clone.state=state;
        clone.leadingAction=leadingAction;
        return clone ;
    }

    public Node right () {
//        if (this.getState().getBoat().getY()==grid[0].length-1)
//            return null;
//
//        Node clone = this.cloneNode() ;
//        RescueBoat rescueBoat = clone.getState().getBoat();
//        rescueBoat.setY(rescueBoat.getY()+1);
//        clone.setLeadingAction(ActionType.right);
//        clone.setParent(this);
        return null ;
    }

    public Node left() {
        if (this.getState().getBoat().getY()==0)
            return null;

        Node clone = this.cloneNode() ;
        RescueBoat rescueBoat = clone.getState().getBoat();
        rescueBoat.setY(rescueBoat.getY()-1);
        clone.setLeadingAction(ActionType.left);
        clone.setParent(this);
        return clone ;
    }

    public Node up() {
//        if (this.getState().getBoat().getX()==grid.length-1)
//            return null;
//
//        Node clone = this.cloneNode() ;
//        RescueBoat rescueBoat = clone.getState().getBoat();
//        rescueBoat.setX(rescueBoat.getX()+1);
//        clone.setLeadingAction(ActionType.up);
//        clone.setParent(this);
        return null;
    }

    public Node down() {
        if (this.getState().getBoat().getX()==0)
            return null;

        Node clone = this.cloneNode() ;
        RescueBoat rescueBoat = clone.getState().getBoat();
        rescueBoat.setX(rescueBoat.getX()-1);
        clone.setLeadingAction(ActionType.down);
        clone.setParent(this);
        return clone;
    }

    public Node pickup() {
//        if (!grid[this.getState().getBoat().getX()]
//                [this.getState().getBoat().getY()].equals(GridObjectType.SHIP) ||
//            this.getState().getRemainingShips().size() == 0
//        )
//            return null;
//
//        GridPosition shipPosition = this.getState().getBoat();
//        Node clone = this.cloneNode() ;
//        Ship currentShip = null ;
//        for (Ship ship: clone.getState().getRemainingShips()){
//            if (ship.getPosition().getX() == shipPosition.getX() &&
//                    ship.getPosition().getY() == shipPosition.getY()) {
//                currentShip = ship;
//                break;
//            }
//        }
//        if (!currentShip.isWrecked()){
//            int passengersToSave = Math.min(clone.getState().getBoat().getAvailableCapacity(), currentShip.getPassengers()) ;
//            currentShip.setPassengers(currentShip.getPassengers()-passengersToSave);
//            clone.getState().getBoat().setAvailableCapacity(clone.getState().getBoat().getAvailableCapacity()-passengersToSave);
//            clone.setLeadingAction(ActionType.pickup);
//        }
//        else if (!currentShip.isFullDamaged()) {
//            grid[clone.getState().getBoat().getPosition().getX()][clone.getState().getBoat().getPosition().getY()] = GridObjectType.EMPTY ;
//            clone.getState().getRemainingShips().remove(currentShip);
//            clone.getState().getBoat().setRescuedBlackBoxes(clone.getState().getBoat().getRescuedBlackBoxes()+1);
//            clone.setLeadingAction(ActionType.retrieve);
//            clone.getState().setRetrieves(clone.getState().getRetrieves()+1);
//        }
//        clone.setParent(this);
//        return clone ;
        return null;
    }

    public Node drop() {
//        if (!grid[this.getState().getBoat().getPosition().getX()]
//                 [this.getState().getBoat().getPosition().getY()].equals(GridObjectType.STATION) ||
//            this.getState().getBoat().isEmpty()
//        )
//            return null ;
//
//        Node clone = this.cloneNode() ;
//        clone.getState().getBoat().emptyRescueBoat();
//        clone.setLeadingAction(ActionType.drop);
//        clone.setParent(this);
//        return clone;
        return null;
    }



    public String toString () {
        return state.toString() ;
    }
}

package code.state;

import code.grid.GridObjectType;
import code.grid.GridPosition;
import code.grid.Ship;

public class Node {
    private State state ;
    private Node parent;
    private ActionType leadingAction;

    private GridObjectType [][] grid ;


    public Node() {
    }

    public Node(State state, Node parent, ActionType leadingAction, GridObjectType[][] grid) {
        this.state = state;
        this.parent = parent;
        this.leadingAction = leadingAction;
        this.grid = grid;
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

    public GridObjectType[][] getGrid() {
        return grid;
    }

    public void setGrid(GridObjectType[][] grid) {
        this.grid = grid;
    }

    public Node cloneNode () {
        Node clone = new Node();
        clone.setState(this.state.cloneState());
        clone.setLeadingAction(this.leadingAction);
        GridObjectType cloneGrid [][] = new GridObjectType [grid.length][grid[0].length];
        for (int i  = 0; i<cloneGrid.length; i++){
            for(int j = 0; j<cloneGrid[0].length; j++){
                cloneGrid[i][j] = grid[i][j] ;
            }
        }
        clone.setGrid(cloneGrid);
        return clone ;
    }

    public Node right () {
        if (this.getState().getBoat().getPosition().getY()==grid[0].length-1)
            return null;

        Node clone = this.cloneNode() ;
        GridPosition boatPosition = clone.getState().getBoat().getPosition();
        boatPosition.setY(boatPosition.getY()+1);
        clone.setLeadingAction(ActionType.right);
        clone.setParent(this);
        return clone ;
    }

    public Node left() {
        if (this.getState().getBoat().getPosition().getY()==0)
            return null;

        Node clone = this.cloneNode() ;
        GridPosition boatPosition = clone.getState().getBoat().getPosition();
        boatPosition.setY(boatPosition.getY()-1);
        clone.setLeadingAction(ActionType.left);
        clone.setParent(this);
        return clone ;
    }

    public Node up() {
        if (this.getState().getBoat().getPosition().getX()==grid.length-1)
            return null;

        Node clone = this.cloneNode() ;
        GridPosition boatPosition = clone.getState().getBoat().getPosition();
        boatPosition.setX(boatPosition.getX()+1);
        clone.setLeadingAction(ActionType.up);
        clone.setParent(this);
        return clone;
    }

    public Node down() {
        if (this.getState().getBoat().getPosition().getX()==0)
            return null;

        Node clone = this.cloneNode() ;
        GridPosition boatPosition = clone.getState().getBoat().getPosition() ;
        boatPosition.setX(boatPosition.getX()-1);
        clone.setLeadingAction(ActionType.down);
        clone.setParent(this);
        return clone;
    }

    public Node pickup() {
        if (!grid[this.getState().getBoat().getPosition().getX()]
                [this.getState().getBoat().getPosition().getY()].equals(GridObjectType.SHIP) ||
            this.getState().getRemainingShips().size() == 0
        )
            return null;

        GridPosition shipPosition = this.getState().getBoat().getPosition();
        Node clone = this.cloneNode() ;
        Ship currentShip = null ;
        for (Ship ship: clone.getState().getRemainingShips()){
            if (ship.getPosition().getX() == shipPosition.getX() &&
                    ship.getPosition().getY() == shipPosition.getY()) {
                currentShip = ship;
                break;
            }
        }
        if (!currentShip.isWrecked()){
            int passengersToSave = Math.min(clone.getState().getBoat().getAvailableCapacity(), currentShip.getPassengers()) ;
            currentShip.setPassengers(currentShip.getPassengers()-passengersToSave);
            clone.getState().getBoat().setAvailableCapacity(clone.getState().getBoat().getAvailableCapacity()-passengersToSave);
            clone.setLeadingAction(ActionType.pickup);
        }
        else if (!currentShip.isFullDamaged()) {
            grid[clone.getState().getBoat().getPosition().getX()][clone.getState().getBoat().getPosition().getY()] = GridObjectType.EMPTY ;
            clone.getState().getRemainingShips().remove(currentShip);
            clone.getState().getBoat().setRescuedBlackBoxes(clone.getState().getBoat().getRescuedBlackBoxes()+1);
            clone.setLeadingAction(ActionType.retrieve);
            clone.getState().setRetrieves(clone.getState().getRetrieves()+1);
        }
        clone.setParent(this);
        return clone ;
    }

    public Node drop() {
        if (!grid[this.getState().getBoat().getPosition().getX()]
                 [this.getState().getBoat().getPosition().getY()].equals(GridObjectType.STATION) ||
            this.getState().getBoat().isEmpty()
        )
            return null ;

        Node clone = this.cloneNode() ;
        clone.getState().getBoat().emptyRescueBoat();
        clone.setLeadingAction(ActionType.drop);
        clone.setParent(this);
        return clone;
    }



    public String toString () {
        return state.toString() ;
    }
}

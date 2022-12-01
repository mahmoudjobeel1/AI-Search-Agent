package code.state;

import code.grid.RescueBoat;
import code.grid.Ship;

import java.util.ArrayList;
import java.util.List;

public class BFSState extends State{


    public BFSState() {
        super();
    }
    public BFSState(List<Ship> remainingShips, RescueBoat boat) {
        super(remainingShips, boat);
    }


    @Override
    public State cloneState () {
        State clone = new BFSState() ;
        clone.setBoat(this.getBoat().clone());
        List<Ship> cloneShips = new ArrayList<>() ;
        for (Ship ship: this.getRemainingShips()) {
            cloneShips.add(ship.clone()) ;
        }
        clone.setRemainingShips(cloneShips);
        clone.setDeaths(this.getDeaths());
        clone.setRetrieves(this.getRetrieves());
        return clone;
    }

}

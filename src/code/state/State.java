package code.state;

import code.grid.RescueBoat;
import code.grid.Ship;

import java.util.List;

public abstract class State {
    // state representation <remainingShips, boatObject, actionLeadingToThisState>
    private List<Ship> remainingShips ;
    private RescueBoat boat ;
    private int deaths ;
    private int retrieves;



    public State() {

    }
    public State(List<Ship> remainingShips, RescueBoat boat) {
        this.remainingShips = remainingShips;
        this.boat = boat;
    }

    public abstract State cloneState () ;

    public boolean checkGoalTest(){
        return remainingShips.isEmpty() && boat.isEmpty();
    }

    public List<Ship> getRemainingShips() {
        return remainingShips;
    }

    public void setRemainingShips(List<Ship> remainingShips) {
        this.remainingShips = remainingShips;
    }

    public RescueBoat getBoat() {
        return boat;
    }

    public void setBoat(RescueBoat boat) {
        this.boat = boat;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRetrieves() {
        return retrieves;
    }

    public void setRetrieves(int retrieves) {
        this.retrieves = retrieves;
    }

    public String toString () {
        return remainingShips.toString() + "\n" + boat.toString() ;
    }

}

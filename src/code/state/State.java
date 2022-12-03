package code.state;

import code.grid.Grid;
import code.grid.RescueBoat;
import code.grid.Ship;

import java.util.List;

public class State {
    // state representation <remainingShips, boatObject, actionLeadingToThisState>
    private Grid grid;
    private RescueBoat boat ;
    private int deaths ;
    private int retrieves;

    public State() {}

    public State(RescueBoat boat,Grid grid) {
        this.grid=grid;
        this.boat = boat;
    }

    public boolean checkGoalTest(){
        return grid.isEmpty() && boat.isEmpty();
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

    public Grid getGrid() {
        return grid;
    }

    @Override
    public String toString() {
        return "State{" +
                "grid=" + grid +
                ", boat=" + boat +
                ", deaths=" + deaths +
                ", retrieves=" + retrieves +
                '}';
    }
}

package code.state;

import code.grid.Grid;
import code.grid.RescueBoat;
import code.grid.Ship;
import code.grid.Station;

public class State {
    // state representation <remainingShips, boatObject, actionLeadingToThisState>
    private Grid grid;
    private RescueBoat boat ;
    private int deaths ;
    private int damagedBoxes ;

    private int currentDamages ;
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

    public int getDamagedBoxes() {
        return damagedBoxes;
    }

    public void setDamagedBoxes(int damagedBoxes) {
        this.damagedBoxes = damagedBoxes;
    }

    public int getCurrentDamages() {
        return currentDamages;
    }

    public void setCurrentDamage(int currentDamages) {
        this.currentDamages = currentDamages;
    }

    public void update(){
        int updates [] = grid.update();
        setDeaths(getDeaths() + updates[0]);
        setDamagedBoxes(getDamagedBoxes() + updates[1]);
        setCurrentDamage(updates[2]);
    }

    public State clone(){
        State clone=new State();
        clone.grid=grid.clone();
        clone.boat=boat.clone();
        clone.deaths=deaths;
        clone.retrieves=retrieves;
        return clone;
    }

    public void gridVisualization(){
        String rescueBoat=boat.getX()+" "+boat.getY();
        for(int i=0;i<grid.getM();i++){
            for(int j=0;j<grid.getN();j++){
                Object o=grid.getShip(i,j)==null ? grid.getStation(i,j): grid.getShip(i,j);
                String str="_";
                if(o instanceof Ship){
                    Ship ship=(Ship) o;
                    if(!ship.isWrecked()){
                        str="Sh("+ship.getPassengers()+")";
                    }else{
                        str="Wr("+ship.getBlackBoxHealth()+")";
                    }
                }else if(o instanceof Station) str="St";
                if((i+" "+j).equals(rescueBoat)){
                    if(str.equals("_")) System.out.format("%-15s", "B");
                    else System.out.format("%-15s", str+"+B");
                }else{
                    System.out.format("%-15s", str);
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("******************************************");
        System.out.println();
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

package code.grid;


import code.state.ActionType;
import code.state.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grid {
    private int m,n;
    private HashMap<String,Ship> shipsHashMap;
    private HashMap<String,Station> stationsHashMap;

    public Grid(){}

    public Grid(int m, int n){
        this.m=m;
        this.n=n;
        shipsHashMap=new HashMap<>();
        stationsHashMap=new HashMap<>();
    }

    public Ship getShip(int x,int y){return shipsHashMap.get(x+" "+y);}

    public void removeShip(Ship ship){
        shipsHashMap.remove(ship.getX()+" "+ship.getY());
    }

    public Station getStation(int x,int y){return stationsHashMap.get(x+" "+y);}

    public void addShip(Ship ship){shipsHashMap.put(ship.getX()+" "+ship.getY(),ship);}

    public void addStation(Station station){stationsHashMap.put(station.getX()+" "+station.getY(),station);}

    private HashMap<String,Ship> cloneShipsHashMap(){
        HashMap<String,Ship> clone=new HashMap<>();
        for(String key:shipsHashMap.keySet()) clone.put(key,shipsHashMap.get(key).clone());
        return clone;
    }

    public boolean isEmpty(){return shipsHashMap.isEmpty();}

    // return how many people dead in this update
    public int update(){
        int deaths=0;
        List<String> damagedShips=new ArrayList<>();
        for(String key:shipsHashMap.keySet()) {
            Ship ship=shipsHashMap.get(key);
            if(!ship.isWrecked()) deaths++;
            ship.update();
            if(ship.isFullDamaged()) damagedShips.add(key);
        }
        for(String key:damagedShips) shipsHashMap.remove(key);
        return deaths;
    }


    private int calculateDistance(RescueBoat b, String s){
        String[] loc = ((String)s).split(" ");
        return Math.abs(Integer.parseInt(loc[0]) - b.getX()) + Math.abs(Integer.parseInt(loc[1]) - b.getY());
    }

    public int calculateMinDistanceStation(RescueBoat b) {
        int d = Integer.MAX_VALUE;
        for(String key: stationsHashMap.keySet()) {
            d = Math.min(d, calculateDistance(b, key));
        }
        return d;
    }
    public int calculateMinDistanceShip(RescueBoat rescueBoat) {
        int dShip = Integer.MAX_VALUE;
        for(String key: shipsHashMap.keySet()) {
            int distance=calculateDistance(rescueBoat, key);
            dShip = Math.min(dShip, distance);
        }
        return dShip;
    }

    public int getHighestNumOfPossibleSavedPassengers(RescueBoat rescueBoat){
        int max=Integer.MIN_VALUE;
        for(String key:shipsHashMap.keySet()){
            int distance=calculateDistance(rescueBoat, key);
            Ship ship=shipsHashMap.get(key);
            max=Math.max(max,ship.getPassengers()-distance);
        }
        return max;
    }

    public Grid clone(){
        Grid clone=new Grid();
        clone.m=m;
        clone.n=n;
        clone.shipsHashMap=cloneShipsHashMap();
        clone.stationsHashMap=stationsHashMap;
        return clone;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "m=" + m +
                ", n=" + n +
                ", shipsHashMap=" + shipsHashMap +
                ", stationsHashMap=" + stationsHashMap +
                '}';
    }
}

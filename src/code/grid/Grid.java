package code.grid;

import java.util.HashMap;

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

    public Station getStation(int x,int y){return stationsHashMap.get(x+" "+y);}

    public void addShip(Ship ship){shipsHashMap.put(ship.getX()+" "+ship.getY(),ship);}

    public void addStation(Station station){stationsHashMap.put(station.getX()+" "+station.getY(),station);}

    private HashMap<String,Ship> cloneShipsHashMap(){
        HashMap<String,Ship> clone=new HashMap<>();
        for(String key:shipsHashMap.keySet()) clone.put(key,shipsHashMap.get(key).clone());
        return clone;
    }

    public boolean isEmpty(){return shipsHashMap.isEmpty();}

    public void update(){
        for(String key:shipsHashMap.keySet()) shipsHashMap.get(key).update();
    }

    public Grid clone(){
        Grid clone=new Grid();
        clone.m=m;
        clone.n=n;
        clone.shipsHashMap=cloneShipsHashMap();
        clone.stationsHashMap=stationsHashMap;
        return clone;
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

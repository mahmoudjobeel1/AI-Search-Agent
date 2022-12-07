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

    public int distance(Node n, int i) {
        if(n.getState().getBoat().isFull() || shipsHashMap.isEmpty()){
            return calculateMinDistanceStation(n.getState().getBoat());
        }
        return i==1? calculateMinDistanceShip(n.getState().getBoat()):calculateMaxPass(n.getState().getBoat(), shipsHashMap);
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
    public int calculateMinDistanceShip(RescueBoat b) {
        int dShip = Integer.MAX_VALUE;
        int dWreck= Integer.MAX_VALUE;
        for(String key: shipsHashMap.keySet()) {
            Ship ship=shipsHashMap.get(key);
            int distance=calculateDistance(b, key);
            if(!ship.isWrecked())
                dShip = Math.min(dShip, distance);
            else dWreck = Math.min(dWreck, distance);
        }
        return dShip==Integer.MAX_VALUE? dWreck:dShip;
    }

    private Integer calculateMaxPass(RescueBoat b, HashMap<String,Ship> h) {
        int n = Integer.MIN_VALUE;
        Ship s = null;
        for(String st: h.keySet()){
            int d = calculateDistance(b,st);
            if(n > Math.max(0, h.get(st).getPassengers() - d)*-1 || n > Math.max(0, h.get(st).getBlackBoxHealth() - d)){
                s = h.get(st);
                n = Math.min(s.getPassengers()*-1, s.getBlackBoxHealth());
            }
        }
        if(s == null)
            return null;
        String st = s.getX()+ " " + s.getY();
        return calculateDistance(b,st);
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

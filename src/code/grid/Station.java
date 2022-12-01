package code.grid;

public class Station {
    private GridPosition position ;
    private int rescuedPassengers ;

    private int rescuedBlackBox;

    public Station(GridPosition position) {
        this.position = position;
    }

    public int getRescuedPassengers() {
        return rescuedPassengers;
    }

    public void setRescuedPassengers(int rescuedPassengers) {
        this.rescuedPassengers += rescuedPassengers;
    }

    public GridPosition getPosition() {
        return position;
    }

    public int getRescuedBlackBox() {
        return rescuedBlackBox;
    }

    public void setRescuedBlackBox(int rescuedBlackBox) {
        this.rescuedBlackBox += rescuedBlackBox;
    }

    public String toString(){
        return "GridObjects.Station Position: "+position+" Rescued Passengers: "+rescuedPassengers+"\n";
    }
}
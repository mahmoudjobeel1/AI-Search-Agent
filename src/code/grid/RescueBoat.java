package code.grid;

public class RescueBoat {
    private GridPosition position;
    private int capacity ;
    private int rescuedPassengers;
    private int rescuedBlackBoxes ;
    private int availableCapacity;

    public RescueBoat () {

    }
    public RescueBoat(GridPosition position, int capacity) {
        this.position = position;
        this.capacity = capacity;
        this.availableCapacity = capacity;
    }

    public boolean isFull () {
        return this.rescuedPassengers == capacity;
    }
    public boolean isEmpty(){
        return rescuedPassengers==0;
    }

    public void emptyRescueBoat(){
        rescuedPassengers=0;
        availableCapacity=capacity;
    }


    public RescueBoat clone () {
        RescueBoat clone = new RescueBoat() ;
        clone.setPosition(this.position.clone());
        clone.setCapacity(this.capacity);
        clone.setRescuedPassengers(this.rescuedPassengers);
        clone.setAvailableCapacity(this.availableCapacity) ;
        return clone ;
    }

    public GridPosition getPosition() {
        return position;
    }

    public void setPosition(GridPosition position) {
        this.position = position;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRescuedPassengers() {
        return rescuedPassengers;
    }

    // This method updates the number of passengers on board and returns the number of saved passengers.
    // If it did not succeed, it will return -1.
    public void setRescuedPassengers(int rescuedPassengers) {
        this.rescuedPassengers = rescuedPassengers ;
        this.availableCapacity = capacity -rescuedPassengers ;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity (int availableCapacity){
        this.availableCapacity = availableCapacity;
        this.rescuedPassengers = capacity - availableCapacity;
    }

    public int getRescuedBlackBoxes() {
        return rescuedBlackBoxes;
    }

    public void setRescuedBlackBoxes(int rescuedBlackBoxes) {
        this.rescuedBlackBoxes = rescuedBlackBoxes;
    }

    public String toString(){
        return "Rescue Boat Position: "+position+" |Full capacity: "+capacity +" |Available capacity: "+availableCapacity+" |Num of rescued passengers: "+rescuedPassengers+"\n";
    }
}

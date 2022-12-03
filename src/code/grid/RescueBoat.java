package code.grid;

public class RescueBoat {
    private int capacity ;
    private int rescuedPassengers;
    private int rescuedBlackBoxes ;
    private int availableCapacity;

    private int x, y;

    public RescueBoat () {

    }
    public RescueBoat(int x,int y, int capacity) {
        this.x = x;
        this.y=y;
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
        clone.x=x;
        clone.y=y;
        clone.setCapacity(this.capacity);
        clone.setRescuedPassengers(this.rescuedPassengers);
        clone.setAvailableCapacity(this.availableCapacity) ;
        return clone ;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "RescueBoat{" +
                "capacity=" + capacity +
                ", rescuedPassengers=" + rescuedPassengers +
                ", rescuedBlackBoxes=" + rescuedBlackBoxes +
                ", availableCapacity=" + availableCapacity +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

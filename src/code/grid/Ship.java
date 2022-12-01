package code.grid;


public class Ship {

    private GridPosition position;
    private int passengers ;
    private int blackBoxHealth ;
    private int deadPassengers;

    private final int blackBoxFullHealth  = 20 ;

    public Ship() {

    }

    public Ship (GridPosition position, int passengers) {
        this.position = position;
        this.passengers = passengers;
    }

    public void update(){
       /* The ship is considered wrecked when all of them are expired or all are picked up
        When the ship becomes a wreck (once its last passenger
        either expires or is picked up), in the next time step, the black box starts counting
        damage from 1 all the way up to 20. Once damage reaches 20, the black box is no
        longer retrievable */
        if(isFullDamaged())
            return;

        if(isWrecked()){
            this.blackBoxHealth += 1;
        }else{
            this.passengers -= 1;
            this.deadPassengers += 1;
        }
    }

    public Ship clone () {
        Ship clone = new Ship() ;
        clone.position = this.position.clone() ;
        clone.passengers = this.passengers ;
        clone.deadPassengers = this.deadPassengers ;
        clone.blackBoxHealth = this.blackBoxHealth ;
        return clone ;
    }

    public boolean isWrecked() {
        return this.passengers == 0;
    }

    public boolean isFullDamaged() {
        return this.passengers == 0 && this.blackBoxHealth == blackBoxFullHealth ;
    }

    public GridPosition getPosition() {
        return position;
    }

    public void setPosition(GridPosition position) {
        this.position = position;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getDeadPassengers() {
        return deadPassengers;
    }

    public void setDeadPassengers(int deadPassengers) {
        this.deadPassengers=deadPassengers;
    }

    public int getBlackBoxHealth() {
        return blackBoxHealth;
    }

    public String toString(){
        return "Ship Position: "+position+" |Num of passengers: "+passengers+" |BlackBox health: "+blackBoxHealth+" |Is Wrecked: "+isWrecked()+"\n";
    }
}

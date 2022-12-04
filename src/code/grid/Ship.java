package code.grid;


public class Ship {

    private int passengers ;
    private int blackBoxHealth ;

    private int x,y;

    private final int blackBoxFullHealth  = 20 ;

    public Ship() {

    }

    public Ship (int x,int y, int passengers) {
        this.x=x;
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
        }
    }

    public Ship clone () {
        Ship clone = new Ship() ;
        clone.x=x;
        clone.y=y;
        clone.passengers = this.passengers ;
        clone.blackBoxHealth = this.blackBoxHealth ;
        return clone ;
    }

    public boolean isWrecked() {
        return this.passengers == 0;
    }

    public boolean isFullDamaged() {
        return this.passengers == 0 && this.blackBoxHealth == blackBoxFullHealth ;
    }


    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getBlackBoxHealth() {
        return blackBoxHealth;
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
        return "Ship{" +
                "passengers=" + passengers +
                ", blackBoxHealth=" + blackBoxHealth +
                ", x=" + x +
                ", y=" + y +
                ", blackBoxFullHealth=" + blackBoxFullHealth +
                '}';
    }
}

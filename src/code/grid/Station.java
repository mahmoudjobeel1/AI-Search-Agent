package code.grid;

public class Station {
    private int rescuedPassengers ;

    private int rescuedBlackBox;

    private  int x,y;
    public Station(int x,int y) {
        this.x=x;
        this.y=y;
    }

    public int getRescuedPassengers() {
        return rescuedPassengers;
    }

    public void setRescuedPassengers(int rescuedPassengers) {
        this.rescuedPassengers += rescuedPassengers;
    }


    public int getRescuedBlackBox() {
        return rescuedBlackBox;
    }

    public void setRescuedBlackBox(int rescuedBlackBox) {
        this.rescuedBlackBox += rescuedBlackBox;
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
        return "Station{" +
                "rescuedPassengers=" + rescuedPassengers +
                ", rescuedBlackBox=" + rescuedBlackBox +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
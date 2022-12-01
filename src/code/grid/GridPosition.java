package code.grid;

public class GridPosition {
    private int x;
    private int y;

    public GridPosition () {

    }
    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isValid(int x,int y, int m, int n){
        return x>=0 && x<m && y>=0 && y<n;
    }

    public GridPosition clone () {
        GridPosition clone = new GridPosition() ;
        clone.setX(this.x) ;
        clone.setY(this.y);
        return clone ;
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

    public String toString(){
        return x+" , "+y;
    }
}
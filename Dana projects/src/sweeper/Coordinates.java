package sweeper;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Coordinates){
            Coordinates t = (Coordinates) o;
            return t.x == x && t.y ==y;
        }
        return super.equals(o);
    }
}

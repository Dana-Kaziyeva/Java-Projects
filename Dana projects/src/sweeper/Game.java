package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;

    public Game(int col, int row, int total){
        Ranges.setSize(new Coordinates(col, row));
        bomb = new Bomb(total);
        flag = new Flag();
    }

    public void start(){
        bomb.start();
        flag.start();

    }

    public Box getBox(Coordinates c){
        if(flag.get(c) == Box.OPENED)
            return bomb.get(c);
        return flag.get(c);

    }

    public void pressLeft(Coordinates c){
        flag.setOpen(c);
    }

    public void pressRight(Coordinates c){
        flag.toggleFlag(c);
    }
}

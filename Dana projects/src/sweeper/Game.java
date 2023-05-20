package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;

    private GameState state;

    public GameState getState() {
        return state;
    }

    public Game(int col, int row, int total){
        Ranges.setSize(new Coordinates(col, row));
        bomb = new Bomb(total);
        flag = new Flag();
    }

    public void start(){
        bomb.start();
        flag.start();
        state = GameState.PLAYED;

    }

    public Box getBox(Coordinates c){
        if(flag.get(c) == Box.OPENED)
            return bomb.get(c);
        return flag.get(c);

    }

    public void pressLeft(Coordinates c){
        if(gameOver()) return;
        openBox(c);
        checkWinner();
    }

    private void checkWinner(){
        if (state == GameState.PLAYED){
            if(flag.getNumOfClosed() == bomb.getTotal()){
                state = GameState.WINNER;
            }
        }
    }

    private void openBox(Coordinates c){
        switch (flag.get(c)){
            case OPENED: setOpenedToBoxedAroundNum(c); return;
            case FLAGED: return;
            case CLOSED: 
                switch (bomb.get(c)){
                    case ZERO: openBoxAround(c); return;
                    case BOMB: openBomb(c); return;
                    default: flag.setOpen(c); return;
                }
        }
    }

    private void setOpenedToBoxedAroundNum(Coordinates c) {
        if(bomb.get(c) != Box.BOMB)
            if(flag.getOpenedToBoxedAroundNum(c) == bomb.get(c).getNum())
                for(Coordinates a: Ranges.getCoorAround(c))
                    if(flag.get(a) == Box.CLOSED)
                        openBox(a);
    }

    private void openBomb(Coordinates c) {
        state = GameState.BOMBED;
        flag.setBombed(c);
        for (Coordinates a:Ranges.getCoordinates() ){
            if(bomb.get(a) == Box.BOMB){
                flag.setOpenToClosed(a);
            }else
                flag.setNoToFlaggedBox(a);
        }
    }

    private void openBoxAround(Coordinates c) {
        flag.setOpen(c);
        for(Coordinates a : Ranges.getCoorAround(c)){
            openBox(a);
        }
    }

    public void pressRight(Coordinates c){
        if(gameOver()) return;
        flag.toggleFlag(c);
    }

    private boolean gameOver() {
        if(state == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}

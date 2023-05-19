package sweeper;

import static sweeper.Box.CLOSED;
import static sweeper.Box.FLAGED;

class Flag {
    private Matrix flagArr;

    void start(){
        flagArr = new Matrix(Box.CLOSED);
    }

    Box get (Coordinates c){
        return flagArr.get(c);
    }

    public void setOpen(Coordinates c) {
        flagArr.set(c, Box.OPENED);
    }

    void toggleFlag(Coordinates c){
        switch ((flagArr.get(c))){
            case FLAGED: setClosed(c); break;
            case CLOSED: setFlag(c); break;

        }
    }

    public void setClosed(Coordinates c){
        flagArr.set(c, CLOSED);
    }

    public void setFlag(Coordinates c){
        flagArr.set(c, FLAGED);
    }
}

package sweeper;

import static sweeper.Box.CLOSED;
import static sweeper.Box.FLAGED;

class Flag {
    private Matrix flagArr;
    private int countOfClosed;

    void start(){

        flagArr = new Matrix(Box.CLOSED);
        countOfClosed = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get (Coordinates c){
        return flagArr.get(c);
    }

    public void setOpen(Coordinates c) {
        flagArr.set(c, Box.OPENED);
        countOfClosed --;
    }

    void toggleFlag(Coordinates c){
        switch ((flagArr.get(c))){
            case FLAGED: setClosed(c); break;
            case CLOSED: setFlag(c); break;

        }
    }

    private void setClosed(Coordinates c){
        flagArr.set(c, CLOSED);
    }

    private void setFlag(Coordinates c){
        flagArr.set(c, FLAGED);
    }

    int getNumOfClosed() {
        return countOfClosed;
    }

    void setBombed(Coordinates c) {
        flagArr.set(c, Box.BOMBED);
    }

    public void setOpenToClosed(Coordinates a) {
        if(flagArr.get(a) == CLOSED){
            flagArr.set(a, Box.OPENED);
        }
    }

    public void setNoToFlaggedBox(Coordinates a) {
        if(flagArr.get(a) == FLAGED){
            flagArr.set(a, Box.NOBOMB);
        }
    }

    int getOpenedToBoxedAroundNum(Coordinates c){
        int count =0;
        for (Coordinates a: Ranges.getCoorAround(c)){
            if(flagArr.get(a) == Box.FLAGED)
                count++;
        }
        return count;
    }
}

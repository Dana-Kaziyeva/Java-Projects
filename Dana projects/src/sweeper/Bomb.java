package sweeper;

import org.w3c.dom.ranges.Range;

import java.awt.image.TileObserver;

public class Bomb {
    private Matrix bombArr;
    private int totalBomb;

    Bomb(int totalBomb){
        this.totalBomb = totalBomb;
        fixCount();
    }

    void start(){
        bombArr = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBomb; i++) {
            placeBomb();
        }
    }

    Box get (Coordinates c){
        return bombArr.get(c);
    }

    private void fixCount(){ // function which will not allow totalBomb be more than the half of the panel size
        int maxBomb = Ranges.getSize().x * Ranges.getSize().y /2;
        if(totalBomb > maxBomb)
            totalBomb = maxBomb;
    }

    private void placeBomb() {
        while (true) {
            Coordinates c = Ranges.getRandomCoor();
            if (Box.BOMB == bombArr.get(c))
                continue;
            bombArr.set(c, Box.BOMB);
            increaseNum(c);
            break;
        }
    }

    private void increaseNum(Coordinates c){
        for(Coordinates a: Ranges.getCoorAround(c)){
            if(Box.BOMB != bombArr.get(a)){
                bombArr.set(a, bombArr.get(a).getNextNum());
            }
        }
    }

    int getTotal() {
        return totalBomb;
    }
}

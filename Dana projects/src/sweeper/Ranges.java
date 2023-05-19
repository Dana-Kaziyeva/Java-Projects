package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coordinates size;
    private static ArrayList<Coordinates> coordinates;
    private static Random random = new Random();

    public static void setSize(Coordinates s){
        size=s;
        coordinates = new ArrayList<>();
        for (int x =0;x<size.x;x++)
            for (int y = 0; y < size.y; y++) {
                coordinates.add(new Coordinates(x,y));

            }
    }
    public static Coordinates getSize(){
        return size;
    }
    public static ArrayList<Coordinates> getCoordinates(){
        return coordinates;
    }

    static boolean inRange(Coordinates c){
        return c.x>=0 &&c.x< size.x && c.y<size.y && c.y>=0;
    }

    static Coordinates getRandomCoor(){
     return new Coordinates(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coordinates> getCoorAround(Coordinates c){
        Coordinates around;
        ArrayList<Coordinates> list = new ArrayList<>();
        for (int i = c.x-1; i <= c.x+1 ; i++) {
            for (int j = c.y-1; j <= c.y+1 ; j++) {
                if(inRange(around = new Coordinates(i, j)) && !around.equals(c))
                        list.add(around);

            }

        }
        return list;
    }

}

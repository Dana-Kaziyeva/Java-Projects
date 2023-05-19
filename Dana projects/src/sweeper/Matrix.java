package sweeper;

class Matrix {
    private Box[][] arr;

    Matrix(Box box){
        arr = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for(Coordinates c :Ranges.getCoordinates())
            arr[c.x][c.y] = box;
    }

    Box get (Coordinates c){
        if(Ranges.inRange(c))
            return arr[c.x][c.y];
        return null;
    }
    void set (Coordinates c, Box b){
        if(Ranges.inRange(c))
            arr[c.x][c.y]  = b;
    }
}

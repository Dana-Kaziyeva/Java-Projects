import javax.swing.*;
import java.awt.*; // GUI library
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coordinates;
import sweeper.Game;
import sweeper.Ranges;

public class Sweeper extends JFrame{
    private Game game;
    private  JPanel panel;
    private  final int COLS  = 10; //the number of columns in the game
    private  final int ROWS  = 10 ; //the number of ROWS in the game
    private  final int IMG_SIZE = 50;
    private final int TOTAL =10;


    public static void main(String[] args){

    new Sweeper();

    }

    private Sweeper() {
        game = new Game(COLS, ROWS, TOTAL);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coordinates c : Ranges.getCoordinates()) {
                    g.drawImage((Image) game.getBox(c).img, c.x*IMG_SIZE, c.y*IMG_SIZE, this);

                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x =e.getX() / IMG_SIZE;
                int y =e.getY() / IMG_SIZE;
                Coordinates c = new Coordinates(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeft(c);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRight(c);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(   Ranges.getSize().x *IMG_SIZE, Ranges.getSize().y*IMG_SIZE)); //the size of the panel
        add (panel);
    }

    private void initFrame(){
        pack(); // function to resize the form
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //close after the window was closed
        setTitle("MineSweeperGame");
        setLocationRelativeTo(null);// open panel at the center
        setResizable(false);// we can't resize the panel
        setVisible(true);//show panel
        setIconImage(getImg("icon"));
    }

    private  void setImages(){ //function to set all images to the panel
        for (Box box: Box.values())
            box.img = getImg(box.name());
    }

    private Image getImg(String name ){ // function to get an image
        String filename = name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}

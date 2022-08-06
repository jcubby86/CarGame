package carGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player {
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private final int WIDTH = 60;
    private final int HEIGHT =70;
    private int lives;
    private boolean doRun = true;
    private Color carColor;
    private final Random rand = new Random();

    public Player() {
        lives = 3;
        x = 270;
        y = 250;
        xVelocity = 0;
        yVelocity = 0;
        carColor = randomColor();
    }

    public void update(int l) {
        lives = l;
        if (lives == 0){
            doRun = false;
        } else if (lives == 3 && !doRun){
            x = 270;
            y = 250;
            xVelocity = 0;
            yVelocity = 0;
            doRun = true;
            carColor = randomColor();
        }
        
            if (((y + yVelocity)<=GameRun.WINDOW_HEIGHT-HEIGHT-40)&&((y + yVelocity)>=20))
                y = y + yVelocity;
            if (((x + xVelocity)<=GameRun.WINDOW_WIDTH-WIDTH-70)&&((x + xVelocity)>=70))
                x = x + xVelocity;
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRoundRect(x+5, y+5, 20, 20,10,10);
        g.fillRoundRect(x+WIDTH-25, y+5, 20, 20,10,10);
        g.fillRoundRect(x+5, y+HEIGHT-25, 20, 20,10,10);
        g.fillRoundRect(x+WIDTH-25, y+HEIGHT-25, 20, 20,10,10);
        g.setColor(carColor);
        g.fillRoundRect(x+11, y, WIDTH-20, HEIGHT,10,10); 
        g.setColor(Color.cyan);
        g.fillRoundRect(x+15, y+7, WIDTH-30, HEIGHT-((2*HEIGHT)/3),10,10);         
    }

    public void setYVelocity(int speed) {
        yVelocity = speed;
    }

    public void setXVelocity(int speed) {
        xVelocity = speed;
    }

    private Color randomColor(){
        int colorRandom;
        colorRandom = rand.nextInt(7)+1;
        Color rtn;
            switch(colorRandom){
                case 1:
                    rtn=(Color.YELLOW);
                    break;
                case 2:
                    rtn=(Color.GREEN);
                    break;
                case 3:
                    rtn=(Color.MAGENTA);
                    break;
                case 4:
                    rtn=(Color.ORANGE);
                    break;
                case 5:
                    rtn=(Color.PINK);
                    break;
                case 6:
                    rtn=(Color.RED);
                    break;
                case 7:
                    rtn=(Color.WHITE);
                    break;
                default:        
                    rtn=(Color.BLUE);     
                    break;   
            }
            return rtn;
    }

}
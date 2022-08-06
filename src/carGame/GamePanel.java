package carGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Player player = new Player();
    private int startTime;
    private int stopTime = 0;
    private int lives = 3;
    Timer time = new Timer(10, this);    
    private boolean doRun = true;
    private int counter=0;
    
    public GamePanel() {
        time.start();
        this.addKeyListener(this);
        this.setFocusable(true);
        startTime = timeGet();
    }

    private void update() {
        player.update(lives);
    }
    
    @Override
    public void paintComponent(Graphics g) {      
        simplePaint(g);
        score(g);
    }

    public void score(Graphics g){
        int[] heartX =  {25,30,35,40,40,25,10,10,15,20};
        int[] hearty1 = {10,5 ,5 ,10,15,30,15,10,5 ,5};
        int[] hearty2 = {50,45,45,50,55,70,55,50,45,45};
        int[] hearty3 = {90,85,85,90,95,110,95,90,85,85};
        g.setColor(Color.red);
        if (lives > 2)            
            g.fillPolygon(heartX, hearty3, 10);
        if (lives > 1)
            g.fillPolygon(heartX, hearty2, 10);
        if (lives > 0)           
            g.fillPolygon(heartX, hearty1, 10);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        g.setColor(Color.BLUE);
        if (lives > 0)
            g.drawString("Score:" + (timeGet()-startTime), 52, 20);
        else
            gameOver(g);
        
    }

    private void gameOver(Graphics g){
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,52));
        g.setColor(Color.black);
        doRun = false;
        player.setXVelocity(0);
        player.setYVelocity(0);
            if (counter > 30)    
                g.drawString("Game Over", 100, 100);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
            if (counter > 60)
                g.drawString("Score:" + (stopTime-startTime), 165, 150);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            if (counter > 90)
                g.drawString("Press 'Space' to Play Again", 55, 425);
            if (counter > 120)
                g.drawString("Press 'Escape' to Exit", 90, 525);
            counter++;    

    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.setYVelocity(-3);
                break;
            case KeyEvent.VK_DOWN:
                player.setYVelocity(3);
                break;
            case KeyEvent.VK_LEFT:
                player.setXVelocity(-3);
                break;
            case KeyEvent.VK_RIGHT:
                player.setXVelocity(3);
                break;
            case KeyEvent.VK_SPACE:
                if (counter > 151){
                    if (!doRun){
                        doRun = true;
                        lives = 3;
                        startTime = timeGet();
                        stopTime = 0;
                        counter = 0;
                    }
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
            player.setYVelocity(0);
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            player.setXVelocity(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private int timeGet() {
        DateFormat dateFormat = new SimpleDateFormat("d");
        Date now = new Date();
        int date = Integer.parseInt(dateFormat.format(now))*24*60*60;
        dateFormat = new SimpleDateFormat("k");
        date += Integer.parseInt(dateFormat.format(now))*60*60;
        dateFormat = new SimpleDateFormat("m");
        date += Integer.parseInt(dateFormat.format(now))*60;
        dateFormat = new SimpleDateFormat("s" );
        date += Integer.parseInt(dateFormat.format(now));
        return date;
    }

    private void simplePaint(Graphics g){
        Color darkGreen = new Color(22,122,39);
            g.setColor(darkGreen);
            g.fillRect(0, 0, GameRun.WINDOW_WIDTH, GameRun.WINDOW_HEIGHT);
            g.setColor(Color.darkGray);
            g.fillRect(50, 0, GameRun.WINDOW_WIDTH-100, GameRun.WINDOW_HEIGHT);
            g.setColor(Color.white);
            for (int i = 0; i < GameRun.WINDOW_HEIGHT; i +=35){
                g.fillRect(148, i+5, 4, 15);
            }
            for (int i = 0; i < GameRun.WINDOW_HEIGHT; i +=35){
                g.fillRect(348, i+5, 4, 15);
            }
            g.setColor(Color.yellow);
            g.fillRect(244, 0, 4, GameRun.WINDOW_HEIGHT);
            g.fillRect(252, 0, 4, GameRun.WINDOW_HEIGHT);
            player.paint(g);
    }
}


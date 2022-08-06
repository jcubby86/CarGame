package carGame;

import javax.swing.JFrame;

public class GameRun extends JFrame {
    final static int WINDOW_WIDTH = 500;
    final static int WINDOW_HEIGHT = 600;

    public GameRun() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gp = new GamePanel();
        add(gp);
        setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("A Game");
    }

    public static void main(String[] args) {
        new GameRun();
    }
}
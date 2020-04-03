package controller;

import java.awt.Font;
import javax.swing.JFrame;
import model.GameData;
import view.GamePanel;
import view.MainWindow;

public class Main {

    public static GamePanel gamePanel;
    public static GameData gameData;
    public static Animator animator;

    public static int WIN_WIDTH = 400;
    public static int WIN_HEIGHT = 600;   
    
    public static Font font;

    public static void main(String[] args) throws Exception {

        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel();

        JFrame game = new MainWindow();
        game.setTitle("YosukeS_termProject");
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        //game.setLocation(800, 180);
        game.setResizable(false);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);

        new Thread(animator).start();
        
        Initialize initilizer = new Initialize();
       
    }
}

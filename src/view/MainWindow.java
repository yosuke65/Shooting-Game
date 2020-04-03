
package view;


import controller.KeyController;
import controller.Main;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import model.HudPanel;

public class MainWindow extends JFrame {


    public MainWindow() {

        Container c = getContentPane();

        c.add(Main.gamePanel, "Center");

        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);
        Main.gamePanel.addMouseMotionListener((MouseMotionListener) Main.gameData.hudPanel);
        Main.gamePanel.addMouseListener((MouseListener) Main.gameData.hudPanel);
    }

}

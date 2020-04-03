package view;

import controller.Initialize;
import controller.Main;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JPanel;
import model.EnemyFigure;
import model.GameFigure;

public class GamePanel extends JPanel {

    public static int width;
    public static int height;
    public float health_x;
    public float health_y;

    private Graphics2D g2;
    private Image dbImage = null;
    private Image countIcon = null;

    public void gameRender() throws FontFormatException, IOException {

        width = getSize().width;
        height = getSize().height;

        health_x = width / 10;
        health_y = height / 10;

        if (dbImage == null) {

            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }

        g2.clearRect(0, 0, width, height);
        setBackground();
        

        if (Main.animator.running) {

            Main.gameData.hudPanel.render(g2);

            for (EnemyFigure e : Main.gameData.enemyFigures) {
                e.render(g2);
            }
            for (GameFigure f : Main.gameData.friendFigures) {
                f.render(g2);
            }
            for (GameFigure f : Main.gameData.itemFigures) {
                f.render(g2);
            }
            for (GameFigure f : Main.gameData.effectFigures) {
                f.render(g2);
                //System.out.println("Called render");
            }
            for (GameFigure f : Main.gameData.bossFigures) {
                f.render(g2);
                //System.out.println("Called render");
            }
        }
    }

    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }

    private void setBackground() {

        g2.drawImage(Initialize.backgroundImage, 0, 0, width, height, null);
    }

}

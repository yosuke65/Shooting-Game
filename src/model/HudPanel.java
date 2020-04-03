
package model;

import controller.Initialize;
import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import static view.GamePanel.height;
import static view.GamePanel.width;


public class HudPanel extends GameFigure implements MouseMotionListener, MouseListener {

    private Image countIcon = null;
    public float health_x;
    public float health_y;
    public float mouse_x;
    public float mouse_y;
    public boolean continueButton = false;
    public boolean quitButton = false;
    public boolean continueButtonClicked = false;
    public boolean quitButtonClicked = false;

    public HudPanel(float x, float y) {
        super(x, y);

        width = Main.WIN_WIDTH;
        height = Main.WIN_HEIGHT;

//        health_x = width / 10;
//        health_y = height / 10;
    }

    @Override
    public void render(Graphics2D g) {
        this.drawHealth(g);
        this.drawScore(g);
        this.drawLevel(g);
        this.drawBombCount(g);
        this.drawMissileCount(g);
        if (Main.gameData.gameState == GameFigureState.GAMEOVER) {
            this.drawGameOver(g);
        }
    }

    @Override
    public void update() {
        updateStatus();
    }

    @Override
    public Rectangle2D getCollisionBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void drawScore(Graphics2D g) {

        g.setColor(Color.yellow);
        g.setFont(Initialize.scoreFont);

        g.drawString(getScore(), width / 100, height / 20);

    }

    private void drawHealth(Graphics2D g) {
        health_x = width / 50;
        health_y = (height / 10) - 20;
        try {
            countIcon = ImageIO.read(getClass().getResource("playerShip2_orange.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open playerShip2_orange.png");
            System.exit(-1);
        }
        for (int i = 0; i < Main.gameData.friendFigures.get(0).health; i++) {
            g.drawImage(countIcon, (int) health_x, (int) health_y, 20, 20, null);
            health_x += 20;
        }
    }

    private String getScore() {
        return "SCORE: " + Main.gameData.gameScore;
    }

    private void drawLevel(Graphics2D g) {
        g.setColor(Color.yellow);
        g.setFont(Initialize.levelFont);

        g.drawString(getLevel(), width - 100, height / 20);
    }

    private String getLevel() {
        return "Level: " + Main.gameData.gameLevel;
    }

    private void drawBombCount(Graphics2D g) {
        g.setColor(Color.yellow);
        g.setFont(Initialize.itemFont);

        g.drawString(getBombCount(), width / 100, height - 20);
    }

    private String getBombCount() {
        return "Bomb: " + Main.gameData.bombCount;
    }

    private void drawMissileCount(Graphics2D g) {
        g.setColor(Color.yellow);
        g.setFont(Initialize.itemFont);

        g.drawString(getMissileCount(), width / 100, height);
    }

    private String getMissileCount() {
        return "Missile: " + Main.gameData.missileCount;
    }

    private void drawGameOver(Graphics2D g) {

        g.setFont(Initialize.gameoverFont);
        g.drawString("GAME OVER", width / 2 - 150, height / 2);
        g.setFont(Initialize.normalFont);

        if (!continueButton) {
            g.setFont(Initialize.normalFont);
            g.setColor(Color.yellow);
            g.drawString("<Continue>", width / 2 - 80, height / 2 + 70);
        } else {
            g.setFont(Initialize.continueFont);
            g.setColor(Color.red);
            g.drawString("<Continue>", width / 2 - 85 , height / 2 + 70);
        }
        if (!quitButton) {
            g.setFont(Initialize.normalFont);
            g.setColor(Color.yellow);
            g.drawString("<Quit>", width / 2 - 40 , height / 2 + 50 + 70);
        } else {
            g.setFont(Initialize.quitFont);
            g.setColor(Color.red);
            g.drawString("<Quit>", width / 2 - 45, height / 2 + 50 + 70);
        }

        //System.out.println("Mouse moved. x = " + mouse_x + " y = " + mouse_y);
        if (mouse_x >= 121 && mouse_x <= 278 && mouse_y >= 341 && mouse_y <= 361) {
            //Continue Button
            continueButton = true;
        } else {
            continueButton = false;
        }
        if (mouse_x >= 159 && mouse_x <= 246 && mouse_y >= 393 && mouse_y <= 413) {
            //Quit Button
            quitButton = true;
        } else {
            quitButton = false;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse moved. x = " + e.getX() + " y = " + e.getY());
        mouse_x = e.getX();
        mouse_y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Mouse dragged. x = " + e.getX() + " y = " + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mouse_x >= 121 && mouse_x <= 278 && mouse_y >= 341 && mouse_y <= 361) {
            //Yes Button
            continueButtonClicked = true;
        } else {
            continueButtonClicked = false;
        }
        if (mouse_x >= 159 && mouse_x <= 246 && mouse_y >= 393 && mouse_y <= 413) {
            //No Button
            quitButtonClicked = true;
        } else {
            quitButtonClicked = false;
        }
        
        //System.out.println("Yes: " + continueButtonClicked+" No: " + quitButtonClicked);
    }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse pressed. x = " + e.getX() + " y = " + e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Mouse released. x = " + e.getX() + " y = " + e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("Mouse entered. x = " + e.getX() + " y = " + e.getY());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("Mouse exited. x = " + e.getX() + " y = " + e.getY());
        }

    private void updateStatus() {
         if(Main.gameData.gameState == GameFigureState.GAMEOVER){
             checkContinue();
         }
    }

    private void checkContinue() {
        if(continueButtonClicked){
            Main.gameData.gameState = GameFigureState.GAME_STAGE_ONE;
            Main.gameData.Initialize();
            continueButtonClicked = false;
            quitButtonClicked = false;
        }
        if(quitButtonClicked){
            System.exit(0);
        }
    }

}

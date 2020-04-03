package model;

import controller.Event;
import controller.Main;
import static controller.Main.WIN_HEIGHT;
import static controller.Main.WIN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Spaceship extends EnemyFigure {

    private final int spaceshipWidth;
    private final int spaceshipHeight;
    private final int UNIT_TRAVEL = 2; // per frame
    private Image spaceshipImage;
    private int distance;
    private final int MAX_X_TRAVEL = 100;
    private final int X_MAX = WIN_WIDTH - 55;
    private final int X_MIN = 0;
    private final int TRIGGER_DISTANCE = 200;

    public Spaceship(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.STATE_NORMAL;
        super.level = Main.gameData.gameLevel;
        setHealth();
        spaceshipWidth = 50;
        spaceshipHeight = 50;
        getImage();
        setAnimation(new Behavior1(x, y, spaceshipWidth, spaceshipHeight, UNIT_TRAVEL, spaceshipImage));
    }

    @Override
    public void render(Graphics2D g) {
        animation.render(g);
//        g.drawImage(ufoImage, (int) super.x, (int) super.y,
//                WIDTH, HEIGHT, null);
    }

    @Override
    public void update() {

        if (distance >= TRIGGER_DISTANCE) {
            animationUpdate();
            distance = 0;
        }
        animation.update();
        distance += UNIT_TRAVEL;
        updateLocation();
        if (y > WIN_HEIGHT) {
            state = GameFigureState.STATE_DISAPPEARED;
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Float box = new Rectangle2D.Float((int) (x - spaceshipWidth / 2), (int) (y - spaceshipHeight / 2), spaceshipWidth, spaceshipHeight);
        return box;

    }

    public void animationUpdate() {

        Random rand = new Random();
        int behavoir = rand.nextInt(3) + 1;
        if (behavoir == 1) {
            setAnimation(new Behavior2(x, y, spaceshipWidth, spaceshipHeight, UNIT_TRAVEL, spaceshipImage));
        } else if (behavoir == 2) {
            setAnimation(new Behavior1(x, y, spaceshipWidth, spaceshipHeight, UNIT_TRAVEL, spaceshipImage));
        } else if (behavoir == 3) {
            setAnimation(new Behavior4(x, y, spaceshipWidth, spaceshipHeight, UNIT_TRAVEL, spaceshipImage));
            
        } else {
            
        }
        animation.update();

    }

    @Override
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    private void getImage() {
        try {
            spaceshipImage = ImageIO.read(getClass().getResource("enemyBlack1.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open ufoGreen.png");
            System.exit(-1);
        }
//        Random rand = new Random();
//        int color = rand.nextInt(4) + 1;
//        //System.out.println(direction);
//        if (color == 1) {
//            try {
//                spaceshipImage = ImageIO.read(getClass().getResource("enemyBlack1.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open ufoGreen.png");
//                System.exit(-1);
//            }
//        } else if (color == 2) {
//            try {
//                spaceshipImage = ImageIO.read(getClass().getResource("enemyBlue2.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open enemyBlue2.png");
//                System.exit(-1);
//            }
//        } else if (color == 3) {
//            try {
//                spaceshipImage = ImageIO.read(getClass().getResource("enemyGreen4.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open enemyGreen4.png");
//                System.exit(-1);
//            }
//        } else {
//            try {
//                spaceshipImage = ImageIO.read(getClass().getResource("enemyRed3.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open enemyRed3.png");
//                System.exit(-1);
//            }
//        }

    }

    private void updateLocation() {
        super.x = animation.getX();
        super.y = animation.getY();
    }

    @Override
    public void update(Event e) {
        
    }

    @Override
    public void setHealth() {
        if (level >= 1 && level <=10){
            health = 2;
        }else if(level >= 11 && level <=20){
            health = 3;
        }else{
            health = 4;
        }
    }

//    @Override
//    public boolean isEnter() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}

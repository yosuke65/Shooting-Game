package model;

import controller.Event;
import controller.Main;
import static controller.Main.WIN_HEIGHT;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Asteroid2 extends Asteroid {

    private final int asteroidWidth;
    private final int asteroidHeight;
    private final int UNIT_TRAVEL = 2; // per frame
    private Image asteroidImage;

    public Asteroid2(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.STATE_NORMAL;
        asteroidWidth = 70;
        asteroidHeight = 70;
        level = Main.gameData.gameLevel;
        setHealth();

        getImage();
        setAnimation(new Behavior1(x, y, asteroidWidth, asteroidHeight, UNIT_TRAVEL, asteroidImage));
    }

    @Override
    public void render(Graphics2D g) {
        animation.render(g);
    }

    @Override
    public void update() {

        stateUpdate();
        animation.update();
        updateLocation();
    }

    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Float box = new Rectangle2D.Float((int) (x - asteroidWidth / 2), (int) (super.y - asteroidHeight / 2), asteroidWidth, asteroidHeight);
        return box;

    }

    public void stateUpdate() {
        if (y > WIN_HEIGHT - 80) {
            state = GameFigureState.STATE_DISAPPEARED;
        }
    }

    @Override
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    private void updateLocation() {
        super.x = animation.getX();
        super.y = animation.getY();
    }

    private void getImage() {
        try {
            asteroidImage = ImageIO.read(getClass().getResource("meteorBrown_big2.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open meteorBrown_big2.png");
            System.exit(-1);
        }
//        Random rand = new Random();
//        int color = rand.nextInt(4) + 1;
//        //System.out.println(direction);
//        if (color == 1) {
//            try {
//                asteroidImage = ImageIO.read(getClass().getResource("meteorBrown_big1.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open meteorBrown_big1.png");
//                System.exit(-1);
//            }
//        } else if (color == 2) {
//            try {
//                asteroidImage = ImageIO.read(getClass().getResource("meteorBrown_big2.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open meteorBrown_big2.png");
//                System.exit(-1);
//            }
//        } else if (color == 3) {
//            try {
//                asteroidImage = ImageIO.read(getClass().getResource("meteorBrown_big3.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open meteorBrown_big3.png");
//                System.exit(-1);
//            }
//        } else {
//            try {
//                asteroidImage = ImageIO.read(getClass().getResource("meteorBrown_big4.png"));
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "Error: Cannot open meteorBrown_big4.png");
//                System.exit(-1);
//            }
//        }
    }

    @Override
    public void update(Event e) {
        level++;
    }

    @Override
    public void setHealth() {
        if (level >= 1 && level <=10){
            health = 2;
        }else if(level >= 11 && level <=20){
            health = 3;
        }else{
            health = 3;
        }
    }
}

package model;

import controller.Event;
import static controller.Main.WIN_HEIGHT;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ItemBomb extends EnemyFigure {

    private final int bombWidth;
    private final int bombHeight;
    private final int UNIT_TRAVEL = 1; // per frame
    private Image asteroidImage;

    public ItemBomb(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.STATE_NORMAL;
        bombWidth = 30;
        bombHeight = 30;
        getImage();
        setAnimation(new Behavior1(x, y, bombWidth, bombHeight, UNIT_TRAVEL, asteroidImage));
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
        Rectangle2D.Float box = new Rectangle2D.Float((int) (x - bombWidth / 2), (int) (super.y - bombHeight / 2), bombWidth, bombHeight);
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
            asteroidImage = ImageIO.read(getClass().getResource("spaceMissiles_021.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open spaceMissiles_021.png");
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

    }
//
//    @Override
//    public boolean isEnter() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}

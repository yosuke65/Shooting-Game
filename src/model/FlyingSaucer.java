package model;

import controller.Event;
import static controller.Main.WIN_HEIGHT;
import static controller.Main.WIN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class FlyingSaucer extends EnemyFigure {

    private final int ufoWidth;
    private final int ufoHeight;
    private final int UNIT_TRAVEL = 2; // per frame
    private Image ufoImage;
    private int yDistance;
    private int xDistance;
    private final int MAX_X_TRAVEL = 100;
    private final int X_MAX = WIN_WIDTH - 55;
    private final int X_MIN = 0;
    private int triggerDistance;
    private int distance;
//    private Animation behavior1;
//    private Animation behavior2;

    public FlyingSaucer(int x, int y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.STATE_NORMAL;
        super.health = 2;
        ufoWidth = 40;
        ufoHeight = 40;
        Random rand = new Random();
        triggerDistance = rand.nextInt(500 - 200) + 200;
        getImage();
        animation = new Behavior1(x, y, ufoWidth, ufoHeight, UNIT_TRAVEL, ufoImage);
    }

    @Override
    public void render(Graphics2D g) {
        animation.render(g);
//        g.drawImage(ufoImage, (int) super.x, (int) super.y,
//                WIDTH, HEIGHT, null);
    }

    @Override
    public void update() {

        animationUpdate();
        updateLocation();
        distance += UNIT_TRAVEL;
    }

    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Float box = new Rectangle2D.Float((int) (x - ufoWidth / 2), (int) (y - ufoHeight / 2), ufoWidth, ufoHeight);
        return box;

    }

    public void animationUpdate() {

        if (distance >= triggerDistance) {
            setRandomBehavior();
            distance = 0;
        }
        if (y > WIN_HEIGHT) {
            state = GameFigureState.STATE_DISAPPEARED;
        }
        animation.update();
    }

    @Override
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    private void getImage() {
        Random rand = new Random();
        int color = rand.nextInt(4) + 1;
        //System.out.println(direction);
        if (color == 1) {
            try {
                ufoImage = ImageIO.read(getClass().getResource("ufoGreen.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open ufoGreen.png");
                System.exit(-1);
            }
        } else if (color == 2) {
            try {
                ufoImage = ImageIO.read(getClass().getResource("ufoBlue.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open ufoGreen.png");
                System.exit(-1);
            }
        } else if (color == 3) {
            try {
                ufoImage = ImageIO.read(getClass().getResource("ufoYellow.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open ufoGreen.png");
                System.exit(-1);
            }
        } else {
            try {
                ufoImage = ImageIO.read(getClass().getResource("ufoRed.png"));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open ufoGreen.png");
                System.exit(-1);
            }
        }

    }

    private void updateLocation() {
        super.x = animation.getX();
        super.y = animation.getY();
    }

    private void setRandomBehavior() {
        setAnimation(new Behavior2(x, y, ufoWidth, ufoHeight, UNIT_TRAVEL, ufoImage));
    }

    @Override
    public void update(Event e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHealth() {
        if (level >= 1 && level <= 10) {
            health = 2;
        } else if (level >= 11 && level <= 20) {
            health = 3;
        } else {
            health = 3;
        }
    }

//    @Override
//    public boolean isEnter() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}

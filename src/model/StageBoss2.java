package model;

import controller.Event;
import static controller.Main.WIN_HEIGHT;
import static controller.Main.WIN_WIDTH;
import controller.Observer;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class StageBoss2 extends StageBoss {

    private final int spaceshipWidth;
    private final int spaceshipHeight;
    private final int UNIT_TRAVEL = 2; // per frame
    private final int ENTER_TRAVEL = 1;
    private final int ESCAPE_TRAVEL = 3;
    private Image spaceshipImage;
    private int distance;
    private int enterDistance;
    private final int MAX_X_TRAVEL = 100;
    private final int X_MAX = WIN_WIDTH - 55;
    private final int X_MIN = 0;
    private final int Y_MAX = WIN_HEIGHT / 2;
    private final int TRIGGER_DISTANCE = 700;
    private ArrayList<Observer> observers;
//    private Animation behavior1;
//    private Animation behavior2;

    public StageBoss2(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.STATE_BOSS_APPEAR;
        isEnter = true;
        setHealth();
        spaceshipWidth = 70;
        spaceshipHeight = 70;
        observers = new ArrayList<>();
        getImage();
        setAnimation(new Behavior1(x, y, spaceshipWidth, spaceshipHeight, ENTER_TRAVEL, spaceshipImage));
    }

    @Override
    public void render(Graphics2D g) {
        animation.render(g);
//        g.drawImage(ufoImage, (int) super.x, (int) super.y,
//                WIDTH, HEIGHT, null);
    }

    @Override
    public void update() {
        //System.out.println("x: " + x + " y: " + y + " State: " + state+" Distance: "+ distance);
        stateUpdate();
        if (state == GameFigureState.STATE_BOSS_APPEAR) {
            animation.update();
        } else {

            if (distance >= TRIGGER_DISTANCE) {
                animationUpdate();
                distance = 0;
            }
            animation.update();
            distance += UNIT_TRAVEL;

        }
        updateLocation();
    }

    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Float box = new Rectangle2D.Float((int) (x - spaceshipWidth / 2), (int) (y - spaceshipHeight / 2), spaceshipWidth, spaceshipHeight);
        return box;

    }

    public void animationUpdate() {
        randomBehavior(this.animation);
    }

    @Override
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }


    private void getImage() {
        try {
            spaceshipImage = ImageIO.read(getClass().getResource("enemyBlack4.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open enemyBlack4.png");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHealth() {
        super.health = 5;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(new Event(this));
        }
    }

    private void stateUpdate() {
        if (y >= 200 && isEnter) {
            state = GameFigureState.STATE_BOSS_BATTLE;
            isEnter = false;
            animationUpdate();
        }

    }

    private void randomBehavior(Animation animation) {
        Random rand = new Random();
        while (this.animation.getClass() == animation.getClass()) {
            int behavoir = rand.nextInt(3) + 1;
            if (behavoir == 1) {
                setAnimation(new BossBehavior0(x, y, spaceshipWidth, spaceshipHeight, ESCAPE_TRAVEL, spaceshipImage));
            } else if (behavoir == 2) {
                setAnimation(new BossBehavior2(x, y, spaceshipWidth, spaceshipHeight, UNIT_TRAVEL, spaceshipImage));
            } else if (behavoir == 3) {
                setAnimation(new BossBehavior1(x, y, spaceshipWidth, spaceshipHeight, ESCAPE_TRAVEL, spaceshipImage));
                //Main.gameData.enemyFigures.add(new EnemyBullet(this.x - (spaceshipWidth / 100), this.y - (spaceshipHeight) /100 + 10));
            } else {

            }
        }
    }

}

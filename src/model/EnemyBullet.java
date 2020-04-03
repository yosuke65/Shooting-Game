package model;

import controller.Event;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class EnemyBullet extends EnemyFigure {

    private Image image;
    private final int WIDTH = 10;
    private final int HEIGHT = 20;

    private static final int UNIT_TRAVEL_DISTANCE = 5; // per frame move

    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     */
    public EnemyBullet(float sx, float sy) {
        super(sx, sy);
        super.state = GameFigureState.BULLET_STATE_LAUNCHED;
        super.health = 1;
        image = null;

        try {
            image = ImageIO.read(getClass().getResource("laserRed12.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open laserRed12");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
                WIDTH, HEIGHT, null);
  //      g.fill(new Rectangle2D.Float(super.x - (int) WIDTH / 100, super.y - (int) HEIGHT / 100, (int) WIDTH, (int) HEIGHT));
    }

    @Override
    public void update() {

        updateState();
        if (state == GameFigureState.BULLET_STATE_LAUNCHED) {
            updateLocation();
        }

    }

    public void updateLocation() {

        super.y += UNIT_TRAVEL_DISTANCE;
    }

    public void updateState() {

        if (super.y < 0) {
            state = GameFigureState.STATE_DONE;
        }
        if (state == GameFigureState.STATE_COLLIGION) {
            state = GameFigureState.STATE_DONE;
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {

        Rectangle2D.Float box = new Rectangle2D.Float(super.x - (int) WIDTH / 100, super.y - (int) HEIGHT / 100, (int) WIDTH, (int) HEIGHT);
        return box;
    }

    @Override
    public void setAnimation(Animation animation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Event e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHealth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public boolean isEnter() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Missile extends GameFigure {

    private Image image;
    private final int WIDTH = 20;
    private final int HEIGHT = 40;

    private static final int UNIT_TRAVEL_DISTANCE = 2; // per frame move

    /**
     *
     * @param sx start x of the missile
     * @param sy start y of the missile
     */
    public Missile(float sx, float sy) {
        super(sx, sy);
        super.state = GameFigureState.MISSILE_STATE_LAUNCHED;
        super.health = 1;
        image = null;

        try {
            image = ImageIO.read(getClass().getResource("spaceMissiles_006.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open spaceMissiles_006");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
                WIDTH, HEIGHT, null);
    }

    @Override
    public void update() {

        updateState();
        if (state == GameFigureState.MISSILE_STATE_LAUNCHED) {
            updateLocation();
        }

    }

    public void updateLocation() {

        super.y -= UNIT_TRAVEL_DISTANCE;
    }

    public void updateState() {

        if (super.y < 0) {
            state = GameFigureState.STATE_DISAPPEARED;
        }
    }

    @Override
    public Rectangle2D getCollisionBox() {

        Rectangle2D.Float box = new Rectangle2D.Float(super.x - (int) WIDTH/100, super.y - (int) HEIGHT/100, (int) WIDTH, (int) HEIGHT);
        return box;
    }

//    @Override
//    public boolean isEnter() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}

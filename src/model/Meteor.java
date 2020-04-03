package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Meteor extends GameFigure {

    private final int meteorWidth;
    private final int meteorHeight;
    private Image meteorImage;

    public Meteor(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.STATE_NORMAL;
        meteorWidth = 30;
        meteorHeight = 30;
        getImage();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(meteorImage, (int) x - meteorWidth / 2,
                (int) y - meteorHeight / 2, meteorWidth, meteorHeight, null);
    }

    @Override
    public void update() {
    }

    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Float box = new Rectangle2D.Float((int) (x - meteorWidth / 2),
                (int) (super.y - meteorHeight / 2), meteorWidth, meteorHeight);
        return box;
    }

    private void getImage() {
        try {
            meteorImage = ImageIO.read(getClass().getResource("meteorBrown_big2.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open meteorBrown_big2.png");
            System.exit(-1);
        }
    }
}

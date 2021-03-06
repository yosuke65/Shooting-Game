package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ExplosionUFO2 extends GameFigure {

    private int width;
    private int height;
    private int ufoWidth;
    private int ufoHeight;
    //private BufferedImage[] image;
    public int explodeCount;
    public final List<Image> image;
    public Image image1;
    public final int MAX_TRAVEL = 200;
    public int distance;
    public final int UNIT_TARVEL = 2;

    public ExplosionUFO2(float x, float y) {
        super(x, y);
        super.state = GameFigureState.STATE_EXPLOSION;
        width = 30;
        height = 30;
        ufoWidth = 40;
        ufoHeight = 40;
        explodeCount = 0;
        //System.out.println("Called ExplosionAnimation");
        image = new CopyOnWriteArrayList<>();
        getImage();

    }

    @Override
    public void update() {
        if (distance <= MAX_TRAVEL) {
            updateLocation();
        } else {
            explodeCount++;
            updateState();
            updateSize();
            //System.out.println("Called update");
        }
    }

    @Override
    public void render(Graphics2D g2) {
        if (distance <= MAX_TRAVEL) {
            g2.drawImage(image1, (int) x - ufoWidth / 2, (int) y - ufoHeight / 2,
                    ufoWidth, ufoHeight, null);
        } else {
            g2.drawImage(image.get(explodeCount), (int) x - width / 2, (int) y - height / 2,
                    width, height, null);
//        System.out.println("Called render");
        }

    }

    private void updateSize() {
        width += 1;
        height += 1;
    }

    @Override
    public Rectangle2D getCollisionBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getImage() {

        try {
            image1 = (ImageIO.read(getClass().getResource("ufoDameaged.png")));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open ufoDameaged.png");
            System.exit(-1);
        }
        for (int i = 0; i < 10; i++) {
            try {
                image.add(ImageIO.read(getClass().getResource("explosionSmoke1.png")));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tank_explosion1.png");
                System.exit(-1);
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                image.add(ImageIO.read(getClass().getResource("explosionSmoke2.png")));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tank_explosion1.png");
                System.exit(-1);
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                image.add(ImageIO.read(getClass().getResource("explosionSmoke3.png")));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tank_explosion1.png");
                System.exit(-1);
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                image.add(ImageIO.read(getClass().getResource("explosionSmoke4.png")));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tank_explosion1.png");
                System.exit(-1);
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                image.add(ImageIO.read(getClass().getResource("explosionSmoke5.png")));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error: Cannot open tank_explosion1.png");
                System.exit(-1);
            }
        }
    }

    private void updateState() {
        if (explodeCount == 39) {
            state = GameFigureState.STATE_DONE;
        }
    }

//    @Override
//    public boolean isEnter() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    private void updateLocation() {
        y += UNIT_TARVEL;
        distance += UNIT_TARVEL;
    }
}

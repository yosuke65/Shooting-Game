package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ExplosionUFO extends GameFigure {

    private int width;
    private int height;
    //private BufferedImage[] image;
    public int explodeCount;
    public final List<Image> image;
    public Image image1;

    public ExplosionUFO(float x, float y) {
        super(x, y);
        super.state = GameFigureState.STATE_EXPLOSION;
        width = 30;
        height = 30;
        explodeCount = 0;
        //System.out.println("Called ExplosionAnimation");
        image = new CopyOnWriteArrayList<>();
        getImage();

    }

    @Override
    public void update() {
        explodeCount++;
        updateState();
        updateSize();
        //System.out.println("Called update");
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(image.get(explodeCount), (int) x - width / 2, (int) y - height / 2,
                width, height, null);
//        System.out.println("Called render");
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

}

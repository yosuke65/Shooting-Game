package model;

import controller.Main;
import static controller.Main.WIN_HEIGHT;
import static controller.Main.WIN_WIDTH;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class Shooter extends GameFigure {

    private Image image;
    private Image[] damageImage;
    public Image[] shieldImage;
    public Image[] fireImage;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    public final int FIRE_WIDTH = 10;
    public final int FIRE_HEIGHT = 10;
//    private final int shieldWIDTH = 50;
//    private final int shieldHEIGHT = 50;
    public int shieldImageType;
    public int fireImageType;
    public float velX;
    public float velY;
    public float xMax = WIN_WIDTH - 55, xMin = 0, yMax = WIN_HEIGHT - 80, yMin = 0;
    public final int UNIT_TRAVEL = 1;

    public Shooter(int x, int y) {
        super(x, y);
        super.state = GameFigureState.STATE_SHIP_APPEAR;
        super.health = 5;
        shield = false;
        velX = 0;
        velY = 0;
        image = null;
        damageImage = new Image[3];
        shieldImage = new Image[3];
        fireImage = new Image[10];
        getShieldImage();
        getFireImage();
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public void update() {
    }

    public abstract double getvelX();

    public abstract void translate(int dx, int dy);

    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Float collisionBox = new Rectangle2D.Float(super.x - WIDTH / 100, super.y - HEIGHT / 100, WIDTH, HEIGHT);
        return collisionBox;

    }

    public abstract void setVelY(int velY);

    public abstract void setVelX(int velX);

    public abstract void tick();

    public abstract void updateFireImage();

    public abstract void updateShieldImage();

    public abstract float getXofMissileShoot();

    public abstract float getYofMissileShoot();

    public abstract float getXofBulletShoot();

    public abstract float getYofBulletShoot();

    public abstract void drawPlayerShip(Graphics2D g);

    public abstract void drawDamege(Graphics2D g);

    public abstract void drawFire(Graphics2D g);

    public abstract void drawShield(Graphics2D g);

    public abstract void getImage();

    private void getShieldImage() {
        try {
            shieldImage[0] = ImageIO.read(getClass().getResource("shield1.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shield1.png");
            System.exit(-1);
        }
        try {
            shieldImage[1] = ImageIO.read(getClass().getResource("shield2.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shield2.png");
            System.exit(-1);
        }
        try {
            shieldImage[2] = ImageIO.read(getClass().getResource("shield3.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shield3.png");
            System.exit(-1);
        }
    }

    private void getFireImage() {
        try {
            fireImage[0] = ImageIO.read(getClass().getResource("fire10.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire10.png");
            System.exit(-1);
        }
        try {
            fireImage[1] = ImageIO.read(getClass().getResource("fire11.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire11.png");
            System.exit(-1);
        }
        try {
            fireImage[2] = ImageIO.read(getClass().getResource("fire12.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire12.png");
            System.exit(-1);
        }
        try {
            fireImage[3] = ImageIO.read(getClass().getResource("fire13.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire13.png");
            System.exit(-1);
        }
        try {
            fireImage[4] = ImageIO.read(getClass().getResource("fire14.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire14.png");
            System.exit(-1);
        }
        try {
            fireImage[5] = ImageIO.read(getClass().getResource("fire15.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire15.png");
            System.exit(-1);
        }
        try {
            fireImage[6] = ImageIO.read(getClass().getResource("fire16.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire16.png");
            System.exit(-1);
        }
        try {
            fireImage[7] = ImageIO.read(getClass().getResource("fire17.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire17.png");
            System.exit(-1);
        }
        try {
            fireImage[8] = ImageIO.read(getClass().getResource("fire18.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire18.png");
            System.exit(-1);
        }
        try {
            fireImage[9] = ImageIO.read(getClass().getResource("fire19.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open fire19.png");
            System.exit(-1);
        }
    }
}

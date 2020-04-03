package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Shooter1 extends Shooter {

    private Image image;
    private Image[] damageImage;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    public Shooter1(int x, int y, boolean shield) {
        super(x, y);
        super.state = GameFigureState.STATE_SHIP_APPEAR;
        health = 5;
        this.shield = shield;
        shieldImageType = 0;
        velX = 0;
        velY = 0;
        image = null;
        damageImage = new Image[3];

        getImage();

    }

    @Override
    public void render(Graphics2D g) {
        //System.out.println(state);
        if (state == GameFigureState.STATE_DONE) {

        } else {
            drawPlayerShip(g);
            drawDamege(g);
            drawFire(g);
            if (shield) {
                drawShield(g);
            }
        }

//        Rectangle2D.Float collisionBox = new Rectangle2D.Float
//        (super.x - WIDTH / 100, super.y - HEIGHT / 100, WIDTH, HEIGHT);
//        g.setColor(Color.red);
//        g.fill(collisionBox);
    }

    @Override
    public void update() {
        //System.out.println(shield);
        tick();
        updateFireImage();
        if(shield){
            updateShieldImage();
        }

    }

    @Override
    public double getvelX() {
        return velX;
    }

    @Override
    public void translate(int dx, int dy) {
        super.x += dx;
        super.y += dy;
    }

    @Override
    public Rectangle2D getCollisionBox() {
        Rectangle2D.Float collisionBox = new Rectangle2D.Float(super.x - WIDTH / 100, super.y - HEIGHT / 100, WIDTH, HEIGHT);
        return collisionBox;

    }

    @Override
    public void setVelY(int velY) {
        this.velY = velY;
    }

    @Override
    public void setVelX(int velX) {
        this.velX = velX;

    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (x > xMax) {
            x = xMax;
        }
        if (x < xMin) {
            x = xMin;
        }
        if (y > yMax) {
            y = yMax;
        }
        if (y < yMin) {
            y = yMin;
        }

    }

    @Override
    public float getXofMissileShoot() {
        return x + WIDTH / 2 - 15;
    }

    @Override
    public float getYofMissileShoot() {
        return y - HEIGHT / 2;
    }

    @Override
    public float getXofBulletShoot() {
        return x + WIDTH / 2 - 5;
    }

    @Override
    public float getYofBulletShoot() {
        return y - HEIGHT / 2;
    }

    @Override
    public void drawPlayerShip(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
                WIDTH, HEIGHT, null);
    }

    @Override
    public void drawShield(Graphics2D g) {
        g.drawImage(shieldImage[shieldImageType],(int) super.x, (int) super.y,
                WIDTH, HEIGHT, null );
    }

    @Override
    public void drawDamege(Graphics2D g) {
        if (super.health == 5) {

        } else if (super.health == 4) {
            g.drawImage(damageImage[0], (int) super.x, (int) super.y,
                    WIDTH, HEIGHT, null);
        } else if (super.health == 3) {
            g.drawImage(damageImage[1], (int) super.x, (int) super.y,
                    WIDTH, HEIGHT, null);
        } else {
            g.drawImage(damageImage[2], (int) super.x, (int) super.y,
                    WIDTH, HEIGHT, null);
        }
    }

    @Override
    public void getImage() {
        try {
            image = ImageIO.read(getClass().getResource("playerShip1_orange.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open playerShip1_orange.png");
            System.exit(-1);
        }
        try {
            damageImage[0] = ImageIO.read(getClass().getResource("playerShip1_damage1.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open playerShip1_damage1.png");
            System.exit(-1);
        }
        try {
            damageImage[1] = ImageIO.read(getClass().getResource("playerShip1_damage2.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open playerShip1_damage2.png");
            System.exit(-1);
        }
        try {
            damageImage[2] = ImageIO.read(getClass().getResource("playerShip1_damage3.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open playerShip1_damage3.png");
            System.exit(-1);
        }
    }

    @Override
    public void updateShieldImage() {
        if(shieldImageType == 0){
            shieldImageType = 1;
        }else if(shieldImageType == 1){
            shieldImageType = 2;
        }else if(shieldImageType == 2){
            shieldImageType = 0;
        }
    }

    @Override
    public void drawFire(Graphics2D g) {
        g.drawImage(fireImage[fireImageType],(int) super.x+20, (int) super.y+50,
                FIRE_WIDTH, FIRE_HEIGHT, null );
    }

    @Override
    public void updateFireImage() {
        fireImageType++;
        if(fireImageType == 10)
            fireImageType = 0;
    }
}

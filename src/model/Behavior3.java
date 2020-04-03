package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

public class Behavior3 implements Animation {

    float x;
    float y;
    int WIDTH;
    int HEIGHT;
    private Image image;
    private final int UNIT_TRAVEL;

    public Behavior3(float x, float y, int width, int height, int unitTravel,Image image) {
        this.x = x;
        this.y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.image = image;
        UNIT_TRAVEL = unitTravel;
    }

    @Override
    public void update() {

        y -= UNIT_TRAVEL;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(image, (int) x - WIDTH / 2, (int) y - HEIGHT / 2,
                WIDTH, HEIGHT, null);
//        g2.setColor(Color.red);
//        g2.fill(new Rectangle2D.Float((int) (x - WIDTH / 2), (int) (y - HEIGHT / 2), WIDTH, HEIGHT));

    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }


}


package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

public interface Animation {
    void update();
    void render(Graphics2D g2);
    float getX();
    float getY();
}
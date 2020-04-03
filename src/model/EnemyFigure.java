package model;

import controller.Observer;
import java.awt.Graphics2D;

public abstract class EnemyFigure extends GameFigure implements Observer {


    public Animation animation;
    public int level;


    public EnemyFigure(float x, float y) {
        super(x, y);
    }

    public abstract void setAnimation(Animation animation);

    
    public abstract void setHealth();

    
}

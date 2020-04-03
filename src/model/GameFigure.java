
package model;

import java.awt.Graphics2D;


public abstract class GameFigure implements CollisionBox{
    
    public float x;
    public float y;
    public int health;
    public int state;
    public boolean shield;
    public int shieldHealth;
    
    public GameFigure(float x, float y){
        this.x = x;
        this.y = y;
        shieldHealth = 5;
    }
    
    public abstract void render(Graphics2D g);
    
    public abstract void update();
    
//    public abstract boolean isEnter();
}

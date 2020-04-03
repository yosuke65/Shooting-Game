package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExplosionAsteroid3 extends GameFigure {

    public final int NUMBER_OF_DEBRIS = 2;
    private final List<Meteor> debris;
    private int dx = 2;
    private int dy = 2;
    private final int UNIT_TRAVEL = 1;
    private int distance;
    private final int MAX_TRAVEL = 1000;

    public ExplosionAsteroid3(float x, float y) {
        super(x, y);
        super.state = GameFigureState.STATE_EXPLOSION;

        debris = new CopyOnWriteArrayList<>();
        getDebris();

    }

    @Override
    public void update() {
        updateState();
        updateLocation();
    }

    @Override
    public void render(Graphics2D g2) {
        for (int i = 0; i < NUMBER_OF_DEBRIS; i++) {
            debris.get(i).render(g2);
        }
    }


    @Override
    public Rectangle2D getCollisionBox() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateState() {
        if(distance >= MAX_TRAVEL){
            super.state = GameFigureState.STATE_DONE;
        }
    }

    private void updateLocation() {

            debris.get(0).x -= dx * Math.cos(Math.toRadians(250));
            debris.get(0).y -= dy * Math.sin(Math.toRadians(250));
            debris.get(1).x -= dx * Math.cos(Math.toRadians(290));
            debris.get(1).y -= dy * Math.sin(Math.toRadians(290));
            distance += UNIT_TRAVEL;
        
    }

    private void getDebris() {
        for(int i = 0; i < NUMBER_OF_DEBRIS; i++){
            debris.add(new Meteor(x,y));
        }
    }

}

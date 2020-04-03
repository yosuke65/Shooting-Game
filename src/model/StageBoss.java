package model;

import controller.Subject;


public abstract class StageBoss extends EnemyFigure implements Subject{
    
    public boolean isEnter;
    public StageBoss (float x, float y){
        super(x,y);
    }
}

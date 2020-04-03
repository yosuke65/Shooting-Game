package model;

public class GameFigureState {

    public static final int STATE_DONE = 0;
    public static final int STATE_COLLIGION = 1;
    public static final int STATE_DISAPPEARED = 11;
    public static final int STATE_NORMAL = 44;
    public static int STATE_DAMAGED = 110;

    public static final int SHOOTER_STATE_HEALTH_LEVEL_5 = 2;

    public static final int MISSILE_STATE_LAUNCHED = 3;

    public static final int BULLET_STATE_LAUNCHED = 4;



    public static int GAMEOVER = 10;
    public static int STATE_EXPLOSION =12;
    
    public static int MOVE_HORIZONTALLY = 14;
    public static int MOVE_FORWARD = 15;
    public static int MOVE_BACKWARD = 18;
    public static int MOVE_RIGHT = 16;
    public static int MOVE_LEFT = 17;
    
    public static int STATE_BOSS_APPEAR = 99;
    public static int STATE_BOSS_BATTLE = 199;
    public static int GAME_STAGE_ONE = 200;
    public static int GAME_STAGE_TWO = 201;
    public static int GAME_STAGE_THREE = 202;
    public static int GAME_STAGE_LOOP = 203;
    
    public static int STATE_SHIP_APPEAR = 300;
    public static int STATE_SHIP_BATTLE = 301;
    
    
   

}

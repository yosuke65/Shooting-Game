package model;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

public class BossBehavior1 implements Animation {

    private float x;
    private float y;
    private final int enemyWidth;
    private final int enemyHeight;
    private final Image image;
    private final int UNIT_TRAVEL;
    private final int TRIGGER_MOVE;
    private final int TRIGGER_SHOOT;
    //private int xDistance;
    //private int yDistance;
    private int distance;
    private int shootDistance;
    //private final int MAX_X_TRAVEL;
//    private final int DIRECTION_RIGHT = 1;
//    private final int DIRECTION_LEFT = 2;
    //private int direction;
    private int state;
    private final int X_MAX = Main.WIN_WIDTH;
    private final int X_MIN = 0;
    private final int Y_MIN = Main.WIN_HEIGHT / 2;
    private final int Y_MAX = 0;

    public BossBehavior1(float x, float y, int width, int height, int unitTravel, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        enemyWidth = width;
        enemyHeight = height;
        state = GameFigureState.MOVE_FORWARD;
        Random rand = new Random();
        TRIGGER_MOVE = rand.nextInt(400) + 200;
        TRIGGER_SHOOT = rand.nextInt(50) + 30;
//        MAX_X_TRAVEL = rand.nextInt(100) + 50;
        UNIT_TRAVEL = unitTravel;
    }

    @Override
    public void update() {
        //System.out.println("x: "+x +"y: "+y);
        stateUpdate();
        if (state == GameFigureState.MOVE_FORWARD) {
            forward();
        }
        if (state == GameFigureState.MOVE_RIGHT) {
            slideRight();
        }
        if (state == GameFigureState.MOVE_LEFT) {
            slideLeft();
        }
        if (state == GameFigureState.MOVE_BACKWARD) {
            backward();
        }
        if (shootDistance >= TRIGGER_SHOOT) {
            shoot();
        }

    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(image, (int) x - enemyWidth / 2, (int) y - enemyHeight / 2,
                enemyWidth, enemyHeight, null);
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

    private void shoot() {
        Main.gameData.enemyFigures.add(new EnemyBullet(this.x - (enemyWidth / 100), this.y - (enemyHeight / 100) + 10));
        shootDistance = 0;
    }
    
    private void randomDirection(int state) {
        while (this.state == state) {
            Random rand = new Random();
            int direction = rand.nextInt(4) + 1;
            //System.out.println(direction);
            if (direction == 1) {
                this.state = GameFigureState.MOVE_RIGHT;
            } else if (direction == 2) {
                this.state = GameFigureState.MOVE_LEFT;
            } else if (direction == 3) {
                this.state = GameFigureState.MOVE_FORWARD;
            } else if (direction == 4) {
                this.state = GameFigureState.MOVE_BACKWARD;
            }
            //           System.out.println(state);
//            if (x >= X_MAX - 200) {
//                this.state = GameFigureState.MOVE_LEFT;
//            }
//            if (x <= X_MIN + 200) {
//                this.state = GameFigureState.MOVE_RIGHT;
//            }
        }
    }

    private void stateUpdate() {
        if (distance >= TRIGGER_MOVE) {
            randomDirection(this.state);
            distance = 0;
        }

    }

    private void forward() {
        y += UNIT_TRAVEL;
        if (y >= Y_MIN) {
            randomDirection(state);
            //backward();
        }
        //yDistance += UNIT_TRAVEL;
        distance += UNIT_TRAVEL;
        shootDistance += UNIT_TRAVEL;
    }

    private void backward() {
        y -= UNIT_TRAVEL;
        if (y <= Y_MAX + 50) {
            randomDirection(state);
            //forward();
        }
        //yDistance += UNIT_TRAVEL;
        distance += UNIT_TRAVEL;
        shootDistance += UNIT_TRAVEL;
    }

    private void slideRight() {
        x += UNIT_TRAVEL;
        if (x >= X_MAX - 50) {
            randomDirection(state);
            //slideLeft();
        }
        //xDistance += UNIT_TRAVEL;
        distance += UNIT_TRAVEL;
        shootDistance += UNIT_TRAVEL;
    }

    private void slideLeft() {
        x -= UNIT_TRAVEL;
        if (x <= X_MIN + 50) {
            randomDirection(state);
            //slideRight();
        }
        //xDistance += UNIT_TRAVEL;
        distance += UNIT_TRAVEL;
        shootDistance += UNIT_TRAVEL;
    }
}

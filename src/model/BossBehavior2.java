
package model;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

public class BossBehavior2 implements Animation {

    private float x;
    private float y;
    private final int enemyWidth;
    private final int enemyHeight;
    private final Image image;
    private final int UNIT_TRAVEL;
    private final int TRIGGER_SHOOT;
    private int xDistance;
    private int yDistance;
    private int distance;
    private final int MAX_X_TRAVEL;
    private final int DIRECTION_RIGHT = 1;
    private final int DIRECTION_LEFT = 2;
    private int direction;
    private int state;
    private final int X_MAX = Main.WIN_WIDTH;
    private final int X_MIN = 0;

    public BossBehavior2(float x, float y, int width, int height, int unitTravel, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        enemyWidth = width;
        enemyHeight = height;
        randomDirection();
        Random rand = new Random();
        TRIGGER_SHOOT = 20;
        MAX_X_TRAVEL = 200;
        UNIT_TRAVEL = unitTravel;
    }

    @Override
    public void update() {

        if (state == GameFigureState.MOVE_RIGHT) {
            slideRight();
        }
        if (state == GameFigureState.MOVE_LEFT) {
            slideLeft();
        }
        if (distance >= TRIGGER_SHOOT) {
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
        distance = 0;
    }

    private void randomDirection() {
        Random rand = new Random();
        int direction = rand.nextInt(2) + 1;
        //System.out.println(direction);
        if (direction == 1) {
            state = GameFigureState.MOVE_RIGHT;
        } else {
            state = GameFigureState.MOVE_LEFT;
        }
//        if (x >= X_MAX - 100) {
//            state = GameFigureState.MOVE_LEFT;
//        }
//        if (x <= X_MIN + 100) {
//            state = GameFigureState.MOVE_RIGHT;
//        }
    }




    private void slideRight() {
        x += UNIT_TRAVEL;
        if (x >= X_MAX) {
            x = X_MAX;
            state = GameFigureState.MOVE_LEFT;
        }
        xDistance += UNIT_TRAVEL;
        distance += UNIT_TRAVEL;
    }

    private void slideLeft() {
        x -= UNIT_TRAVEL;
        if (x <= X_MIN) {
            x = X_MIN;
            state = GameFigureState.MOVE_RIGHT;
        }
        xDistance += UNIT_TRAVEL;
        distance += UNIT_TRAVEL;
    }
}

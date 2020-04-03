package model;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

public class Behavior2 implements Animation {

    private float x;
    private float y;
    private final int enemyWidth;
    private final int enemyHeight;
    private final Image image;
    private final int UNIT_TRAVEL;
    private final int TRIGGER_MOVE;
    private final int TRIGGER_SHOOT;
    private int xDistance;
    private int yDistance;
    private final int MAX_X_TRAVEL;
    private final int DIRECTION_RIGHT = 1;
    private final int DIRECTION_LEFT = 2;
    private int direction;
    private int state;
    private final int X_MAX = Main.WIN_WIDTH;
    private final int X_MIN = 0;

    public Behavior2(float x, float y, int width, int height, int unitTravel, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        enemyWidth = width;
        enemyHeight = height;
        state = GameFigureState.MOVE_FORWARD;
        Random rand = new Random();
        TRIGGER_MOVE = rand.nextInt(100) + 50;
        TRIGGER_SHOOT = rand.nextInt(50) + 50;
        MAX_X_TRAVEL = rand.nextInt(100) + 50;
        UNIT_TRAVEL = unitTravel;
    }

    @Override
    public void update() {
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


    private void randomDirection() {
        Random rand = new Random();
        int direction = rand.nextInt(2) + 1;
        //System.out.println(direction);
        if (direction == 1) {
            state = GameFigureState.MOVE_RIGHT;
        } else {
            state = GameFigureState.MOVE_LEFT;
        }
        if (x >= X_MAX - 200) {
            state = GameFigureState.MOVE_LEFT;
        }
        if (x <= X_MIN + 200) {
            state = GameFigureState.MOVE_RIGHT;
        }
    }

    private void stateUpdate() {
        if (yDistance >= TRIGGER_MOVE) {
            randomDirection();
            yDistance = 0;
        }
        if (xDistance >= MAX_X_TRAVEL) {
            state = GameFigureState.MOVE_FORWARD;
            xDistance = 0;
        }
    }

    private void forward() {
        y += UNIT_TRAVEL;
        yDistance += UNIT_TRAVEL;
    }

    private void slideRight() {
        x += UNIT_TRAVEL;
        if (x >= X_MAX) {
            x = X_MAX;
            state = GameFigureState.MOVE_FORWARD;
        }
        xDistance += UNIT_TRAVEL;
    }

    private void slideLeft() {
        x -= UNIT_TRAVEL;
        if (x <= X_MIN) {
            x = X_MIN;
            state = GameFigureState.MOVE_FORWARD;
        }
        xDistance += UNIT_TRAVEL;
    }
}

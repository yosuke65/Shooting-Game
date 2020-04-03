package controller;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GameFigureState;
import model.Missile;

public class Animator implements Runnable {

    public boolean running = true;
    private final int FRAMES_PER_SECOND = 70;
    public float spawnTimer = 0;
    public float spawnTime = 0;
    public long time;
    //on windows
//    private final float MAX_TIME = 100;
//    private final float MIN_TIME = 50;
    //om MacOs
    private final float MAX_TIME = 1000;
    private final float MIN_TIME = 500;

    @Override
    public void run() {

        while (running) {
            long startGameTime = System.nanoTime();
            long startTime = System.currentTimeMillis();

            processCollisions();

            Main.gameData.update();
            try {
                Main.gamePanel.gameRender();
            } catch (FontFormatException ex) {
                Logger.getLogger(Animator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Animator.class.getName()).log(Level.SEVERE, null, ex);
            }
            Main.gamePanel.printScreen();
            if (spawnTimer == 0) {
                Random rand = new Random();
                spawnTimer = rand.nextFloat() * (MAX_TIME - MIN_TIME) + MIN_TIME;
            }

            long endGameTime = System.nanoTime();
            time += (endGameTime - startGameTime) / 1000000;
//            System.out.println("SpawnTimer: " + spawnTimer);
//            System.out.println("Time: " + time);
            if (time >= spawnTimer) {
                Main.gameData.Spawner();
                spawnTimer = 0;
                time = 0;
            }

            long endTime = System.currentTimeMillis();

            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {

                }
            }
        }
        System.exit(0);
    }

    private void processCollisions() {
        // detect collisions between friendFigure and enemyFigures
        // if detected, mark it as STATE_DONE, so that
        // they can be removed at update() method
        if (Main.gameData.gameState != GameFigureState.GAMEOVER) {
            for (int i = 0; i < Main.gameData.friendFigures.size(); i++) {
                for (int j = 0; j < Main.gameData.enemyFigures.size(); j++) {
                    if (Main.gameData.friendFigures.get(i).getCollisionBox().intersects(
                            Main.gameData.enemyFigures.get(j).getCollisionBox())) {
                        if (Main.gameData.friendFigures.get(i).getClass() == Missile.class) {
                            Main.gameData.friendFigures.get(i).state = GameFigureState.MISSILE_STATE_LAUNCHED;
                            Main.gameData.enemyFigures.get(j).state = GameFigureState.STATE_DONE;
                        }else{
                            Main.gameData.friendFigures.get(i).state = GameFigureState.STATE_COLLIGION;
                            Main.gameData.enemyFigures.get(j).state = GameFigureState.STATE_COLLIGION;
                        }
                        if (i == 0) {
                            Main.gameData.enemyFigures.get(j).state = GameFigureState.STATE_DONE;
                        }
                    }

                }
                if (Main.gameData.gameState == GameFigureState.STATE_BOSS_BATTLE) {
                    for (int j = 0; j < Main.gameData.bossFigures.size(); j++) {
                        if (Main.gameData.friendFigures.get(i).getCollisionBox().intersects(
                                Main.gameData.bossFigures.get(j).getCollisionBox())) {
                            Main.gameData.friendFigures.get(i).state = GameFigureState.STATE_COLLIGION;
                            Main.gameData.bossFigures.get(j).state = GameFigureState.STATE_COLLIGION;
                        }
                    }
                }
                if (i == 0) {
                    for (int j = 0; j < Main.gameData.itemFigures.size(); j++) {
                        if (Main.gameData.friendFigures.get(i).getCollisionBox().intersects(
                                Main.gameData.itemFigures.get(j).getCollisionBox())) {
                            Main.gameData.friendFigures.get(i).state = GameFigureState.STATE_NORMAL;
                            Main.gameData.itemFigures.get(j).state = GameFigureState.STATE_COLLIGION;
                        }
                    }
                }
            }
        }
    }
}

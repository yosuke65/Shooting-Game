package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Bullet;
import model.GameFigureState;
import model.Missile;
import model.Shooter;

public class KeyController extends KeyAdapter {

    public boolean keyPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {

        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                shooter.setVelX(-5);
                break;
            case KeyEvent.VK_RIGHT:
                shooter.setVelX(5);
                break;
            case KeyEvent.VK_UP:
                shooter.setVelY(-5);
                break;
            case KeyEvent.VK_DOWN:
                shooter.setVelY(5);
                break;
//            case KeyEvent.VK_Q:
//                Main.gameData.scoreCounter = 1000;
//                break;
//            case KeyEvent.VK_W:
//                Main.gameData.gameLevel = 1;
//                break;
//            case KeyEvent.VK_E:
//                Main.gameData.gameLevel = 2;
//                break;
//            case KeyEvent.VK_R:
//                Main.gameData.gameLevel = 3;
//                break;
//            case KeyEvent.VK_T:
//                Main.gameData.addAsteroid();
//                break;
//            case KeyEvent.VK_Y:
//                Main.gameData.addUFO();
//                break;
//            case KeyEvent.VK_U:
//                Main.gameData.addSpaceship();
//                break;
//            case KeyEvent.VK_I:
//                Main.gameData.addItemBomb();
//                break;
//            case KeyEvent.VK_O:
//                Main.gameData.addItemMissile();
//                break;
//            case KeyEvent.VK_P:
//                Main.gameData.addItemShield();
//                break;
//            case KeyEvent.VK_F:
//                Main.gameData.addItemPowerUp();
//                break;
//        }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                shooter.setVelX(0);
//                System.out.println("x= " + shooter.x);
//                System.out.println("y= " + shooter.y);
                break;
            case KeyEvent.VK_RIGHT:
                shooter.setVelX(0);
//                System.out.println("x= " + shooter.x);
//                System.out.println("y= " + shooter.y);
                break;
            case KeyEvent.VK_UP:
                shooter.setVelY(0);
//                System.out.println("x= " + shooter.x);
//                System.out.println("y= " + shooter.y);
                break;
            case KeyEvent.VK_DOWN:
                shooter.setVelY(0);
//                System.out.println("x= " + shooter.x);
//                System.out.println("y= " + shooter.y);
                break;
            case KeyEvent.VK_S:
                //Shoot Bullet
                Bullet bl = new Bullet(
                        shooter.getXofBulletShoot(),
                        shooter.getYofBulletShoot()
                );
                Main.gameData.friendFigures.add(bl);
                break;
            case KeyEvent.VK_X:
                //Shoot Missile
                if (Main.gameData.missileCount >= 1) {
                    Missile m = new Missile(
                            shooter.getXofMissileShoot(),
                            shooter.getYofMissileShoot()
                    );
                    Main.gameData.friendFigures.add(m);
                    Main.gameData.missileCount--;
                }

                break;
            case KeyEvent.VK_A:
                //Use Bomb
                if (Main.gameData.bombCount >= 1) {
                    Main.gameData.bombCount--;
                    for (int i = 0; i < Main.gameData.enemyFigures.size(); i++) {
                        Main.gameData.enemyFigures.get(i).state = GameFigureState.STATE_DONE;
                    }

                }
                break;
        }
    }
}

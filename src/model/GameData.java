package model;

import controller.LevelObserver;
import controller.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import view.GamePanel;

public class GameData {
   
    public final List<EnemyFigure> enemyFigures;
    public final List<GameFigure> friendFigures;
    public final List<GameFigure> itemFigures;
    public final List<GameFigure> effectFigures;
    public final List<StageBoss> bossFigures;
    public final GameFigure hudPanel;
    public int gameScore;
    public int gameLevel;
    public int gameState;

    public static Shooter shooter;
    public int enemyCount;
    public int asteroidCount;
    public int ufoCount;
    public int spaceshipCount;
    public int bombCount = 5;
    public int missileCount =5;
    public int killCount;
    public int scoreCounter;
    public int shipType;
    public final int MAX_ASTEROID_COUNT = 3;
    public final int MAX_UFO_COUNT = 3;
    public final int MAX_SPACESHIP_COUNT = 3;
    public final int MAX_ENEMY_COUNT = 5;
    public LevelObserver levelObserver;

    public GameData() {
        enemyFigures = new CopyOnWriteArrayList<>();
        friendFigures = new CopyOnWriteArrayList<>();
        itemFigures = new CopyOnWriteArrayList<>();
        effectFigures = new CopyOnWriteArrayList<>();
        bossFigures = new CopyOnWriteArrayList<>();
        hudPanel = new HudPanel(10, 10);

        gameScore = 0;
        gameLevel = 1;
        gameState = GameFigureState.GAME_STAGE_ONE;
        enemyCount = 0;
        asteroidCount = 0;
        spaceshipCount = 0;
        shooter = new Shooter1(Main.WIN_WIDTH / 2 - 5, Main.WIN_HEIGHT - 140, false);
        friendFigures.add(shooter);
        levelObserver = new LevelObserver();
    }

    public void update() {

            System.out.println(" ");
        ArrayList<EnemyFigure> removeEnemies = new ArrayList<>();
        EnemyFigure e;
        for (int i = 0; i < enemyFigures.size(); i++) {
            e = enemyFigures.get(i);
            if (e.state == GameFigureState.STATE_COLLIGION) {
                e.health--;
                e.state = GameFigureState.STATE_DAMAGED;
                if (e.health == 0) {
                    e.state = GameFigureState.STATE_DONE;
                }
            }
            if (e.state == GameFigureState.STATE_DONE
                    || e.state == GameFigureState.STATE_DISAPPEARED) {
                if (e.getClass() == Asteroid.class) {
                    asteroidCount--;
                    if (e.state == GameFigureState.STATE_DONE) {
                        gameScore += 10;
                        scoreCounter += 10;
                        killCount++;
                        //effectFigures.add(new ExplosionAsteroid(e.x, e.y));
                        effectFigures.add(new ExplosionAsteroid3(e.x, e.y));
                    }
                }
                if (e.getClass() == Asteroid2.class) {
                    asteroidCount--;
                    if (e.state == GameFigureState.STATE_DONE) {
                        gameScore += 20;
                        scoreCounter += 20;
                        killCount++;
                        //effectFigures.add(new ExplosionAsteroid(e.x, e.y));
                        effectFigures.add(new ExplosionAsteroid3(e.x, e.y));
                    }
                }
                if (e.getClass() == FlyingSaucer.class) {
                    ufoCount--;
                    if (e.state == GameFigureState.STATE_DONE) {
                        gameScore += 30;
                        scoreCounter += 30;
                        killCount++;
                        effectFigures.add(new ExplosionUFO2(e.x, e.y));
                    }
                }
                if (e.getClass() == Spaceship.class) {
                    spaceshipCount--;
                    if (e.state == GameFigureState.STATE_DONE) {
                        gameScore += 50;
                        scoreCounter += 50;
                        killCount++;
                        effectFigures.add(new ExplosionSpaceship(e.x, e.y));
                    }
                }
                if (e.getClass() == EnemyBullet.class) {
                    if (e.state == GameFigureState.STATE_DONE) {
                        effectFigures.add(new ExplosionBullet(e.x, e.y));
                    }
                }

                removeEnemies.add(e);
                enemyCount--;
            }
        }
        enemyFigures.removeAll(removeEnemies);

        for (EnemyFigure g : enemyFigures) {
            g.update();
        }

        // missiles are removed if explosion is done
        GameFigure f;
        ArrayList<GameFigure> removeFriends = new ArrayList<>();
        for (int i = 0; i < friendFigures.size(); i++) {
            f = friendFigures.get(i);
            if (f.state == GameFigureState.STATE_COLLIGION) {
                f.state = GameFigureState.STATE_NORMAL; 
                if(!f.shield){
                    f.health--;
                }else{
                    f.shieldHealth--;
                    if(f.shieldHealth == 0){
                        f.shield = false;
                    }
                }
                if (f.health == 0) {
                    f.state = GameFigureState.STATE_DONE;
                }
            }
            if (f.state == GameFigureState.STATE_DONE || f.state == GameFigureState.STATE_DISAPPEARED) {
                if (f instanceof Shooter) {
                    gradeDownShip();

                } else {
                    removeFriends.add(f);

                    if (f.state == GameFigureState.STATE_DONE) {
                        if (f.getClass() == Bullet.class) {
                            effectFigures.add(new ExplosionBullet(f.x, f.y));
                        }
                        if (f.getClass() == Missile.class) {
                            effectFigures.add(new ExplosionMissile(f.x, f.y));
                        }
                    }
                }

            }
        }
        friendFigures.removeAll(removeFriends);

        for (GameFigure g : friendFigures) {
            g.update();

        }

        ArrayList<GameFigure> removeItems = new ArrayList<>();
        for (int i = 0; i < itemFigures.size(); i++) {
            f = itemFigures.get(i);
            if (f.state == GameFigureState.STATE_COLLIGION) {
                removeItems.add(f);
                if (f.getClass() == ItemBomb.class) {
                    bombCount++;
                }
                if (f.getClass() == ItemMissile.class) {
                    missileCount++;
                }
                if (f.getClass() == ItemShield.class) {
                    friendFigures.get(0).shield = true;
                    friendFigures.get(0).shieldHealth = 5;
                }
                if (f.getClass() == ItemPowerUpShip.class) {
                    gradeUpShip();
                }

            }
        }
        itemFigures.removeAll(removeItems);

        for (GameFigure g : itemFigures) {
            g.update();

        }

        ArrayList<GameFigure> removeExplosion = new ArrayList<>();
        for (int i = 0; i < effectFigures.size(); i++) {
            f = effectFigures.get(i);
            if (f.state == GameFigureState.STATE_DONE) {
                removeExplosion.add(f);
            }
        }
        effectFigures.removeAll(removeExplosion);

        for (GameFigure g : effectFigures) {
            g.update();
        }

        StageBoss s;
        ArrayList<StageBoss> removeBoss = new ArrayList<>();
        for (int i = 0; i < bossFigures.size(); i++) {
            s = bossFigures.get(i);
            if (s.state == GameFigureState.STATE_COLLIGION) {
                s.state = GameFigureState.STATE_DAMAGED;
                if (!s.isEnter) {
                    s.health--;

                } else {
                    s.state = GameFigureState.STATE_BOSS_APPEAR;
                }
                if (s.health == 0) {
                    s.state = GameFigureState.STATE_DONE;
                    effectFigures.add(new ExplosionBoss(s.x, s.y));
                    //System.out.println("STATE_DONE");
                    removeBoss.add(s);
                    s.notifyObservers();
                }
            }
        }

        bossFigures.removeAll(removeBoss);

        for (GameFigure g : bossFigures) {
            g.update();
        }

        hudPanel.update();

        gameStateUpdate();
    }

    public void addAsteroid() {

        Asteroid asteroid;
        if (enemyCount < MAX_ENEMY_COUNT && asteroidCount < MAX_ASTEROID_COUNT) {
            Random rand = new Random();
            int type = rand.nextInt(2) + 1;
            if (type == 1) {
                asteroid = new Asteroid((int) (Math.random() * (GamePanel.width - 55)), 10);
                enemyFigures.add(asteroid);
            } else {
                asteroid = new Asteroid2((int) (Math.random() * (GamePanel.width - 55)), 10);
                enemyFigures.add(asteroid);
            }
            enemyCount++;
            asteroidCount++;
        }
    }

    public void addUFO() {
        FlyingSaucer ufo;
        if (enemyCount < MAX_ENEMY_COUNT && ufoCount < MAX_UFO_COUNT) {
            ufo = new FlyingSaucer((int) (Math.random() * (GamePanel.width - 55)), 10);
            enemyFigures.add(ufo);
            enemyCount++;
            ufoCount++;
        }
    }

    public void addSpaceship() {
        Spaceship spaceship;
        if (enemyCount < MAX_ENEMY_COUNT && spaceshipCount < MAX_SPACESHIP_COUNT) {
            spaceship = new Spaceship((int) (Math.random() * (GamePanel.width - 55)), 10);
            enemyFigures.add(spaceship);
            enemyCount++;
            spaceshipCount++;
        }
    }

    public void Spawner() {
        //System.out.println(gameState);
        if (gameState != GameFigureState.STATE_BOSS_APPEAR && gameState != GameFigureState.STATE_BOSS_BATTLE) {
            Random rand = new Random();
            int type = rand.nextInt(3) + 1;
            //System.out.println(type);
            if (type == 1) {
                addAsteroid();
            } else if (type == 2) {
                addUFO();
            } else {
                addSpaceship();
            }
        }
        if (gameState == GameFigureState.STATE_BOSS_APPEAR) {
            addBoss();
            scoreCounter = 0;
        }
        if (killCount >= 10) {
            addRandomItem();
            killCount = 0;
        }
    }

    private void addBoss() {
        if (gameLevel == 1) {
            StageBoss1 b = new StageBoss1(Main.WIN_WIDTH / 2, 10);
            bossFigures.add(b);
            b.addObserver(levelObserver);
        } else if (gameLevel == 2) {
            StageBoss2 b = new StageBoss2(Main.WIN_WIDTH / 2, 10);
            bossFigures.add(b);
            b.addObserver(levelObserver);
        } else if (gameLevel == 3) {
            StageBoss3 b = new StageBoss3(Main.WIN_WIDTH / 2, 10);
            bossFigures.add(b);
            b.addObserver(levelObserver);
        } else if (gameLevel >= 4) {
            StageBoss3 b = new StageBoss3(Main.WIN_WIDTH / 2, 10);
            bossFigures.add(b);
            b.addObserver(levelObserver);
        }
        gameState = GameFigureState.STATE_BOSS_BATTLE;
    }

    private void gameStateUpdate() {
        if (scoreCounter >= 1000) {
            gameState = GameFigureState.STATE_BOSS_APPEAR;
            //scoreCounter = 0;
        }
    }

    public void addRandomItem() {
//        itemFigures.add(new ItemBomb((int) (Math.random() * (GamePanel.width - 55)), 10));
//        itemFigures.add(new ItemMissile((int) (Math.random() * (GamePanel.width - 55)), 10));
//        itemFigures.add(new ItemShield((int) (Math.random() * (GamePanel.width - 55)), 10));
//        itemFigures.add(new ItemPowerUpShip((int) (Math.random() * (GamePanel.width - 55)), 10));

        Random rand = new Random();
        int type = rand.nextInt(4) + 1;
        //System.out.println(type);
        if (type == 1) {
            addItemBomb();
        } else if (type == 2) {
            addItemMissile();
        } else if (type == 3) {
            addItemShield();
        } else {
            addItemPowerUp();
        }
    }

    private void gradeUpShip() {

        Shooter p = (Shooter) friendFigures.get(0);
        if (p.getClass() == Shooter1.class) {
            friendFigures.set(0, new Shooter2((int) p.x,(int) p.y,p.shield));
        } else if (p.getClass() == Shooter2.class) {
            friendFigures.set(0, new Shooter3((int)p.x, (int)p.y,p.shield));
        } else {
            friendFigures.get(0).health = 5;
        }

    }

    private void gradeDownShip() {
        Shooter p = (Shooter) friendFigures.get(0);
        if (p.getClass() == Shooter1.class) {
            gameState = GameFigureState.GAMEOVER;
            //System.out.println(p.state);
        } else if (p.getClass() == Shooter2.class) {
            friendFigures.set(0, new Shooter1((int)p.x, (int)p.y,false));
        } else if (p.getClass() == Shooter3.class) {
            friendFigures.set(0, new Shooter2((int)p.x, (int)p.y, false));
        }
    }

    public void Initialize() {
        gameScore = 0;
        gameLevel = 1;
        gameState = GameFigureState.GAME_STAGE_ONE;
        enemyCount = 0;
        asteroidCount = 0;
        spaceshipCount = 0;
        friendFigures.set(0, new Shooter1(Main.WIN_WIDTH / 2, Main.WIN_HEIGHT + 20,false));
        enemyFigures.clear();
        itemFigures.clear();
        bossFigures.clear();
        effectFigures.clear(); 

    }

    public void addItemBomb() {
        itemFigures.add(new ItemBomb((int) (Math.random() * (GamePanel.width - 55)), 10));
    }

    public void addItemMissile() {
        itemFigures.add(new ItemMissile((int) (Math.random() * (GamePanel.width - 55)), 10));
    }

    public void addItemShield() {
        itemFigures.add(new ItemShield((int) (Math.random() * (GamePanel.width - 55)), 10));
    }

    public void addItemPowerUp() {
        itemFigures.add(new ItemPowerUpShip((int) (Math.random() * (GamePanel.width - 55)), 10));
    }
}

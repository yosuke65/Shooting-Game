package controller;

import model.GameFigureState;

public class LevelObserver implements Observer {

    @Override
    public void update(Event e) {
        Main.gameData.gameLevel++;
        switch (Main.gameData.gameLevel) {
            case 2:
                Main.gameData.gameState = GameFigureState.GAME_STAGE_TWO;
                break;
            case 3:
                Main.gameData.gameState = GameFigureState.GAME_STAGE_THREE;
                break;
            default:
                Main.gameData.gameState = GameFigureState.GAME_STAGE_LOOP;
                break;
        }

        System.out.println("Boss killed");
    }

}

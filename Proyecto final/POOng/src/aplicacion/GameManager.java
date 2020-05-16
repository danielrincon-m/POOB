package aplicacion;

import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntitySpawner;

import java.util.LinkedHashMap;

public class GameManager implements TimerListener {

    private final ApplicationManager applicationManager;
    private EntitySpawner entitySpawner;
    private GameTimer gameTimer;

    public GameManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        gameTimer = new GameTimer();
    }

    public void startGame() {
        intializeParameters();
        createGameObjects();
        Entity.startAll();
        gameTimer.addTimerListener(this, 1);
        gameTimer.start();
    }

    public void update() {
        Entity.updateAll();
    }

    public void pauseGame() {
        if (gameTimer.isStarted()) {
            LinkedHashMap<TimerListener, Integer> timerListeners = gameTimer.getListeners();
            gameTimer.cancel();
            //gameTimer.purge();
            gameTimer = new GameTimer();
            gameTimer.setListeners(timerListeners);
        }
    }

    public void resumeGame() {
        if (!gameTimer.isStarted()) {
            gameTimer.start();
        }
    }

    public void endGame() {
        gameTimer.cancel();
        gameTimer.purge();
        gameTimer = new GameTimer();
        Entity.removeAll();
    }

    public EntitySpawner getEntitySpawner() {
        return entitySpawner;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    private void intializeParameters() {
        Entity.removeAll();
        entitySpawner = new EntitySpawner(applicationManager);
    }

    private void createGameObjects() {
        entitySpawner.SpawnObjects();
    }
}

package aplicacion;

import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntitySpawner;

public class GameManager {

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
        gameTimer.start();
    }

    public void update() {
        Entity.updateAll();
    }

    public void endGame() {
        gameTimer.cancel();
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

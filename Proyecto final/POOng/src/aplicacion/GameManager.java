package aplicacion;

import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntitySpawner;
import presentacion.GameScreen;

public class GameManager {

    private final ApplicationManager applicationManager;
    private EntitySpawner entitySpawner;
    private GameTimer gameTimer;

    public GameManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        gameTimer = new GameTimer();
        //System.out.println(System.getProperty("user.dir"));
    }

    public void startGame() {
        intializeParameters();
        createGameObjects();
        Entity.defineAllComponents();
        Entity.startAll();
        startTime();
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

    private void startTime() {
        gameTimer.start();
    }

    private void intializeParameters() {
        Entity.removeAll();
        entitySpawner = new EntitySpawner(applicationManager);
    }

    private void createGameObjects() {
        entitySpawner.SpawnObjects();
    }
}

package aplicacion;

import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.engine.Timer.TimerListener;
import aplicacion.game.entities.Entity;
import aplicacion.game.entities.spawner.EntitySpawner;
import presentacion.GameScreen;

public class  GameManager implements TimerListener {

    private boolean running = false;

    private final ApplicationManager applicationManager;
    private GameScreen gameScreen;
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
        running = true;
    }

    public void update() {
        if (running) {
            Entity.updateAll();
        }
    }

    public void endGame() {
        Entity.removeAll();
    }

    public EntitySpawner getEntitySpawner() {
        return entitySpawner;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public void setGameScreen(GameScreen gs) {
        gameScreen = gs;
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

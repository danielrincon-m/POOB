package aplicacion;

import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;
import aplicacion.game.entities.spawner.EntitySpawner;
import presentacion.GameScreen;

public class GameManager {

    private final GameScreen gameScreen;
    private final GameProperties gameProperties;
    private EntitySpawner entitySpawner;
    private GameTimer gameTimer;

    public GameManager(ApplicationManager applicationManager, GameScreen gs) {
        this.gameProperties = applicationManager.getGameProperties();
        gameScreen = gs;
        //System.out.println(System.getProperty("user.dir"));
    }

    public void startGame() {
        intializeParameters();
        createGameObjects();
        Entity.defineAllComponents();
        Entity.startAll();
        gameTimer.start();
    }

    public void update() {
        Entity.updateAll();
        gameScreen.repaint();
    }

    public void endGame() {
        Entity.removeAll();
    }

    public EntitySpawner getEntitySpawner() {
        return entitySpawner;
    }

    private void intializeParameters() {
        Entity.removeAll();
        gameTimer = new GameTimer(this);
        entitySpawner = new EntitySpawner(gameProperties);
    }

    private void createGameObjects() {
        entitySpawner.SpawnObjects();
    }
}

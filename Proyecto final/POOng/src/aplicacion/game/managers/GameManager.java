package aplicacion.game.managers;

import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;
import aplicacion.game.entities.EntitySpawner;
import presentacion.GameScreen;

public class GameManager {

    private final GameScreen screen;
    private EntitySpawner entitySpawner;
    private GameTimer gameTimer;

    public GameManager(GameScreen screen) {
        this.screen = screen;
    }

    public void startGame() {
        intializeParameters();
        createGameObjects();
        Entity.startAll();
        gameTimer.start();
    }

    public void update() {
        Entity.updateAll();
        screen.repaint();
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
        entitySpawner = new EntitySpawner();
    }

    private void createGameObjects() {
        entitySpawner.SpawnObjects();
    }
}

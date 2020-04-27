package aplicacion.game.managers;

import aplicacion.game.engine.time.GameTimer;
import aplicacion.game.entities.Entity;

import javax.swing.*;

public class GameManager extends JFrame {

    private EntitySpawner entitySpawner;
    private GameTimer gameTimer;

    public void startGame(float gameWidth, float gameHeight) {
        intializeParameters(gameWidth, gameHeight);
        createGameObjects();
        Entity.startAll();
        gameTimer.start();
    }

    public void update() {
        Entity.updateAll();
    }

    public void endGame() {
        Entity.removeAll();
    }

    public EntitySpawner getEntitySpawner() {
        return entitySpawner;
    }

    private void intializeParameters(float gameWidth, float gameHeight) {
        Entity.removeAll();
        gameTimer = new GameTimer(this);
        entitySpawner = new EntitySpawner(gameWidth, gameHeight);
    }

    private void createGameObjects() {
        entitySpawner.SpawnObjects();
    }
}

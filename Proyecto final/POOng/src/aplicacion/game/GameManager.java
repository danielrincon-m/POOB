package aplicacion.game;

import aplicacion.POOngException;
import aplicacion.game.gameObject.GameObjectManager;

public class GameManager {
    private static GameManager instance;

    private GameObjectManager gameObjectManager;

    private float gameWidth;
    private float gameHeight;

    private GameManager(){}

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame() throws POOngException {
        if (gameWidth == 0 && gameHeight == 0) {
            throw new POOngException(POOngException.GAME_DIMENSIONS_NOT_SET);
        }
        createGameObjects();
    }

    public void setDimensions(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    private void createGameObjects() {
        gameObjectManager = new GameObjectManager(gameWidth, gameHeight);
        gameObjectManager.SpawnObjects();
        System.out.println(gameObjectManager.toString());
    }
}

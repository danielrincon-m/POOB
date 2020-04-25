package aplicacion.game;

import aplicacion.POOngGameException;
import aplicacion.game.engine.time.GameTimer;
import aplicacion.game.gameObject.GameObjectManager;

public class GameManager {
    private static GameManager instance;

    private GameObjectManager gameObjectManager;
    private GameTimer gameTimer;

    private float gameWidth;
    private float gameHeight;

    private GameManager(){}

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void initialize(float gameWidth, float gameHeight) {
        intializeParameters(gameWidth, gameHeight);
        createGameObjects();
    }

    public void startGame() throws POOngGameException {
        if (gameWidth == 0 && gameHeight == 0) {
            throw new POOngGameException(POOngGameException.GAME_DIMENSIONS_NOT_SET);
        }
        gameTimer.start();
    }

    public void update() {

    }

    public void endGame() {}

    private void intializeParameters(float gameWidth, float gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        gameTimer = new GameTimer();
    }

    private void createGameObjects() {
        gameObjectManager = new GameObjectManager(gameWidth, gameHeight);
        gameObjectManager.SpawnObjects();
    }
}

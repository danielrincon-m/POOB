package aplicacion;

import aplicacion.exception.ApplicationException;
import aplicacion.game.engine.Input;
import aplicacion.GameManager;
import presentacion.GameScreen;

public class ApplicationManager {

    private ResourceManager resourceManager;
    private GameProperties gameProperties;
    private GameManager gameManager;

    public ApplicationManager() {
        resourceManager = new ResourceManager();
        gameProperties = new GameProperties();
        gameManager = new GameManager(this);
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public GameProperties getGameProperties() {
        return gameProperties;
    }

    public void startGame() {
        if (gameProperties.areValid()) {
            gameManager.startGame();
        } else {
            throw new ApplicationException(ApplicationException.INVALID_PROPERTIES);
        }
    }

    public static void main(String[] args) {
        //GameManager gm = new GameManager();
        //gm.startGame();
        //gm.setVisible(true);
        //gm.addKeyListener(Input.getInstance());
    }
}

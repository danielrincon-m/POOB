package aplicacion;

import aplicacion.exception.ApplicationException;
import presentacion.GameScreen;

public class ApplicationManager {

    private ResourceManager resourceManager;
    private GameProperties gameProperties;
    private GameManager gameManager;

    public ApplicationManager() {
        resourceManager = new ResourceManager(this);
        gameProperties = new GameProperties();
        gameManager = new GameManager(this);
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public GameProperties getGameProperties() {
        return gameProperties;
    }

    public GameManager getGameManager() {return gameManager;}

    public void startGame() {
        if (gameProperties.areValid()) {
            gameManager.startGame();
        } else {
            throw new ApplicationException(ApplicationException.INVALID_PROPERTIES);
        }
    }

    public void endGame() {
        System.out.println("MAL");
        gameManager.endGame();
        gameProperties = new GameProperties();
    }
}

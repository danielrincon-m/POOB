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
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
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
        gameManager.endGame();
        gameProperties = new GameProperties();
    }
}

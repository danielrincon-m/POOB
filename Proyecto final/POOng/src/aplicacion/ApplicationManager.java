package aplicacion;

import aplicacion.exception.ApplicationException;

public class ApplicationManager {

    private final GameManager gameManager;
    private final ResourceManager resourceManager;
    private final GameProperties gameProperties;

    /**
     * Genera un ApplicationManager con todos sus componentes por defecto
     */
    public ApplicationManager() {
        resourceManager = new ResourceManager();
        gameProperties = new GameProperties();
        gameManager = new GameManager(this);
    }

    /**
     * @return El ResourceManager del juego
     */
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    /**
     * @return El objeto GameProperties del juego
     */
    public GameProperties getGameProperties() {
        return gameProperties;
    }

    /**
     * @return El objeto GameManager del juego
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Inicia el juego si todas las propiedades se configuraron correctamente
     */
    public void startGame() {
        if (gameProperties.areValid()) {
            gameManager.startGame();
        } else {
            throw new ApplicationException(ApplicationException.INVALID_PROPERTIES);
        }
    }

    /**
     * Termina la ejecución del juego y reinicia las propiedades
     */
    public void endGame() {
        gameManager.endGame();
        gameProperties.deselectCharacters();
    }
}

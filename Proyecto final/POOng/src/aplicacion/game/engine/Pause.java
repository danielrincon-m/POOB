package aplicacion.game.engine;

import aplicacion.GameManager;
import aplicacion.game.engine.input.Input;
import aplicacion.game.engine.input.InputListener;
import aplicacion.game.enums.GameState;

import java.awt.event.KeyEvent;

/**
 * Clase encargada del sistema de pausa del juego, interactua con el GameManager, para pausar y reanudar el juego
 */
public class Pause implements InputListener {
    GameManager gameManager;

    /**
     * @param gameManager El GameManager del juego
     */
    public Pause(GameManager gameManager) {
        this.gameManager = gameManager;
        Input.getInstance().addInputListener(this);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (gameManager.getGameState() == GameState.PAUSED) {
                gameManager.resumeGame();
            } else if (gameManager.getGameState() == GameState.RUNNING) {
                gameManager.pauseGame();
            }
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }
}

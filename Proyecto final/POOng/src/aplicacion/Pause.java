package aplicacion;

import aplicacion.game.engine.Input;
import aplicacion.game.engine.InputListener;

import java.awt.event.KeyEvent;

public class Pause implements InputListener {
    GameManager gameManager;

    public Pause(GameManager gameManager) {
        this.gameManager = gameManager;
        Input.getInstance().addInputListener(this);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (gameManager.isPaused()) {
                gameManager.resumeGame();
            } else {
                gameManager.pauseGame();
            }
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {

    }
}

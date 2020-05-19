package aplicacion.game.components.pause;

import aplicacion.GameManager;
import aplicacion.game.components.Component;
import aplicacion.game.engine.Input;
import aplicacion.game.engine.InputListener;
import aplicacion.game.entitiy.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pause extends Component implements InputListener {

    GameManager gameManager;

    public Pause(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
        gameManager = applicationManager.getGameManager();
        Input.getInstance().addInputListener(this);
    }

    @Override
    public void update() {

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

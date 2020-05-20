package aplicacion.game.engine;

import java.awt.event.KeyEvent;

public interface InputListener {
    void onKeyPressed(KeyEvent e);
    void onKeyReleased(KeyEvent e);
}

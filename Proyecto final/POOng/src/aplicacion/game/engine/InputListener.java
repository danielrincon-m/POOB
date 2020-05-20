package aplicacion.game.engine;

import java.awt.event.KeyEvent;
import java.io.Serializable;

public interface InputListener extends Serializable {
    void onKeyPressed(KeyEvent e);
    void onKeyReleased(KeyEvent e);
}

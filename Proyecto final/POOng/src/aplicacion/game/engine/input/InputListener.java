package aplicacion.game.engine.input;

import java.awt.event.KeyEvent;

/**
 * Interfaz que se utiliza para registrarse como listener del Input
 */
public interface InputListener {

    /**
     * Función que se llama cuando una tecla fue presionada
     * @param e El evento que registra la tecla
     */
    void onKeyPressed(KeyEvent e);

    /**
     * Función que se llama cuando una tecla fue liberada
     * @param e El evento que registra la tecla
     */
    void onKeyReleased(KeyEvent e);
}

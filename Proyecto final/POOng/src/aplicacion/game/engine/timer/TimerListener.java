package aplicacion.game.engine.timer;

/**
 * Interfaz que debe ser implementada por cada clase que desee ser oyente del timer, y luego de esto,
 * podrá registrarse como oyente en el timer
 */
public interface TimerListener {
    /**
     * Método al cual se llamará cada frame del juego
     */
    void update();
}

package aplicacion.exception;

public class POOngGameException extends Exception {
    public static final String GAME_NOT_INITIALIZED = "No se ha inicializado el juego antes de jugar";

    public POOngGameException(String message) {
        super(message);
    }
}

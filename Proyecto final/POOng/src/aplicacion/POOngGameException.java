package aplicacion;

public class POOngGameException extends Exception {
    public static final String GAME_DIMENSIONS_NOT_SET = "No se han definido las dimensiones del juego";
    public static final String GAME_NOT_INITIALIZED = "No se ha inicializado el juego antes de iniciarlo";

    public POOngGameException(String message) {
        super(message);
    }
}

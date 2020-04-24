package aplicacion;

public class POOngException extends Exception {
    public static final String GAME_DIMENSIONS_NOT_SET = "No se han definido las dimensiones del juego.";

    public POOngException(String message) {
        super(message);
    }
}

package aplicacion.exception;

/**
 * Excepciones generales de la aplicación
 */
public class ApplicationException extends RuntimeException {
    public static final String NOT_A_MACHINE = "Se solicitó una imagen de máquina para un Character que es humano";
    public static final String NOT_A_CHARACTER = "Se solicitó una imagen de jugador para un Character que es máquina";
    public static final String INVALID_PROPERTIES = "Las propiedades del juego no son válidas, o no están completas";

    public ApplicationException(String message) {
        super(message);
    }
}

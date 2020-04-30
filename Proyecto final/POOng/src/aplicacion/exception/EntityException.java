package aplicacion.exception;

public class EntityException extends RuntimeException {
    //Excepciones generales
    public static final String DUPLICATED_NAME = "Un objeto con el nombre dado ya ha sido definido";
    public static final String GAMEOBJECT_NOT_FOUND = "No existe un GameObject con ese nombre";

    //Excepciones de ball
    public static final String INVALID_DIRECTION = "La dirección dada no es válida";

    //Excepciones de Player
    public static final String INVALID_NAME = "El nombre del jugador no tiene sentido :(";

    public EntityException(String message) {
        super(message);
    }
}


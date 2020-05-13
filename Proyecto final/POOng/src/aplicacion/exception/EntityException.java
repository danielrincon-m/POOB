package aplicacion.exception;

public class EntityException extends RuntimeException {
    //Excepciones generales
    public static final String DUPLICATED_NAME = "Un objeto con el nombre dado ya ha sido definido";
    public static final String ENTITY_NOT_FOUND = "No existe una entidad con ese nombre";
    public static final String COMPONENT_ALREADY_ADDED = "El componente ya fué agregado a la entidad";
    public static final String COMPONENT_NOT_FOUND = "El componente no existe en la entidad";
    public static final String CANNOT_ADD_COMPONENT = "El componente no puede ser agregado luego de registrar la entidad";

    //Excepciones de ball
    //public static final String INVALID_FIELD_SIDE = "La dirección del jugador dada no es válida";

    //Excepciones de Player
    public static final String INVALID_NAME = "El nombre del jugador no tiene sentido :(";
    public static final String INVALID_DIRECTION = "La dirección de movimiento del jugador es inválida";

    public EntityException(String message) {
        super(message);
    }
}


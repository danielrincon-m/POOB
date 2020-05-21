package aplicacion.exception;

/**
 * Excepciones que pueden ocurrir en las entidades
 */
public class EntityException extends RuntimeException {
    //Excepciones generales
    public static final String DUPLICATED_NAME = "Un objeto con el nombre dado ya ha sido definido";
    public static final String ENTITY_NOT_FOUND = "No existe una entidad con ese nombre";
    public static final String COMPONENT_ALREADY_ADDED = "El componente ya fué agregado a la entidad";
    public static final String COMPONENT_NOT_FOUND = "El componente no existe en la entidad";

    //Excepciones de Player
    public static final String INVALID_NAME = "El nombre del jugador no tiene sentido :(";

    public EntityException(String message) {
        super(message);
    }
}


package aplicacion.exception;

public class EntityException extends RuntimeException{
    public static final String DUPLICATED_NAME = "Un objeto con el nombre dado ya ha sido definido";
    public static final String GAMEOBJECT_NOT_FOUND = "No existe un GameObject con ese nombre";

    public EntityException(String message) {
        super(message);
    }
}


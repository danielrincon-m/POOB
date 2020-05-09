package aplicacion.exception;

public class ApplicationException extends RuntimeException {
    public static final String PROBLEM_LOADING_RESOURCE = "Ha ocurrido un problema al cargar el recurso";
    public static final String NOT_A_MACHINE = "Se solicitó una imagen de máquina para un Character que es humano";
    public static final String INVALID_PROPERTIES = "Las propiedades del jeugo no son válidas, o no están completas";

    public ApplicationException(String message) {
        super(message);
    }
}

package aplicacion;

public class SINAPExcepecion extends Exception{
    public static final String SIN_NOMBRE_INTERNACIONAL = "El area no tiene nombre internacional";

    public SINAPExcepecion(String message){
        super(message);
    }
}

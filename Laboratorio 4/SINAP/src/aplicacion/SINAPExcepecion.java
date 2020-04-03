package aplicacion;

public class SINAPExcepecion extends Exception{
    public static final String SIN_NOMBRE_INTERNACIONAL = "El area no tiene nombre internacional";
    public static final String AREA_DUPLICADA = "El area ya se encuentra registrada";
    public static final String AREA_NO_NUMERICA = "El area no es un valor numérico";
    public static final String DESCRIPCION_MUY_CORTA = "La descripción debe ser de al menos 20 caracteres";

    public SINAPExcepecion(String message){
        super(message);
    }
}

package aplicacion;

public class AutomataException extends Exception {
    public final static  String ABRIR_EN_CONSTRUCCION = "Opción abrir en construcción";
    public final static  String ARCHIVO_CORRUPTO ="El archivo abierto se ecnuentra corrupto.";
    public final static  String CLASE_NO_ENCONTRADA ="La clase que se desea construir no fué encontrada.";
    public final static  String DATOS_PRIMITIVOS ="Se encontraron datos primitivos en lugar de objetos.";
    public final static  String ERROR_AL_ABRIR ="Ha ocurrido un error al abrir el objeto.";

    public final static  String GUARDAR_EN_CONSTRUCCION ="Opción guardar en construcción";
    public final static  String ARCHIVO_NO_ENCONTRADO ="No se ha encontrado el archivo.";
    public final static  String ERROR_ENTRADA_SALIDA ="Ha ocurrido un error de entrada/salida.";
    public final static  String CLASE_INVALIDA ="La clase contiene errores.";
    public final static  String CLASE_NO_SERIALIZABLE ="La clase a guardar o una clase referenciada no está marcada como serializable.";
    public final static  String ERROR_AL_GUARDAR ="Ha ocurrido un error al guardar el objeto.";

    public final static  String EXPORTE_EN_CONSTRUCCION = "Opción exporte en construcción";
    public final static  String ERROR_AL_EXPORTAR ="Ha ocurrido un error al exportar el objeto.";

    public final static  String IMPORTAR_EN_CONSTRUCCION = "Opción importar en construcción";
    public final static  String ACCESO_ILEGAL = "No se tiene acceso al constructor de la clase.";
    public final static  String ERROR_DE_INSTANCIACION = "No se ha podido crear la instancia de la clase.";
    public final static  String NO_EXISTE_EL_METODO = "No se pudo encontrar el constructor especificado.";
    public final static  String ERROR_DE_INVOCACION = "Error de invocación.";
    public final static  String ERROR_AL_IMPORTAR ="Ha ocurrido un error al importar el objeto.";
    public final static  String ERROR_AL_IMPORTAR_CON_DETALLES ="Ha ocurrido un error al importar el objeto, puede encontrar " +
            "mas detalles en el archivo 'automataErr.txt'";

    public final static  String REINICIAR_EN_CONSTRUCCION = "Opción reiniciar en construcción";
    public  AutomataException (String msg){super(msg);}

}

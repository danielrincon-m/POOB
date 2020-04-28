package aplicacion;

public class AutomataException extends Exception {
    public final static  String ABRIR_EN_CONSTRUCCION = "Opción abrir en construcción";
    public final static  String GUARDAR_EN_CONSTRUCCION ="Opción guardar en construcción";
    public final static  String EXPORTE_EN_CONSTRUCCION = "Opción exporte en construcción";
    public final static  String IMPORTAR_EN_CONSTRUCCION = "Opción importar en construcción";
    public final static  String REINICIAR_EN_CONSTRUCCION = "Opción reiniciar en construcción";
    public  AutomataException (String msg){super(msg);}

}

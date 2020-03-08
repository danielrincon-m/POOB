package aplicacion;
import java.awt.Color;

/**
 * Write a description of class Izquierdosa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Izquierdosa extends Celula
{
    

    /**
     * Constructor for objects of class Izquierdosa
     */
    public Izquierdosa(AutomataCelular ac,int fila, int columna, String nombre)
    {
        super(ac,fila,columna,nombre);
        color=Color.red;
    }

    @Override
    public void decida(){
        Elemento elemento=automata.getElemento(fila, columna+1);
        if(elemento!=null&& elemento.isVivo()){
            estadoSiguiente=MUERTA;
        } 

    }

}


package aplicacion;

import java.awt.*;

/**
 * Write a description of class Izquierdosa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Izquierdosa extends Celula
{

    /**
     * Izquierdosa Constructor
     *
     * @param ac El automata celular al que pertenece este elemento
     * @param fila La fila en la que se encuentra ubicado el elemento
     * @param columna La columna en la que se encuentra ubicado el elemento
     * @param nombre El nombre del elemento
     */
    public Izquierdosa(AutomataCelular ac,int fila, int columna, String nombre)
    {
        super(ac,fila,columna,nombre);
        color=Color.red;
    }

    /**
     * Método que decide que hará la célula en el siguiente periodo de tiempo
     */
    @Override
    public void decida(){
        Elemento elemento=automata.getElemento(fila, columna+1);
        if(elemento!=null&& elemento.isVivo()){
            estadoSiguiente=MUERTA;
        } 

    }

}


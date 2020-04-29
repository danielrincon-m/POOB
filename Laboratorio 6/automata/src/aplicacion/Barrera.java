package aplicacion;

import java.awt.*;

/**
 * Write a description of class Barrera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Barrera implements Elemento
{
    protected AutomataCelular automata;
    protected Color color;
    protected int fila, columna;
    protected String nombre;
    
    /**
     * Barrera Constructor
     *
     * @param ac El automata celular al que pertenece este elemento
     * @param fila La fila en la que se encuentra ubicado el elemento
     * @param columna La columna en la que se encuentra ubicado el elemento
     * @param nombre El nombre del elemento
     */
    public Barrera(AutomataCelular ac,int fila, int columna, String nombre){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        this.nombre=nombre;
        color=Color.black;
        automata.setElemento(fila,columna,(Elemento)this);
    }
    
    /**
     * Método para obtener la forma del elemento
     *
     * @return La representación en entero de la forma del elemento
     */
    public final int getForma(){
        return CUADRADA;
    }
    
    /**
     * Método para obtener el color del elemento
     *
     * @return El color del elemento
     */
    public final Color getColor(){
        return color;
    }
    
    /**
     * Retorna la fila del automata en que se encuentra 
     * @return la fila del elemento
     */

    public final int getFila(){
        return fila;
    }

    /**
     * Retorna la columna del automata en que se encuentra
     * @return la columna del elemento
     */
    public final int getColumma(){
        return columna;
    }
    
    /**
     * Retorna si está viva
     * @return Si está viva
     */
    public final boolean isVivo(){
        return false;
    }
}

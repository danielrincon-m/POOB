package aplicacion;
import java.awt.Color;

/**
 * Write a description of class Barrera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Barrera implements Elemento
{
    private AutomataCelular automata;
    private int fila,columna;
    private Color color;
    private String nombre;
    /**
     * Constructor for objects of class Barrera
     */
    public Barrera(AutomataCelular ac,int fila, int columna, String nombre){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        this.nombre=nombre;
        color=Color.black;
        automata.setElemento(fila,columna,(Elemento)this);
    }
    
    public final int getForma(){
        return CUADRADA;
    }
    
    public final Color getColor(){
        return color;
    }
    
    /**Retorna la fila del automata en que se encuentra 
    @return 
     */

    public final int getFila(){
        return fila;
    }

    /**Retorna la columna del automata en que se encuentra
    @return 
     */
    public final int getColumma(){
        return columna;
    }
    
    /**Retorna si está viva
    @return v
     */
    public final boolean isVivo(){
        return false;
    }
}

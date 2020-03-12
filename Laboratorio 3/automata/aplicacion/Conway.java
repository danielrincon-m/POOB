package aplicacion;

import java.awt.Color;

/**
 * Write a description of class Conway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Conway extends Celula
{
    public Conway(AutomataCelular ac,int fila, int columna, String nombre)
    {
        super(ac, fila, columna, nombre);
        color = Color.blue;
    }
}

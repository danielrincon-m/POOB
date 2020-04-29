package aplicacion;

import java.awt.*;

/**Informacion sobre una célula<br>
<b>(COLOR,automata,fila,columna,estadoActual,estadoSigiente)</b><br>
Las celulas conocen su color, el automata en la que viven, la posición en la que están en ese autómata,su estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
Los posibles estados de una célula son tres: viva, muerta o latente<br>
 */
public class Celula implements Elemento{

    protected final static char VIVA='v', MUERTA='m';
    protected AutomataCelular automata;
    protected int fila,columna;
    protected char estadoActual,estadoSiguiente;
    protected Color color;
    private int edad;
    private String nombre;
    
    /**Crea una célula, viva o latente, en la posición (<b>fila,columna</b>) del autómta <b>ac</b>.Toda nueva célula va a estar viva en el estado siguiente.
     * @param ac automata celular en el que se va a ubicar la nueva célula
     * @param fila fila en el automata celular
     * @param columna columna en el automata celula
     */
    public Celula(AutomataCelular ac,int fila, int columna, String nombre){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        this.nombre=nombre;
        estadoActual=' ';
        estadoSiguiente=VIVA;
        edad=0;
        automata.setElemento(fila,columna,(Elemento)this);  
        color=Color.black;
    }
    
    /**
     * Método para obtener la forma del elemento
     *
     * @return Un entero que representa la forma del elemento
     */
    public final int getForma(){
        return REDONDA;
    }
    
    /**
     * Retorna la fila del automata en que se encuentra 
     * @return fila del elemento
     */

    public final int getFila(){
        return fila;
    }

    /**
     * Retorna la columna del automata en que se encuentra
     * @return Columna del elemento
     */
    public final int getColumma(){
        return columna;
    }

    /**
     * Retorna el color de  la célula
     * @return El color de la célula
     */
    public final Color getColor(){
        return color;
    }

    /**
     * Retorna si está viva
     * @return Si la célula está viva
     */
    public final boolean isVivo(){
        return (estadoActual == VIVA) ;
    }

    /**
     * Retorna la edad de la célula
     * @return La edad de la célula
     */
    public final int edad(){
        return (edad) ;
    }

    /**
     * Decide cual va a ser su  siguiente estado 
     */
    public void decida(){
        if (edad>=2){
            estadoSiguiente=MUERTA;
        }   
    }

    /**
     * Actualiza su estado actual considerando lo definido como siguiente estado
     */
    public final void cambie(){
        edad++;
        estadoActual=estadoSiguiente;
    }

}

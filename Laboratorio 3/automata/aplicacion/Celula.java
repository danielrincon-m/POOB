package aplicacion;

import java.awt.Color;

/**Informacion sobre una célula<br>
<b>(COLOR,automata,fila,columna,estadoActual,estadoSigiente)</b><br>
Las celulas conocen su color, el automata en la que viven, la posición en la que están en ese autómata,su estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
Los posibles estados de una célula son tres: viva, muerta o latente<br>
 */
public class Celula implements Elemento{

    private final static char VIVA='v', MUERTA='m';
    private AutomataCelular automata;
    private int fila,columna;
    protected char estadoActual,estadoSiguiente;
    protected Color color;
    private int edad;

    /**Crea una célula, viva o latente, en la posición (<b>fila,columna</b>) del autómta <b>ac</b>.Toda nueva célula va a estar viva en el estado siguiente.
    @param ac automata celular en el que se va a ubicar la nueva célula
    @param fila fila en el automata celular
    @param columna columna en el automata celula
     */
    public Celula(AutomataCelular ac,int fila, int columna){
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        estadoActual=' ';
        estadoSiguiente=VIVA;
        edad=0;
        automata.setElemento(fila,columna,(Elemento)this);	
        color=Color.black;
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

    
    /**Retorna el color de  la célula
    @return 
     */
    public final Color getColor(){
        return color;
    }

    /**Retorna si está viva
    @return v
     */
    public final boolean isVivo(){
        return (estadoActual == VIVA) ;
    }

    /**Retorna la edad de la célula
    @return 
     */
    public final int edad(){
        return (edad) ;
    }

    /**Decide cual va a ser su  siguiente estado 
     */
    public void decida(){
        if (edad>=2){
            estadoSiguiente=MUERTA;
        }   
    }

    /**Actualiza su estado actual considerando lo definido como siguiente estado
     */
    public final void cambie(){
        edad++;
        estadoActual=estadoSiguiente;
    }

}

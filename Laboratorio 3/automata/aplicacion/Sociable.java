package aplicacion;
import java.awt.Color;

/**
 * Write a description of class Toxica here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sociable extends Celula
{
    /**
     * Constructor for objects of class Izquierdosa
     */
    public Sociable(AutomataCelular ac,int fila, int columna, String nombre)
    {
        super(ac,fila,columna,nombre);
        color=Color.green;
    }

    @Override
    public void decida(){
        int celulasVivas=0;

        for(int i=-1; i <=1;i++){
            for(int j=-1; j<=1;j++){
                Elemento elemento=automata.getElemento(fila+i, columna+j);
                if(i!=0 || j!=0){
                    if(elemento!=null && elemento.isVivo()){
                        celulasVivas++;
                    }
                    
                }

            }
        }
        if(celulasVivas>=3){
            estadoSiguiente=VIVA;
        }
        else{
            estadoSiguiente=MUERTA;
        }
    }
}
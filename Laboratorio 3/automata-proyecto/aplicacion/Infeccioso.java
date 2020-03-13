package aplicacion;
import java.awt.Color;

/**
 * Write a description of class Infeccioso here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Infeccioso extends Barrera
{
    /**
     * Infeccioso Constructor
     *
     * @param ac El automata celular al que pertenece este elemento
     * @param fila La fila en la que se encuentra ubicado el elemento
     * @param columna La columna en la que se encuentra ubicado el elemento
     * @param nombre El nombre del elemento
     */
    public Infeccioso(AutomataCelular ac,int fila, int columna, String nombre)
    {
        super(ac, fila, columna, nombre);
        color = Color.yellow;
    }

    /**
     * Método para tomar la decisión de que hace la célula en el siguiente periodo del tiempo
     */
    @Override
    public void decida(){
        for(int i = -1; i <= 1; i++){
            for(int j =- 1; j <= 1; j++){
                Elemento elemento = automata.getElemento(fila + i, columna + j);
                if(i != 0 || j != 0){
                    if(elemento != null){
                        Elemento nuevoElemento = new Infeccioso(automata, fila, columna, "copia infecciosa");
                        automata.setElemento(fila + i, columna + j, nuevoElemento);
                    }
                }
            }
        }
    }
}

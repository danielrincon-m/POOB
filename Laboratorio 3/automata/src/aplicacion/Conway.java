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
    /**
     * Conway Constructor
     *
     * @param ac El automata celular al que pertenece este elemento
     * @param fila La fila en la que se encuentra ubicado el elemento
     * @param columna La columna en la que se encuentra ubicado el elemento
     * @param nombre El nombre del elemento
     */
    public Conway(AutomataCelular ac,int fila, int columna, String nombre)
    {
        super(ac, fila, columna, nombre);
        color = Color.blue;
    }

    /**
     * Método Que decide que va a hacer la célula en el siguiente periodo de tiempo
     */
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
        if(!isVivo()){
            if(celulasVivas == 3){
                estadoSiguiente=VIVA;
            }
        }else{
            if(celulasVivas == 1 || celulasVivas > 3){
                estadoSiguiente = MUERTA;
            }
        }
    }
}
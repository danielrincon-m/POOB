package checkers;

import javax.swing.JOptionPane;
import java.util.HashMap;

/**
 * Clase que extiende a Board y representa el tablero de configuración
 *
 * @author Paula Guevara & Daniel Rincón
 * @version 06-mar-2020
 */
public class ConfigurationBoard extends Board
{
    private HashMap<String, String> memoria;

    /**
     * ConfigurationBoard Constructor
     *
     * @param width La cantidad de celdas en cada dirección
     * @param squareSize El tamaño de cada celda en pixeles
     * @param xPosition La posición de la esquina superior izquierda en el eje x
     * @param yPosition La posición de la esquina superiuor izquierda en el eje y
     * @param lightColor Color claro del tablero R,G,B
     * @param darkColor Color oscuro del tablero R,G,B
     */
    public ConfigurationBoard(int width, int squareSize, int xPosition, int yPosition, String lightColor, String darkColor, Checkers checkers)
    {
        super(width, squareSize, xPosition, yPosition, lightColor, darkColor, checkers);
        memoria = new HashMap<String,String>();
    }

    /**
     * Guarda el tablero actual con el nombre dado
     * 
     * @param name El nombre que se le desea dar al tablero
     */
    public void save(String name){
        memoria.put(name, write());
    }

    /**     
     * Carga y retorna en forma de string el tablero guardado con name, o null si el nombre no existe
     * 
     * @param name El nombre del tablero que se desea recuperar
     * 
     * @return El tablero cargado en forma de String
     */
    public String recover(String name){
        if (memoria.containsKey(name)){
            String board = memoria.get(name);
            read(board);
            return board;
        }else{
            JOptionPane.showMessageDialog(null, "No existe ese tablero en memoria");
            return null;
        }
    }
}

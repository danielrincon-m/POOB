import javax.swing.JOptionPane;
import java.util.HashMap;

/**
 * Write a description of class ConfigurationBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ConfigurationBoard extends Board
{
    private HashMap<String, String> memoria;

    /**
     * Constructor for objects of class ConfigurationBoard
     */
    public ConfigurationBoard(int width, int squareSize, int xPosition, int yPosition, String lightColor, String darkColor)
    {
        super(width, squareSize, xPosition, yPosition, lightColor, darkColor);
        memoria = new HashMap<String,String>();
    }

    /**
     * Remover una ficha del tablero si es posible
     * 
     * @param row Fila en la que se encuentra la ficha
     * @param column Columna en la que se encuentra la ficha
     */
    public void remove(int row, int column){
        Piece piece = findPiece(row, column);
        if (piece != null){
            removePiece(piece);
        }else{
            JOptionPane.showMessageDialog(null, "No hay ninguna pieza en esa posición");
        }
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

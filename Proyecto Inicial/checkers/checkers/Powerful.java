package checkers;
import javax.swing.JOptionPane;

/**
 * Clase 
 *
 * @author author Paula Guevara & Daniel Rincón
 * @version 21-02-10
 */
public class Powerful extends Piece
{
    /**
     * Powerful Constructor
     *
     * @param isKing Si la pieza es un rey
     * @param isWhite Si la pieza es de color blanco
     * @param visible Si la pieza se inicializa visible
     * @param xPosition Coordenada en el eje x
     * @param yPosition Coordenada en el eje y
     * @param row Fila a la que pertenece en el tablero
     * @param column Columna a la que pertenece en el tablero
     * @param size Tamaño de la pieza
     */
    public Powerful (Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size){
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }

    /**
     * Calcula el tipo de la pieza
     */
    @Override
    protected void calculateInitialType(){
        initials = "W";
        type = "powerful";
        calculateInitialColorAndKing();
    }

    /**
     * Método para capturar la pieza
     */
    @Override
    public void capture(){
        JOptionPane.showMessageDialog(null, "No puedo ser capturada");
    }
}

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
    public Powerful (Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size){
    super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }
    @Override
    protected void calculateInitialType(){
        initials = "W";
        type = "powerful";
        calculateInitialColorAndKing();
    }
    @Override
    public void capture(){
        JOptionPane.showMessageDialog(null, "No puedo ser capturada");
    }
}

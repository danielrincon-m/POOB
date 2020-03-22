package checkers;
import javax.swing.JOptionPane;

/**
 * Write a description of class Powerful here.
 *
 * @author (your name)
 * @version (a version number or a date)
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

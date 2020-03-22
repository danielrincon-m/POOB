package checkers;

/**
 * Write a description of class RebelDani here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rebel  extends Piece
{   
    int moveCount=0;

    public Rebel(Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size){
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }

    @Override
    protected void calculateInitialType(){
        initials = "R";
        type = "rebel";
        calculateInitialColorAndKing();
    }

    @Override
    public void shift(boolean top, boolean right, int newRow, int newColumn, Piece blockingPiece, int[] coordinates){

        if(moveCount==0){
            moveCount++;
            ((GameBoard)board).shift(!top, !right);
        }
        else{
            super.shift(top, right, newRow, newColumn, blockingPiece, coordinates);
            moveCount=moveCount%1;
        }
    } 
}

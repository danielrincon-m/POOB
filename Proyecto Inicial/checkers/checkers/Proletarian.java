package checkers;


/**
 * Write a description of class Proletarian here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Proletarian extends Piece
{
    public Proletarian(Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size)
    {
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }
    
    @Override
    public void setKing(boolean isKing){
        if (!this.isKing && isKing){
            remove();
        }else{
            super.setKing(isKing);
        }
    }
    
    @Override
    protected void calculateInitialType(){
        initials = "P";
        type = "proletarian";
        calculateInitialColorAndKing();
    }
}

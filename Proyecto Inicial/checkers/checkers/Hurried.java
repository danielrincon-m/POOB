package checkers;

/**
 * Write a description of class Hurried here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hurried extends Piece
{
    private int moveCount=0;

    public Hurried(Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size){
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }

    @Override
    protected void calculateInitialType(){
        initials = "H";
        type = "hurried";
        calculateInitialColorAndKing();
    }

    @Override
    public void shift(boolean top, boolean right, int newRow, int newColumn, Piece blockingPiece, int[] coordinates){
        super.shift(top, right, newRow, newColumn, blockingPiece, coordinates);
        if(moveCount==0){
            moveCount++;
            ((GameBoard)board).shift(top, right);
        }

        moveCount=moveCount%1;
    }

    @Override
    public void jump(boolean top, boolean right, int newRow, int newColumn, Piece enemyPiece, Piece blockingPiece, int[] coordinates){
        super.jump(top, right, newRow, newColumn, enemyPiece, blockingPiece, coordinates);
        if(moveCount==0){
            moveCount++;
            ((GameBoard)board).jump(top, right);
        }

        moveCount=moveCount%1;
    }
}

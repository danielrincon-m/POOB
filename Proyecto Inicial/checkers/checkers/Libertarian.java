package checkers;

/**
 * Write a description of class Libertarian here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Libertarian extends Piece
{
    public Libertarian(Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size)
    {
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }

    @Override
    protected void calculateInitialType(){
        initials = "L";
        type = "libertarian";
        calculateInitialColorAndKing();
    }

    @Override
    public void jump(boolean top, boolean right, int newRow, int newColumn, Piece enemyPiece, Piece blockingPiece, int[] coordinates){
        boolean enemyExisted = enemyPiece != null;
        boolean isDead;
        boolean isKing = false;
        boolean isWhite = false;
        int enemyRow = -1;
        int enemyColumn = -1;
        String type = "";
        if (enemyExisted){
            enemyRow = enemyPiece.getRow();
            enemyColumn = enemyPiece.getColumn();
            isKing = enemyPiece.isKing();
            isWhite = enemyPiece.isWhite();
            type = enemyPiece.getType();
        }
        super.jump(top, right, newRow, newColumn, enemyPiece, blockingPiece, coordinates);
        isDead = board.findPiece(enemyRow, enemyColumn) == null;
        System.out.println("Es libertarian");
        if(enemyExisted && isDead){
            board.add(isKing, isWhite, enemyRow, enemyColumn, type);
            System.out.println("Agregando...");
        }
    }
}

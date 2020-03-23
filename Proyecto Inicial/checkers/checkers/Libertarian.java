package checkers;

/**
 * Write a description of class Libertarian here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Libertarian extends Piece
{
    /**
     * Libertarian Constructor
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
    public Libertarian(Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size)
    {
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }

    /**
     * Calcula el tipo de la pieza
     */
    @Override
    protected void calculateInitialType(){
        initials = "L";
        type = "libertarian";
        calculateInitialColorAndKing();
    }

    /**
     * Método para hacer que la pieza salte en cierta dirección si es posible
     *
     * @param top Si se mueve hacia arriba
     * @param right Si se mueve hacia la derecha
     * @param newRow La nueva fila 
     * @param newColumn La nueva columna
     * @param enemyPiece La pieza que se desea saltar
     * @param blockingPiece La posible pieza que está bloqueando esta
     * @param coordinates Las nuevas coordenadas de la pieza
     */
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
        if(enemyExisted && isDead){
            board.add(isKing, isWhite, enemyRow, enemyColumn, type);
        }
    }
    /**
     * Función que define los colores que posee la ficha
     */
    @Override
    public void setColors(){
        if (isWhite){
            circleColor = "250, 184, 113";
        }else{
            circleColor = "135, 88, 38";
        }
        crownColor = "red";
    }
}

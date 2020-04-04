package checkers;


/**
 * Write a description of class Lazy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lazy extends Piece
{
    private int sequenceNumber;
    
    /**
     * Lazy Constructor
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
    public Lazy (Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size)            
    {
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
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
        if(sequenceNumber != board.getCheckers().getMoveSequenceNumber()) {
            sequenceNumber = board.getCheckers().getMoveSequenceNumber();
            super.jump(top, right, newRow, newColumn, enemyPiece, blockingPiece, coordinates);
        }
    }
    
    /**
     * Calcula el tipo de la pieza
     */
    @Override
    protected void calculateInitialType(){
        initials = "Z";
        type = "lazy";
        calculateInitialColorAndKing();
    }
    
    /**
     * Función que define los colores que posee la ficha
     */
    @Override
    public void setColors(){
        if (isWhite){
            circleColor = "255, 236, 141";
        }else{
            circleColor = "210, 192, 102";
        }
        crownColor = "red";
    }
}

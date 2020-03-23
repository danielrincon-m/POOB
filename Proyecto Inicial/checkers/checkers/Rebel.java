package checkers;

/**
 * Write a description of class RebelDani here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rebel  extends Piece
{   
    private int moveCount=0;

    /**
     * Rebel Constructor
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
    public Rebel(Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size){
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }

    /**
     * Calcula el tipo de la pieza
     */
    @Override
    protected void calculateInitialType(){
        initials = "R";
        type = "rebel";
        calculateInitialColorAndKing();
    }

    /**
     * Método que mueve la pieza en cierta dirección un espacio
     *
     * @param top Si se mueve hacia arriba
     * @param right Si se mueve hacia la derecha
     * @param newRow Cual es la nueva fila
     * @param newColumn La nueva columna
     * @param blockingPiece La pieza que puede estar bloqueandola
     * @param coordinates La nuevas coordenadas de la pieza
     */
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

    /**
     * Función que define los colores que posee la ficha
     */
    @Override
    public void setColors(){
        if (isWhite){
            circleColor = "216, 255, 0";
        }else{
            circleColor = "118, 137, 10";
        }
        crownColor = "red";
    }
}

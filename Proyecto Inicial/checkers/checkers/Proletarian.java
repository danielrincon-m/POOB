package checkers;

/**
 * Write a description of class Proletarian here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Proletarian extends Piece
{
    /**
     * Proletarian Constructor
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
    public Proletarian  (Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size)            
    {
        super(board, isKing, isWhite, visible, xPosition, yPosition, row, column, size);
    }

    /**
     * Cambiar si la píeza es rey o no
     * 
     * @param isKing si la pieza es rey
     */
    @Override
    public void setKing(boolean isKing){
        if (!this.isKing && isKing){
            remove();
        }else{
            super.setKing(isKing);
        }
    }

    /**
     * Calcula el tipo de la pieza
     */
    @Override
    protected void calculateInitialType(){
        initials = "P";
        type = "proletarian";
        calculateInitialColorAndKing();
    }
}

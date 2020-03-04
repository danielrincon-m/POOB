import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Board
{
    protected boolean visible = false;
    protected final int squareSize;
    protected int width;
    protected ArrayList<Piece> pieces;

    private Rectangle [][] board;

    /**
     * Constructor for objects of class Board
     */
    public Board(int width, int squareSize, int xPosition, int yPosition, String lightColor, String darkColor)
    {
        this.squareSize = squareSize;
        this.width = width;
        pieces = new ArrayList<Piece>();
        createBoard(width, xPosition, yPosition, lightColor, darkColor);
    }

    public void makeVisible(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                board[i][j].makeVisible();
            }
        }
        for (Piece piece : pieces){
            piece.makeVisible();
        }
        visible = true;
    }

    public void makeInvisible(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                board[i][j].makeInvisible();
            }
        }
        for (Piece piece : pieces){
            piece.makeInvisible();
        }
        visible = false;
    }

    /**
     * Agrega una pieza al tablero
     * 
     * @param king Si la ficha es un rey
     * @param white Si la ficha es blanca
     * @param row Fila en la que se desea colocar la ficha
     * @param column Columna en la que se desea colocar la ficha
     */
    public void add(boolean king, boolean white, int row, int column){
        //Encontrar las coordenadas de la fila y la columna
        int[] coords = positionToCoordinates(row, column);
        if (coords != null && isBlackSquare(row, column)){

            //Verificar que no haya una ficha en esa posición
            Piece piece = findPiece(row, column);

            if (piece == null){
                pieces.add(new Piece(king, white, visible, coords[0], coords[1], row, column, squareSize));
            }else{
                JOptionPane.showMessageDialog(null, "Ya hay una pieza en ese espacio");
            }
        }
    }

    public void read(String checkerboard){
        int index = 0;
        //limpia el tablero
        clear();
        //Coloca las nuevas fichas
        for(int i = 1; i < width+1; i++){
            for (int j = 1; j < width+1; j++){
                if((checkerboard.charAt(index)!='.')||(checkerboard.charAt(index)!='-')){
                    // asigna la fichas al tablero de configuración dependiendo del caracter que tenga
                    if(checkerboard.charAt(index)=='B'){
                        add(true, false, i, j);
                    }else if(checkerboard.charAt(index)=='W'){
                        add(true, true, i, j);
                    }else if(checkerboard.charAt(index)=='w'){
                        add(false, true, i, j);
                    }else if(checkerboard.charAt(index)=='b'){
                        add(false, false,i, j);
                    }
                }

                index ++;
            }           
        }
    }

    public String write(){
        String cadena="";
        Piece piece;
        // crea matriz de caracteres con un tamaño de width de . y -
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                piece = findPiece(i + 1, j + 1);
                if (piece != null){
                    if (!piece.isWhite() && piece.isKing()){
                        cadena += "B";
                    }else if(piece.isWhite() && piece.isKing()){
                        cadena += "W";
                    }else if(!piece.isWhite()){
                        cadena += "b";
                    }else{
                        cadena += "w";
                    }
                }else{
                    if((i + j) % 2 == 0){
                        cadena += "-";
                    }else{
                        cadena += ".";
                    }
                }
            }
        }
        return cadena;
    }

    public int[] positionToCoordinates(int row, int column){
        if (row < 1 || row > width || column < 1 || column > width){
            JOptionPane.showMessageDialog(null, "La posición no existe en el tablero");
            return null;
        }

        int [] coords = new int[2];
        Rectangle rectangle;

        rectangle  = board[row - 1][column - 1];
        coords[0] = rectangle.getXPosition();
        coords[1] = rectangle.getYPosition();

        return coords;
    }

    public void clear(){
        for(int i = pieces.size() - 1; i >= 0; i--){
            removePiece(pieces.get(i));
        }
    }

    protected boolean isBlackSquare(int row, int column){
        boolean isBlack = false;
        if (row >= 1 && row <= width && column >= 1 && column <= width){
            if ((row + column) % 2 == 1){
                isBlack = true;
            }else{
                JOptionPane.showMessageDialog(null, "El cuadrado no es negro");
            }
        }
        return isBlack;
    }

    /**
     * Función que encuentra si una pieza existe en una posición en específico
     * 
     * @param row La fila en la que se desea buscar
     * @param column La columna en la que se desea buscar
     * 
     * @return La pieza si la encontró o null si no fue así
     */
    protected Piece findPiece(int row, int column){
        Piece foundPiece = null;

        for (int i = 0; i < pieces.size() && foundPiece == null; i++){
            int pieceRow = pieces.get(i).getRow();
            int pieceColumn = pieces.get(i).getColumn();
            if (pieceRow == row && pieceColumn == column){
                foundPiece = pieces.get(i);
            }
        }

        return foundPiece;
    }

    /**
     * Función que vuelve una pieza invisible y la elimina del arrayList de piezas
     * 
     * @param piece La pieza a eliminar
     */
    protected void removePiece(Piece piece){
        piece.remove();
        pieces.remove(piece);
    }

    private void createBoard(int width, int xPosition, int yPosition, String lightColor, String darkColor){
        int color = 0;
        board = new Rectangle[width][width];
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                board[j][i] = new Rectangle((xPosition + (squareSize + 1) * i), yPosition + ((squareSize + 1) * j), squareSize);
                if (color == 0){
                    board[j][i].changeColor(lightColor);
                    color = 1;
                }
                else{
                    board[j][i].changeColor(darkColor);
                    color = 0;
                }
            }
            color = (color + 1) % 2;
        }
    }
}

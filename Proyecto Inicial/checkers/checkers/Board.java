package checkers;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import shapes.*;

/**
 * Clase abstracta para poner los métodos compartidos por los dos tipos de tablero.
 *
 * @author Paula Guevara & Daniel Rincón
 * @version 06-mar-2020
 */
public abstract class Board
{
    protected boolean visible = false;
    protected final int squareSize;
    protected int width;
    protected ArrayList<Piece> pieces;

    private Rectangle [][] board;

    /**
     * Board Constructor
     *
     * @param width La cantidad de celdas en cada dirección
     * @param squareSize El tamaño de cada celda en pixeles
     * @param xPosition La posición de la esquina superior izquierda en el eje x
     * @param yPosition La posición de la esquina superiuor izquierda en el eje y
     * @param lightColor Color claro del tablero R,G,B
     * @param darkColor Color oscuro del tablero R,G,B
     */
    public Board(int width, int squareSize, int xPosition, int yPosition, String lightColor, String darkColor)
    {
        this.squareSize = squareSize;
        this.width = width;
        pieces = new ArrayList<Piece>();
        createBoard(width, xPosition, yPosition, lightColor, darkColor);
    }

    /**
     * Método que hace visible el tablero y todos sus componentes
     */
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

    /**
     * Método que hace invisible el tablero y todos sus componentes
     */
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
    public void add(boolean king, boolean white, int row, int column, String type){
        //Encontrar las coordenadas de la fila y la columna
        int[] coords = positionToCoordinates(row, column);
        if (coords != null && isBlackSquare(row, column)){

            //Verificar que no haya una ficha en esa posición
            Piece piece = findPiece(row, column);

            if (piece == null){
                addType(king, white, coords, row, column, type);
            }else{
                JOptionPane.showMessageDialog(null, "Ya hay una pieza en ese espacio");
            }
        }
    }

    public void add(boolean king, boolean white, int row, int column){
        add(king, white, row, column, "normal");
    }

    /**
     * Método que toma una representación en String de un tablero y la transforma al modelo correspondiente
     * @param checkerboard La representación en String del tablero según la arena que se está simulando
     */
    public void read(String checkerboard){
        int index = 0;
        //limpia el tablero
        clear();
        //Coloca las nuevas fichas
        for(int i = 1; i < width + 1; i++){
            for (int j = 1; j < width + 1; j++){
                if((checkerboard.charAt(index)!='.') && (checkerboard.charAt(index)!='-')){
                    // asigna la fichas al tablero de configuración dependiendo del caracter que tenga
                    char typeChar = checkerboard.charAt(index);
                    char otherInfo = checkerboard.charAt(index + 1);
                    String type;
                    switch(typeChar){
                        case 'N':
                        type = "normal";
                        break;
                        case 'P':
                        type = "proletarian";
                        break;
                        case 'L':
                        type = "libertarian";
                        break;
                        default:
                        type = "normal";
                        break;
                    }
                    if(otherInfo=='B'){
                        add(true, false, i, j, type);
                    }else if(otherInfo=='W'){
                        add(true, true, i, j, type);
                    }else if(otherInfo=='w'){
                        add(false, true, i, j, type);
                    }else if(otherInfo=='b'){
                        add(false, false, i, j, type);
                    }
                    index ++;
                }
                index ++;
            }           
        }
    }

    /**
     * Método que traduce el tablero en su equivalente en fotmato String según la arena a resolver
     *
     * @return El tablero en formato String
     */
    public String write(){
        String cadena="";
        Piece piece;
        // crea matriz de caracteres con un tamaño de width de . y -
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                piece = findPiece(i + 1, j + 1);
                if (piece != null){
                    cadena += piece.getInitials();
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

    /**
     * Método Método que transforma la posición en índices a coordenadas del canvas
     *
     * @param row Fila en el tablero
     * @param column Columna en el tablero
     * @return Las coordenadas en el canvas de la esquina superior izquierda de esa celda
     */
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

    /**
     * Método que borra todas las piezas del tablero
     */
    public void clear(){
        for(int i = pieces.size() - 1; i >= 0; i--){
            pieces.get(i).remove();
        }
    }

    /**
     * Método que calcula la información del tablero de la forma [Fichas blancas/negras][Cantidad de fichas][informacion de la ficha]
     *
     * @return La matriz que representa la información de las piezas del tablero
     */
    public int[][][] getPiecesDescription(){
        int blackPieces = getNumberOfPieces(true);
        int whitePieces = getNumberOfPieces(false);
        int blackCount = 0;
        int whiteCount = 0;
        int[][][] description = new int[2][Math.max(blackPieces, whitePieces)][3];

        for(Piece p : pieces){
            int firstIndex = p.isWhite() ? 0 : 1;
            int secondIndex = p.isWhite() ? whiteCount++ : blackCount++;
            int[] data = new int[3];
            data[0] = p.isKing() ? 1 : 0;
            data[1] = p.getRow();
            data[2] = p.getColumn();

            description [firstIndex][secondIndex] = data;
        }
        return description;
    }

    public int getWidth(){
        return width;
    }

    /**
     * Método que retorna el numero de piezas de cierto color presentes en el tablero
     *
     * @param black Si la pieza es negra
     * @return El numero de piezas del color dado en el tablero
     */
    protected int getNumberOfPieces(boolean black){
        int count = 0;
        for (Piece piece : pieces){
            if((black && !piece.isWhite()) || (!black && piece.isWhite())){
                count += 1;
            }
        }
        return count;
    }

    /**
     * Método que verifica si un cuadro en ciertas coordenadas es negro o no
     *
     * @param row fila a verificar
     * @param column Columna a verificar
     * @return Si dicho cuadrado es negro o no
     */
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
        pieces.remove(piece);
    }

    private void addType(boolean king, boolean white, int[] coords, int row, int column, String type){
        switch(type){
            case "normal":
            pieces.add(new Piece(this, king, white, visible, coords[0], coords[1], row, column, squareSize));
            break;

            case "proletarian":
            pieces.add(new Proletarian(this, king, white, visible, coords[0], coords[1], row, column, squareSize));
            break;
            
            case "libertarian":
            pieces.add(new Libertarian(this, king, white, visible, coords[0], coords[1], row, column, squareSize));
            break;
        }
    }

    /**
     * Método auxiliar para crear la cuadricula del tablero
     *
     * @param width La cantidad de casillas en cada eje
     * @param xPosition La posición de la esquina superior izquierda en el eje x
     * @param yPosition La posición de la esquina superiuor izquierda en el eje y
     * @param lightColor Color claro del tablero R,G,B
     * @param darkColor Color oscuro del tablero R,G,B
     */
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

import javax.swing.JOptionPane;

/**
 * Clase que extiende a Board y representa el tablero de juego
 *
 * @author Paula Guevara & Daniel Rincón
 * @version 06-mar-2020
 */
public class GameBoard extends Board
{
    private Piece selectedPiece;

    /**
     * GameBoard Constructor
     *
     * @param width La cantidad de celdas en cada dirección
     * @param squareSize El tamaño de cada celda en pixeles
     * @param xPosition La posición de la esquina superior izquierda en el eje x
     * @param yPosition La posición de la esquina superiuor izquierda en el eje y
     * @param lightColor Color claro del tablero R,G,B
     * @param darkColor Color oscuro del tablero R,G,B
     */
    public GameBoard(int width, int squareSize, int xPosition, int yPosition, String lightColor, String darkColor)
    {
        super(width, squareSize, xPosition, yPosition, lightColor, darkColor);
    }

    /**
     * Método que selecciona una pieza en la fila y columna dadas
     *
     * @param row Fila de la pieza
     * @param column Columna de la pieza
     */
    public void select(int row, int column){
        Piece piece = findPiece(row, column);
        //Verificar si hay una pieza en la fila y la columna dadas
        if (piece != null){
            //Deseleccionar la pieza seleccionada
            if (selectedPiece != null){
                selectedPiece.setSelected(false);
                selectedPiece = null;
            }        
            //Seleccionar la nueva pieza
            piece.setSelected(true);
            selectedPiece = piece;
        }else{
            JOptionPane.showMessageDialog(null, "No hay ninguna pieza en esa posición");
        }
    }

    /**
     * Mueve una ficha diagonalmente una casilla si es posible (debe haber una ficha seleccionada)
     * 
     * @param top Si se desea que se mueva hacia arriba
     * @param right Si se desea que se mueva hacia la derecha
     */
    public void shift(boolean top, boolean right){
        if (selectedPiece != null){
            int pieceRow = selectedPiece.getRow();
            int pieceColumn = selectedPiece.getColumn();
            int newRow = top ? pieceRow - 1 : pieceRow + 1;
            int newColumn = right ? pieceColumn + 1 : pieceColumn - 1;
            Piece blockingPiece = findPiece(newRow, newColumn);

            if (selectedPiece.validMovement(top, right) && blockingPiece == null){
                int[] coordinates = positionToCoordinates(newRow, newColumn);
                if(coordinates != null){
                    selectedPiece.move(coordinates[0], coordinates[1], newRow, newColumn);
                }else{
                    JOptionPane.showMessageDialog(null, "Se sale de la zona de juego!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "La pieza no se puede mover en esa dirección o la casilla destino ya está ocupada");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una pieza primero");
        }
    }

    /**
     * Mueve una ficha diagonalmente saltando sobre una enemiga si es posible (debe haber una ficha seleccionada)
     * 
     * @param top Si se desea que se mueva hacia arriba
     * @param right Si se desea que se mueva hacia la derecha
     */
    public void jump(boolean top, boolean right){
        if (selectedPiece != null){
            int pieceRow = selectedPiece.getRow();
            int pieceColumn = selectedPiece.getColumn();
            int enemyRow = top ? pieceRow - 1 : pieceRow + 1;
            int enemyColumn = right ? pieceColumn + 1 : pieceColumn - 1;
            boolean sameTeam;
            int newRow = top ? pieceRow - 2 : pieceRow + 2;
            int newColumn = right ? pieceColumn + 2 : pieceColumn - 2;

            Piece piece = findPiece(newRow, newColumn);
            Piece enemyPiece = findPiece(enemyRow, enemyColumn);

            if (enemyPiece != null){
                sameTeam = selectedPiece.isWhite() == enemyPiece.isWhite();
                if (!sameTeam){
                    if (selectedPiece.validMovement(top, right) && piece == null){
                        int[] coordinates = positionToCoordinates(newRow, newColumn);
                        if(coordinates != null){
                            selectedPiece.move(coordinates[0], coordinates[1], newRow, newColumn);
                            removePiece(enemyPiece);
                        }else{
                            JOptionPane.showMessageDialog(null, "Se sale de la zona de juego!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "La pieza no se puede mover en esa dirección o la casilla destino ya está ocupada");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Tienes que atacar a una ficha del otro equipo!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No puede saltar en esa direccion, no hay nadie a quién atrapar");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una pieza primero");
        }
    }

    /**
     * Método que extiende las funcionalidades de la clase padre, deseleccionando
     * la pieza seleccionada antes de limpiar el tablero.
     */
    @Override
    public void clear(){
        if(selectedPiece != null){
            selectedPiece = null;
        }
        super.clear();
    }
}
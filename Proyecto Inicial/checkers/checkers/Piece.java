package checkers;

import shapes.*;
import javax.swing.JOptionPane;

/**
 * Clase que describe una pieza utilizada para el juego de Checkers.
 * 
 * @author Paula Guevara & Daniel Rincón 
 * @version 04-02-2020
 */
public class Piece
{
    protected Board board;

    protected boolean isKing;
    protected boolean isWhite;
    protected boolean visible;

    protected final float framePercentage = 0.2f;
    protected final float crownPercentage = 0.3f;

    protected int row;
    protected int column;
    protected int xPosition = 0;
    protected int yPosition = 0;
    protected int size = 10;
    protected int circleXPosition;
    protected int circleYPosition;
    protected int circleSize;
    protected int crownXPosition;
    protected int crownYPosition;
    protected int crownSize;
    protected int kingRow;

    protected String circleColor;
    protected String crownColor;
    protected String initials;
    protected String type;

    protected Circle circle;
    protected Circle crown;

    /**
     * Constructor de la pieza
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
    public Piece(Board board, boolean isKing, boolean isWhite, boolean visible, int xPosition, int yPosition, int row, int column, int size){        
        this.board = board;
        this.isKing = isKing;
        this.isWhite = isWhite;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.row = row;
        this.column = column;
        this.size = size;
        this.visible = visible;

        setColors();
        create();
    }

    /**
     * Mover la pieza a otra posición en el tablero
     * 
     * @param newPositionX La nueva posición en el eje x
     * @param newPositionY La nueva posición en el eje y
     * @param newRow La nueva fila 
     * @param newColumn La nueva columna
     */
    public void move(int newPositionX, int newPositionY, int newRow, int newColumn){
        xPosition = newPositionX;
        yPosition = newPositionY;
        row = newRow;
        column = newColumn;
        calculateCircleData();
        changePosition();
        checkKing();
    }

    /**
     * Cambiar el estado de selección de la pieza
     * 
     * @param selected Si la pieza está seleccionada
     */
    public void setSelected(boolean selected){
        if (selected){
            circle.changeColor("orange");
        }else{
            circle.changeColor(circleColor);
        }

        if (crown != null){
            crown.redraw();
        }
    }

    /**
     * Cambiar si la píeza es rey o no
     * 
     * @param isKing si la pieza es rey
     */
    public void setKing(boolean isKing){
        this.isKing = isKing;
        if (!isKing && crown != null){
            crown.makeInvisible();
            crown = null;
        }
        create();
    }

    /**
     * Eliminar la pieza
     */
    public void remove(){
        circle.makeInvisible();
        if (crown != null){
            crown.makeInvisible();
        }
        board.removePiece(this);
    }

    /**
     * Hacer visible la pieza
     */
    public void makeVisible(){
        circle.makeVisible();
        if (crown != null){
            crown.makeVisible();
        }
        visible = true;
    }

    /**
     * Hacer invisible la pieza
     */
    public void makeInvisible(){
        circle.makeInvisible();
        if (crown != null){
            crown.makeInvisible();
        }
        visible = false;
    }

    /**
     * Obtener la fila a la que peretenece la pieza
     * 
     * @return La fila en la que se encuentra la pieza
     */
    public int getRow(){
        return row;
    }

    /**
     * Obtener la columna a la que pertenece la pieza
     * 
     * @return La columna en la que se encuentra la pieza.
     */
    public int getColumn(){
        return column;
    }

    /**
     * Si la pieza es un rey
     * 
     * @return Si la pieza es un rey
     */
    public boolean isKing(){
        return isKing;
    }

    /**
     * Obtener si la pieza es blanca
     * 
     * @return Si la pieza es blanca
     */
    public boolean isWhite(){
        return isWhite;
    }

    /**
     * Obtener si el movimiento que se pretende hacer es válido según el tipo y el color de la pieza
     * 
     * @param top Si se desea mover hacia arriba
     * @param right Si se desea mover hacia la derecha
     * 
     * @return Si el movimiento es válido.
     */
    public boolean validMovement(boolean top, boolean right){
        if (isKing || (isWhite && top) || (!isWhite && !top)){
            return true;
        }
        return false;
    }

    public void shift(boolean top, boolean right, int newRow, int newColumn, Piece blockingPiece, int[] coordinates){
        if (validMovement(top, right) && blockingPiece == null){
            if(coordinates != null){
                move(coordinates[0], coordinates[1], newRow, newColumn);
            }else{
                JOptionPane.showMessageDialog(null, "Se sale de la zona de juego!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "La pieza no se puede mover en esa dirección o la casilla destino ya está ocupada");
        }
    }

    public void jump(boolean top, boolean right, int newRow, int newColumn, Piece enemyPiece, Piece blockingPiece, int[] coordinates){
        if (enemyPiece != null){
            boolean sameTeam = isWhite() == enemyPiece.isWhite();
            if (!sameTeam){
                if (validMovement(top, right) && blockingPiece == null){
                    if(coordinates != null){
                        move(coordinates[0], coordinates[1], newRow, newColumn);
                        enemyPiece.remove();
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
    }
    
    public String getInitials(){
        return initials;
    }
    
    public String getType(){
        return type;
    }
    
    protected void calculateInitialType(){
        initials = "N";
        type = "normal";
        calculateInitialColorAndKing();
    }
    
    protected void calculateInitialColorAndKing(){
        if(isKing){
            if(isWhite){
                initials += "W";
            }else{
                initials += "B";
            }
        }else{
            if(isWhite){
                initials += "w";
            }else{
                initials += "b";
            }
        }
    }

    /**
     * Función que calcula la posición en X y en Y del circulo dibujado y de la corona si la pieza la posee
     */
    private void calculateCircleData(){
        circleXPosition = xPosition + (int)(size * framePercentage / 2f);
        circleYPosition = yPosition + (int)(size * framePercentage / 2f);
        circleSize = size - (int)(size * framePercentage);
        crownSize = (int)(size * crownPercentage);
        crownXPosition = circleXPosition + (circleSize / 2) - (crownSize / 2);
        crownYPosition = circleYPosition + (int)(crownSize * crownPercentage);
    }

    /**
     * Función que cambia la posición de la ficha
     */
    private void changePosition(){
        circle.setPosition(circleXPosition, circleYPosition);

        if (crown != null){
            crown.setPosition(crownXPosition, crownYPosition);
        }
    }
    
    private void checkKing(){
        if(!isKing && row == kingRow){
            setKing(true);
        }
    }

    /**
     * Función para crear los componentes de la ficha y dibujarlos si es necesario
     */
    private void create(){
        calculateCircleData();
        calculateInitialType();
        kingRow = isWhite ? 1 : board.getWidth();
        if (circle == null){
            circle = new Circle(circleSize, circleXPosition, circleYPosition, circleColor);
            if (visible){
                circle.makeVisible();
            }
        }   
        if (isKing && crown == null){
            crown = new Circle(crownSize, crownXPosition, crownYPosition, crownColor);
            if (visible){
                crown.makeVisible();
            }
        }
    }

    /**
     * Función que define los colores que posee la ficha
     */
    private void setColors(){
        if (isWhite){
            circleColor = "white";
        }else{
            circleColor = "black";
        }
        crownColor = "red";
    }
}

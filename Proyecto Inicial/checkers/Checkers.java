import java.util.ArrayList;
import java.awt.*;
import javax.swing.JOptionPane;

/**
 * Clase para simular la arena Checks Post Facto.
 *
 * @author Paula Guevara & Daniel Rincón
 * @version 06-feb-2020
 */
public class Checkers
{
    // instance variables - replace the example below with your own
    private boolean ok;
    private boolean isVisible;
    private boolean isInConfigurationZone;

    private int width;
    private final int size;
    private String checherboard;
    private Piece selectedPiece;
    private Rectangle [][] zonaDeJuego;
    private Rectangle [][] zonaDeConfiguracion;

    private ArrayList<Piece> pieces;
    private ArrayList<String> strings;
    /**
     * Constructor de la clase Checkers
     * 
     * @param width El número de celdas que componen el tablero (es cuadrado)
     */
    public Checkers(int width)
    {
        // initialise instance variable
        ok = true;
        isVisible = false;
        isInConfigurationZone = true;
        size = 50;
        this.width = width;

        zonaDeJuego = new Rectangle[width][width];
        zonaDeConfiguracion = new Rectangle[width][width];

        Canvas canvas = new Canvas("Checkers", 1080, 500);
        pieces = new ArrayList<Piece>();
        strings= new ArrayList<String>();
        crearTablero();

        this.checherboard= checherboard;
    }

    /**
     * Selecciona una ficha que se encuentra en el tablero de juego si es posible.
     * 
     * @param row La fila de la ficha que se desea seleccionar
     * @param column La columna de la ficha que se desea seleccionar
     */
    public void select(int row, int column){
        Piece piece = findPiece(row, column);
        //Verificar si hay una pieza en la fila y la columna dadas
        if (piece == null){
            JOptionPane.showMessageDialog(null, "No hay ninguna pieza en esa posición");
            return;
        }
        //Deseleccionar la pieza seleccionada
        if (selectedPiece != null){
            selectedPiece.setSelected(false);
        }        
        //Seleccionar la nueva pieza
        piece.setSelected(true);
        selectedPiece = piece;
    }

    /**
     * Mueve una ficha diagonalmente una casilla si es posible (debe haber una ficha seleccionada)
     * 
     * @param top Si se desea que se mueva hacia arriba
     * @param right Si se desea que se mueva hacia la derecha
     */
    public void shift(boolean top, boolean right){
        if (isInConfigurationZone){
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de juego para mover una pieza");
            return;
        }
        if (selectedPiece == null){
            JOptionPane.showMessageDialog(null, "Seleccione una pieza primero");
            return;
        }

        int pieceRow = selectedPiece.getRow();
        int pieceColumn = selectedPiece.getColumn();

        pieceRow += top ? -1 : 1;
        pieceColumn += right ? 1 : -1;

        Piece piece = findPiece(pieceRow, pieceColumn);

        if (selectedPiece.validMovement(top, right) && piece == null){
            int[] coordinates = positionToCoordinates(pieceRow, pieceColumn);
            if(coordinates == null){
                JOptionPane.showMessageDialog(null, "Se sale de la zona de juego!");
                return;
            }
            selectedPiece.move(coordinates[0], coordinates[1], pieceRow, pieceColumn);
        }
    }

    /**
     * Mueve una ficha diagonalmente saltando sobre una enemiga si es posible (debe haber una ficha seleccionada)
     * 
     * @param top Si se desea que se mueva hacia arriba
     * @param right Si se desea que se mueva hacia la derecha
     */
    public void jump(boolean top, boolean right){
        if (isInConfigurationZone){
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de juego para mover una pieza");
            return;
        }

        if (selectedPiece == null){
            JOptionPane.showMessageDialog(null, "Seleccione una pieza primero");
            return;
        }

        int pieceRow = selectedPiece.getRow();
        int pieceColumn = selectedPiece.getColumn();
        int enemyRow;
        int enemyColumn;

        enemyRow = top ? pieceRow - 1 : pieceRow + 1;
        pieceRow += top ? -2 : 2;

        enemyColumn = right ? pieceColumn + 1 : pieceColumn - 1;
        pieceColumn += right ? 2 : -2;

        Piece piece = findPiece(pieceRow, pieceColumn);
        Piece enemyPiece = findPiece(enemyRow, enemyColumn);

        if (enemyPiece == null){
            JOptionPane.showMessageDialog(null, "No puede saltar en esa direccion");
            return;
        }

        boolean sameTeam = selectedPiece.isWhite() == enemyPiece.isWhite();
        if (sameTeam){
            JOptionPane.showMessageDialog(null, "Tienes que atacar a una ficha del otro equipo!");
            return;
        }

        if (selectedPiece.validMovement(top, right) && piece == null){
            int[] coordinates = positionToCoordinates(pieceRow, pieceColumn);
            if(coordinates == null){
                JOptionPane.showMessageDialog(null, "Se sale de la zona de juego!");
                return;
            }
            selectedPiece.move(coordinates[0], coordinates[1], pieceRow, pieceColumn);
            removePiece(enemyPiece);
        }
    }

    /**
     *  
     * 
     * @param king Si la ficha es un rey
     * @param white Si la ficha es blanca
     * @param row Fila en la que se desea colocar la ficha
     * @param column Columna en la que se desea colocar la ficha
     */
    public void add(boolean king, boolean white, int row, int column){
        //Verificar si está en la zona de configuración
        if (!isInConfigurationZone){
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder agregar una pieza");
            return;
        }

        //Encontrar las coordenadas de la fila y la columna
        int[] coords = positionToCoordinates(row, column);
        if (coords == null){
            return;
        }

        //Verificar que no haya una ficha en esa posición
        for (Piece piece : pieces){
            int pieceRow = piece.getRow();
            int pieceColumn = piece.getColumn();

            if (pieceRow == row && pieceColumn == column){
                JOptionPane.showMessageDialog(null, "Ya hay una pieza en ese espacio");
                return;
            }
        }

        pieces.add(new Piece(king, white, isVisible, coords[0], coords[1], row, column, size));
    }

    /**
     * Agrega múltiples piezas al tablero
     * 
     * @param men Matriz de tamaño n x 2 en donde n es la cantidad de piezas a añadir. Esta matriz debe presentar la fila y la columna de cada una de las piezas.
     * @param white Si la pieza es blanca.
     */
    public void add(int[][] men, boolean white){
        //Verificar si está en la zona de configuración
        if (!isInConfigurationZone){
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder agregar una pieza");
            return;
        }

        for (int i = 0; i < men.length; i++){
            add(false, white, men[i][0], men[i][1]);
        }
    }

    /**
     * Remover una ficha del tablero si es posible
     * 
     * @param row Fila en la que se encuentra la ficha
     * @param column Columna en la que se encuentra la ficha
     */
    public void remove(int row, int column){
        if (!isInConfigurationZone){
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder eliminar una pieza");
            return;
        }

        Piece piece = findPiece(row, column);
        if (piece == null){
            JOptionPane.showMessageDialog(null, "No hay ninguna pieza en esa posición");
            return;
        }
        removePiece(piece);
    }

    /**
     * Remueve múltiples piezas del tablero
     * 
     * @param pieces Matriz de tamaño n x 2 en donde n es la cantidad de piezas a eliminar. Esta matriz debe presentar la fila y la columna de cada una de las piezas.
     */
    public void remove(int[][] pieces){
        if (!isInConfigurationZone){
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder eliminar una pieza");
            return;
        }

        for (int i = 0; i < pieces.length; i++){
            remove(pieces[i][0], pieces[i][1]);
        }
    }

    /**
     * Intercambiar entre la zona de juego y la zona de configuración
     */
    public void swap(){
        isInConfigurationZone = !isInConfigurationZone;
        for (Piece piece : pieces){
            int row = piece.getRow();
            int column = piece.getColumn();
            int [] coordinates = new int[2];

            coordinates = positionToCoordinates(row, column);
            piece.move(coordinates[0], coordinates[1], row, column);
        }
    }

    /**
     * Consulta las coordenadas y la posición en la que se encuentra cada ficha
     * 
     * @return Una matriz que presenta cada pieza con sus respectivas coordenadas y el tablero en donde está ubicada
     */
    public int[][] consult(){
        int[][] answer = new int[pieces.size()][3];
        int tablero = isInConfigurationZone ? 1 : 0;

        for(int i = 0; i < pieces.size(); i++){
            answer[i][0] = pieces.get(i).getRow();
            answer[i][1] = pieces.get(i).getColumn();
            answer[i][2] = tablero;
        }

        return answer;
    }

    /**
     * Hacer visible el tablero de juego
     */
    public void makeVisible(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                zonaDeJuego[i][j].makeVisible();
                zonaDeConfiguracion[i][j].makeVisible();
            }
        }
        for (Piece piece : pieces){
            piece.makeVisible();
        }
        isVisible = true;
    }

    /**
     * Hacer invisible el tablero de juego
     */
    public void makeInvisible(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                zonaDeJuego[i][j].makeInvisible();
                zonaDeConfiguracion[i][j].makeInvisible();
            }
        }
        for (Piece piece : pieces){
            piece.makeInvisible();
        }
        isVisible = false;
    }

    /**
     * Función que retorna si la ultima instrucción se ejecutó correctamente
     * 
     * @return Si la última instrucción se ejecutó correctamente
     */
    public boolean ok(){
        return ok;
    }

    /**
     * Terminar el juego.
     */
    public void finish(){
        System.exit(0);
    }

    
    /**
     * Función que traduce todo el tablero en una cadana de string 
     * @return una cadena correspondiente a las posiciones del tablero
     */
    public String  write(){
        String cadena="";
        String matriz [][] = new String [width][width];
        int cordenadas [][] = new int [pieces.size()][pieces.size()];
        // crea matriz de caracteres con un tamaño de width de . y -
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                if((i%2==0 && j%2==0)||(i%2==1 && j%2==1)){
                    matriz[i][j]="-";
                }
                else{
                    matriz[i][j]=".";
                }
            }
        }
        //consulta las cordenadas de la ficha
        for(int i = 0; i < pieces.size(); i++){
            cordenadas[i][0] = pieces.get(i).getRow();
            cordenadas[i][1] = pieces.get(i).getColumn();
        }
        //asigna a la matriz creada los caracteres correspodiente a las fichas que se encuentra en tablero
        for(int i = 0; i < pieces.size(); i++){
            int x=cordenadas[i][0]-1;
            int y=cordenadas[i][1]-1;
            if ((pieces.get(i).isWhite()==false) && (pieces.get(i).isKing()==true)){
                matriz[x][y]="B";
            }

            else if((pieces.get(i).isWhite()==true) &&   (pieces.get(i).isKing()==true)){
                matriz[x][y]="W";
            }

            else if((pieces.get(i).isWhite()==false)){
                matriz[x][y]="b";
            }
            else{
                matriz[x][y]="w";
            }
        } 

        //asigna a la cadena los caracteres que esta en matriz
        for(int i = 0; i < width; i++){
            for(int j = 0; j < width; j++){
                cadena +=matriz[i][j];      
            }
        }

        return cadena;
    } 

    /**
     * Fución que recibe una cadena de string y los posicicona en el tablero de coniguración
     * @param checherboard cadena de string
     */
    public void read(String checherboard){
        int index = 0;
        for(int i = 1; i < width+1; i++){
            for (int j = 1; j < width+1; j++){
                // verifica en esa posicion ya existe una ficha para removerla
                if(this.findPiece(i,j)!=null){
                    this.removePiece(this.findPiece(i,j));
                }
                if((checherboard.charAt(index)!='.')||(checherboard.charAt(index)!='-')){
                    // asigna la fichas al tablero de configuración dependiendo del caracter que tenga
                    if(checherboard.charAt(index)=='B'){
                        this.add(true,false, i,j);
                    }
                    else if(checherboard.charAt(index)=='W'){
                        this.add(true, true, i, j);
                    }
                    else if(checherboard.charAt(index)=='w'){
                        this.add(false,true, i,j);
                    }
                    else if(checherboard.charAt(index)=='b'){
                        this.add(false,false,i,j);
                    }
                }
                index ++;
            }           
        }
    }
    
    
 

    /**
     * Función que vuelve una pieza invisible y la elimina del arrayList de piezas
     * 
     * @param piece La pieza a eliminar
     */
    private void removePiece(Piece piece){
        piece.remove();
        pieces.remove(piece);
    }

    /**
     * Función que encuentra si una pieza existe en una posición en específico
     * 
     * @param row La fila en la que se desea buscar
     * @param column La columna en la que se desea buscar
     * 
     * @return La pieza si la encontró o null si no fue así
     */
    private Piece findPiece(int row, int column){
        Piece foundPiece = null;

        for (Piece piece : pieces){
            int pieceRow = piece.getRow();
            int pieceColumn = piece.getColumn();
            if (pieceRow == row && pieceColumn == column){
                foundPiece = piece;
                break;
            }
        }

        return foundPiece;
    }

    private void crearTablero(){
        int color = 0;
        int margen = 10;
        int distanciaTableroConfig = 200;

        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                zonaDeJuego[j][i] = new Rectangle((margen + (size + 1) * i), margen + ((size + 1) * j), size);
                zonaDeConfiguracion[j][i] = new Rectangle( ((width * (size + 1)) + distanciaTableroConfig) + ((size + 1) * i) , (margen + (size + 1) * j) , size );
                if (color == 0){
                    zonaDeJuego[j][i].changeColor("219, 198, 212 ");
                    zonaDeConfiguracion[j][i].changeColor("170, 204, 207 ");
                    color = 1;
                }
                else{
                    zonaDeJuego[j][i].changeColor("97, 50, 82 ");
                    zonaDeConfiguracion[j][i].changeColor("65, 120, 124");
                    color = 0;
                }
            }
            if (color == 0){
                color = 1;
            }
            else{
                color = 0;
            }
        }
    }

    /**
     * Función que cambia las posiciones del tablero a coordenadas del canvas en pixeles
     * 
     * @param row La fila que se desea calcular
     * @param column La columna que se desea calcular
     * 
     * @return Una lista con las dos coordenadas, o null si no es una posición válida.
     */
    private int[] positionToCoordinates(int row, int column){
        if (row < 1 || row > width || column < 1 || column > width){
            JOptionPane.showMessageDialog(null, "La posición no existe en el tablero");
            return null;
        }

        int [] coords = new int[2];
        Rectangle rectangle;

        if (isInConfigurationZone){
            rectangle  = zonaDeConfiguracion[row - 1][column - 1];
        }else{
            rectangle  = zonaDeJuego[row - 1][column - 1];
        }
        coords[0] = rectangle.getXPosition();
        coords[1] = rectangle.getYPosition();

        return coords;
    }
}


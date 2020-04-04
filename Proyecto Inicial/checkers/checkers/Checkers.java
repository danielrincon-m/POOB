package checkers;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import shapes.*;


/**
 * Clase para simular la arena Checks Post Facto.
 *
 * @author Paula Guevara & Daniel Rincón
 * @version 06-mar-2020
 */
public class Checkers
{
    // instance variables - replace the example below with your own
    private final int squareSize;
    private boolean ok;
    private boolean isVisible;
    private boolean isInConfigurationZone;
    private int width;
    private int moveSequenceNumber;
    private GameBoard gameZone;
    private ConfigurationBoard configurationZone;

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
        this.width = width;
        squareSize = 50;

        Canvas canvas = new Canvas("Checkers", 1080, 500);
        createBoards();
    }

    /**
     * Selecciona una ficha que se encuentra en el tablero de juego si es posible.
     * 
     * @param row La fila de la ficha que se desea seleccionar
     * @param column La columna de la ficha que se desea seleccionar
     */
    public void select(int row, int column){
        if (!isInConfigurationZone){
            gameZone.select(row, column);
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de juego.");
        }
    }

    /**
     * Método que des-selecciona la pieza seleccionada si esta existe
     */
    public void unSelect(){
        gameZone.unSelect();
    }

    /**
     * Mueve una ficha diagonalmente una casilla si es posible (debe haber una ficha seleccionada)
     * 
     * @param top Si se desea que se mueva hacia arriba
     * @param right Si se desea que se mueva hacia la derecha
     */
    public void shift(boolean top, boolean right){
        if (!isInConfigurationZone){
            gameZone.shift(top, right);
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de juego para mover una pieza");
        }
    }

    /**
     * Mueve una ficha diagonalmente saltando sobre una enemiga si es posible (debe haber una ficha seleccionada)
     * 
     * @param top Si se desea que se mueva hacia arriba
     * @param right Si se desea que se mueva hacia la derecha
     */
    public void jump(boolean top, boolean right){
        if (!isInConfigurationZone){
            gameZone.jump(top, right);
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de juego para mover una pieza");
        }
    }

    /**
     * Mueve la posición segun unas instrucciones que son del tipo n1-n2 ó n1xn2xn3xn4x...
     * 
     * @param notation El String que representa los movimientos
     */
    public void move (String notation){
        if (!isInConfigurationZone){
            moveSequenceNumber += 1;
            boolean jumping = notation.contains("x") ? true : false;
            String delimiter = jumping ? "x" : "-";

            String[] stringNumbers = notation.split(delimiter);
            int[] numbers = new int[stringNumbers.length];

            for (int i = 0; i < stringNumbers.length; i++) {
                numbers[i] = Integer.parseInt(stringNumbers[i]);
            }

            int[] initialPosition = indexToPosition(numbers[0]);
            select(initialPosition[0], initialPosition[1]);
            for (int i = 1; i < numbers.length; i++){
                int[] startPosition = indexToPosition(numbers[i - 1]);
                int[] endPosition = indexToPosition(numbers[i]);

                if (jumping){
                    jump(startPosition[0] > endPosition[0], startPosition[1] < endPosition[1]);
                }else{
                    shift(startPosition[0] > endPosition[0], startPosition[1] < endPosition[1]);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de juego para realizar movimientos.");
        }
    }

    /**
     * Agrega una pieza al tablero
     * 
     * @param king Si la ficha es un rey
     * @param white Si la ficha es blanca
     * @param row Fila en la que se desea colocar la ficha
     * @param column Columna en la que se desea colocar la ficha
     * @param type El tipo de la pieza
     */
    public void add(boolean king, boolean white, int row, int column, String type){
        //Verificar si está en la zona de configuración
        if (isInConfigurationZone){
            configurationZone.add(king, white, row, column, type);
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder agregar una pieza");
        }
    }
    
    /**
     * Sobreescritura del método add, agrega una pieza normal
     *
     * @param king Si la ficha es un rey
     * @param white Si la ficha es blanca
     * @param row Fila en la que se desea colocar la ficha
     * @param column Columna en la que se desea colocar la ficha
     */
    public void add(boolean king, boolean white, int row, int column){
        add(king, white, row, column, "normal");
    }

    /**
     * Agrega múltiples piezas al tablero
     * 
     * @param men Matriz de tamaño n x 2 en donde n es la cantidad de piezas a añadir. Esta matriz debe presentar la fila y la columna de cada una de las piezas.
     * @param white Si la pieza es blanca.
     */
    public void add(int[][] men, boolean white){
        //Verificar si está en la zona de configuración
        if (isInConfigurationZone){
            for (int i = 0; i < men.length; i++){
                add(false, white, men[i][0], men[i][1]);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder agregar una pieza");
        }
    }

    /**
     * Remover una ficha del tablero si es posible
     * 
     * @param row Fila en la que se encuentra la ficha
     * @param column Columna en la que se encuentra la ficha
     */
    public void remove(int row, int column){
        if (isInConfigurationZone){
            Piece p = configurationZone.findPiece(row, column);
            p.remove();
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder eliminar una pieza");
        }
    }

    /**
     * Remueve múltiples piezas del tablero
     * 
     * @param pieces Matriz de tamaño n x 2 en donde n es la cantidad de piezas a eliminar. Esta matriz debe presentar la fila y la columna de cada una de las piezas.
     */
    public void remove(int[][] pieces){
        if (isInConfigurationZone){
            for (int i = 0; i < pieces.length; i++){
                remove(pieces[i][0], pieces[i][1]);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder eliminar una pieza");
        }
    }

    /**
     * Intercambiar entre la zona de juego y la zona de configuración
     */
    public void swap(){
        if(isInConfigurationZone){
            gameZone.clear();
            String configBoard = configurationZone.write();
            gameZone.read(configBoard);
        }else{
            configurationZone.clear();
            String gameBoard = gameZone.write();
            configurationZone.read(gameBoard);
        }
        isInConfigurationZone = !isInConfigurationZone;
    }

    /**
     * Consulta las coordenadas y la posición en la que se encuentra cada ficha
     * 
     * @return Una matriz que presenta cada pieza con sus respectivas coordenadas y el tablero en donde está ubicada
     */
    public int[][][][] consult(){
        int[][][][] answer = new int[2][][][];
        answer[0] = gameZone.getPiecesDescription();
        answer[1] = configurationZone.getPiecesDescription();
        return answer;
    }

    /**
     * Hacer visible el tablero de juego
     */
    public void makeVisible(){
        gameZone.makeVisible();
        configurationZone.makeVisible();
        isVisible = true;
    }

    /**
     * Hacer invisible el tablero de juego
     */
    public void makeInvisible(){
        gameZone.makeInvisible();
        configurationZone.makeInvisible();
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
     * Fución que recibe una cadena de string y los posicicona en el tablero de coniguración
     * @param checkerboard cadena de string que toma solo valores como: B, W, w, b.
     */
    public void read(String checkerboard){
        if (isInConfigurationZone){
            configurationZone.read(checkerboard);
        }else{
            gameZone.read(checkerboard);
        }
    }

    /**
     * Función que traduce todo el tablero en una cadana de string 
     * @return una cadena correspondiente a las posiciones del tablero
     */
    public String write(){
        if (isInConfigurationZone){
            return configurationZone.write();
        }else{
            return gameZone.write();
        }
    }

    /**
     * Guarda el tablero actual con el nombre dado
     * 
     * @param name El nombre que se le desea dar al tablero
     */
    public void save(String name){
        configurationZone.save(name);
    }

    /**
     * Carga y retorna en forma de string el tablero guardado con name, o null si el nombre no existe
     * 
     * @param name El nombre del tablero que se desea recuperar
     * 
     * @return El tablero cargado en forma de String
     */
    public String recover(String name){
        if(isInConfigurationZone){
            return configurationZone.recover(name);
        }else{
            JOptionPane.showMessageDialog(null, "Debe estar en la zona de configuración para poder cargar las fichas en el juego");
            return null;
        }
    }
    
    public int getMoveSequenceNumber() {
        return moveSequenceNumber;
    }

    /**
     * Convierte un indice de numeración (Arena Checkers) a coordenadas del tablero
     * 
     * @param index El indice que se desea convertir
     * 
     * @return Las cos coordenadas que representa ese indice
     */
    private int[] indexToPosition(int index){
        int[] coordinates = new int[2];
        int count = 0;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                if ((i + j ) % 2 == 1){
                    count ++;                    
                    if (count == index){
                        coordinates[0] = i + 1;
                        coordinates[1] = j + 1;
                    }
                }
            }
        }
        return coordinates;
    }

    /**
     * Crea los tableros de juego y configuración del tamaño deseado
     */
    private void createBoards(){
        int firstPosition = 10;
        int secondPosition = firstPosition * 10 + squareSize * width;

        gameZone = new GameBoard(width, squareSize, firstPosition, firstPosition, "219, 198, 212", "97, 50, 82", this);
        configurationZone = new ConfigurationBoard(width, squareSize, secondPosition, firstPosition, "170, 204, 207", "65, 120, 124", this);
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
        int[] coords;
        if (isInConfigurationZone){
            coords = configurationZone.positionToCoordinates(row, column);
        }else{
            coords = gameZone.positionToCoordinates(row, column);
        }
        return coords;
    }
}


package checkers;

import java.util.Arrays;

/**
 * Clase construida para solucionar y simular el problema de la arena Checks Post Facto
 *
 * @author Paula Guevara & Daniel Rincón
 * @version 06-mar-2020
 */
public class CheckersContest
{
    int size = 8;

    /**
     * Método que soluciona el problema de la arena
     *
     * @param player El color del primer jugador que mueve
     * @param moves Los movimientos realizados durante el juego
     * @return Dos tableros, el tablero inicial y el tablero final luego de aplicar todos los movimientos
     */
    public String[] solve(String player, String[] moves){
        String[] initialBoard = new String[32];
        String[] finalBoard;
        String[] answer = new String[2];
        String initialPiece = "";
        for(int i = moves.length - 1; i >= 0; i--){ //Recorrer los movimientos hacia atrás para reconstruir las posiciones
            boolean jumping = moves[i].contains("x") ? true : false;
            int[] spltMoves = splitMovements(jumping, moves[i]);
            for(int j = spltMoves.length - 1; j > 0; j--){ //Recorrer la secuencia en reversa para recorrer cada moviemiento específicamente
                initialBoard = calculatePastMovement(initialBoard, spltMoves[j - 1], spltMoves[j], jumping);
            }
        }
        finalBoard = initialBoard.clone();
        for(int i = 0; i < moves.length; i++){
            boolean jumping = moves[i].contains("x") ? true : false;
            int[] spltMoves = splitMovements(jumping, moves[i]);
            for(int j = 0; j < spltMoves.length - 1; j++){
                finalBoard = calculateFutureMovement(finalBoard, spltMoves[j], spltMoves[j + 1], jumping);
                if(i == 0 && j == 0){
                    initialPiece = finalBoard[spltMoves[j + 1] - 1];
                }
            }
        }
        answer[0] = toString(initialBoard, initialPiece, player);
        answer[1] = toString(finalBoard, initialPiece, player);
        //System.out.println(Arrays.toString(answer));
        return answer;
    }

    /**
     * Método que simula la solución de la arena por edio de la clas Checkers
     *
     * @param player El jugador que realiza el primer movimiento
     * @param moves La secuencia de movimientos a realizar
     * @param slow Si la simulación se realiza de forma lenta
     */
    public void simulate(String player, String[] moves, boolean slow){
        String[] solution = solve(player, moves);
        Checkers game = new Checkers(size);
        game.makeVisible();
        game.read(solution[0]);
        wait(slow);
        game.swap();
        wait(slow);
        for(int i = 0; i < moves.length; i++){
            boolean jumping = moves[i].contains("x") ? true : false;
            int[] spltMoves = splitMovements(jumping, moves[i]);
            for(int j = 0; j < spltMoves.length - 1; j++){
                String notation = getNotation(spltMoves[j], spltMoves[j + 1], jumping);
                game.move(notation);
                wait(slow);
            }
        }
        game.unSelect();
    } 

    /**
     * Método que calcula como debió ser el tablero antes del movimiento dado
     *
     * @param board La configuración del tablero luego de realizar el movimiento
     * @param fromIndex �?ndice inicial del movimiento
     * @param toIndex �?ndice final del movimiento
     * @param jumping Si se está saltando
     * @return El tablero antes de realizar el movimiento dado
     */
    private String[] calculatePastMovement(String[] board, int fromIndex, int toIndex, boolean jumping){
        fromIndex -= 1;
        toIndex -= 1;
        if(jumping){
            int centralIndex = indexBetween(fromIndex, toIndex);
            board[fromIndex] = board[toIndex] != null ? board[toIndex] : "x";
            if(board[centralIndex] != null){
                board[fromIndex] = getOpposite(board[centralIndex]);
            }else{
                board[centralIndex] = getOpposite(board[fromIndex]);
            }
        }else{
            board[fromIndex] = board[toIndex] != null ? board[toIndex] : "x";
        }
        board[toIndex] = null;
        return board;
    }

    /**
     * Método que calcula como es el tablero despues del movimiento dado
     *
     * @param board El tablero antes del movimeinto
     * @param fromIndex �?ndice inicial del movimiento
     * @param toIndex �?ndice final del movimiento
     * @param jumping Si se está saltando
     * @return El tablero despues de realizar el movimiento dado
     */
    private String[] calculateFutureMovement(String[] board, int fromIndex, int toIndex, boolean jumping){
        fromIndex -= 1;
        toIndex -= 1;
        if(jumping){
            int centralIndex = indexBetween(fromIndex, toIndex);
            board[centralIndex] = null;
        }
        board[toIndex] = board[fromIndex];
        board[fromIndex] = null;
        return board;
    }
    
        /**
     * Funcion que encuentra el indice entre dos casillas
     * @param firstIndex El primer índice de una de las dos casillas
     * @param secondIndex El segundo índice
     * @return El índice de la casilla que queda en medio de las dos o null si no existe tal casilla
     */
    private int indexBetween(int firstIndex, int secondIndex){
        int minimum = Math.min(firstIndex + 1, secondIndex + 1);
        int maximum = Math.max(firstIndex + 1, secondIndex + 1);
        if(((minimum - 1) / 4) % 2 == 0){
            return maximum - ((maximum - minimum) / 2) - 1;
        }else{
            return maximum - ((maximum - minimum) / 2 + 1) - 1;
        }
    }

    /**
     * Método que divide los movimientos que vienen juntos en una String
     * en movimientos independientes en un String[]
     * @param jumping Si se está saltando
     * @param move Los movimientos 
     * @return Los movimientos separados
     */
    private int[] splitMovements(boolean jumping, String move){
        String delimiter = jumping ? "x" : "-";
        String[] strMoves = move.split(delimiter);
        int[] spltMoves = new int[strMoves.length];
        for (int j = 0; j < strMoves.length; j++) {
            spltMoves[j] = Integer.parseInt(strMoves[j]);
        }
        return spltMoves;
    }

    /**
     * Método que toma el tablero y lo escribe en forma de cadena según el formato brindado por el enunciado de la arena
     *
     * @param board El tablero que se desea traducir a String
     * @param initialPiece La representación de la pieza inicial x-o
     * @param initialPlayer La representación del jugador inicial B-W
     * @return El tablero en forma de String con los jugadores reemplazados por su valor correcto
     */
    private String toString(String[] board, String initialPiece, String initialPlayer){
        int index = 0;
        String ans = "";
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if((i + j) % 2 == 0){
                    ans += "-";
                }else{
                    ans += board[index] == null ? "." : board[index];
                    index++;
                }
            }
        }
        ans = ans.replace(initialPiece, initialPlayer);
        ans = ans.replace(getOpposite(initialPiece), getOpposite(initialPlayer));
        return ans;
    }

    /**
     * Método que obtiene el opuesto del jugador o color dado
     *
     * @param color El jugador del cual se desea calcular el opuesto
     * @return El jugador opuesto
     */
    private String getOpposite(String color){
        switch(color){
            case "x":
            return "o";
            case "o":
            return "x";
            case "NW":
            return "NB";
            case "NB":
            return "NW";
        }
        return null;
    }
    
    /**
     * Método que construye la notación utilizada por el método move en la clase Checkers
     *
     * @param fromIndex El índice inicial
     * @param toIndex El índice final
     * @param jumping Si está saltando
     * @return La notación lista para ser utilizada por el método move
     */
    private String getNotation(int fromIndex, int toIndex, boolean jumping){
        String notation = Integer.toString(fromIndex);
        notation += jumping ? "x" : "-";
        notation += Integer.toString(toIndex);
        return notation;
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    private void wait(boolean slow){
        try{
            if(slow){
                Thread.sleep(1500);
            }
        } catch (InterruptedException e){
            // ignoring exception at the moment
        }
    }
}

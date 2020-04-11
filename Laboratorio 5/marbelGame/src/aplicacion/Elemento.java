package aplicacion;

import java.awt.*;

public abstract class Elemento {
    int fila;
    int columna;

    Color color;
    MarbelGameBoard tablero;
    String tipo;

    /**
     * Constructor del elemento
     * @param tablero El tablero en el que se etá jugando
     */
    public Elemento(MarbelGameBoard tablero) {
        this.tablero = tablero;
        int[] posicion = tablero.posicionAleatoriaValida();
        this.fila = posicion[0];
        this.columna = posicion[1];
        tablero.agregarElemento(fila, columna, this);
    }

    /**
     * Retorna el tipo del elemento
     * @return Tipo del elemento
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Retorna el color del elemento
     * @return Color del elemento
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retorna la información del elemento
     * @return Información del elemento
     */
    public String getInfo() {
        return tipo;// + " - " + color.toString();
    }
}

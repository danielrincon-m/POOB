package aplicacion;

import java.awt.*;

public abstract class Elemento {
    int fila;
    int columna;

    Color color;
    MarbelGameBoard tablero;
    String tipo;

    public Elemento(MarbelGameBoard tablero) {
        this.tablero = tablero;
        int[] posicion = tablero.posicionAleatoriaValida();
        this.fila = posicion[0];
        this.columna = posicion[1];
        tablero.agregarElemento(fila, columna, this);
    }

    public String getTipo() {
        return tipo;
    }

    public Color getColor() {
        return color;
    }

    public String getInfo() {
        return tipo;// + " - " + color.toString();
    }
}

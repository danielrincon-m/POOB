package aplicacion;

import java.awt.*;

public class Canica extends Elemento {
    public Canica(MarbelGameBoard tablero, Color color) {
        super(tablero);
        this.color = color;
        this.tipo = "canica";
    }

    public void mover(String direccion) {
        int sumandoFila = direccion.equals("norte") ? -1 : (direccion.equals("sur") ? 1 : 0);
        int sumandoColumna = direccion.equals("este") ? 1 : (direccion.equals("oeste") ? -1 : 0);
        int nuevaFila = fila + sumandoFila;
        int nuevaColumna = columna + sumandoColumna;
        boolean puedeMoverse = tablero.existePosicion(nuevaFila, nuevaColumna);

        while (puedeMoverse) {
            Elemento elementoEnFrente = tablero.elementoEn(nuevaFila, nuevaColumna);
            if (elementoEnFrente instanceof Agujero) {
                ((Agujero)elementoEnFrente).ocuparAgujero(color);
                tablero.eliminarElemento(fila, columna);
                puedeMoverse = false;
            } else if (elementoEnFrente instanceof Barrera || elementoEnFrente instanceof Canica) {
                puedeMoverse = false;
            } else if (elementoEnFrente == null) {
                tablero.moverElemento(fila, columna, nuevaFila, nuevaColumna);
                fila = nuevaFila;
                columna = nuevaColumna;
            }
            nuevaFila = fila + sumandoFila;
            nuevaColumna = columna + sumandoColumna;
            puedeMoverse = puedeMoverse && tablero.existePosicion(nuevaFila, nuevaColumna);
        }
    }
}

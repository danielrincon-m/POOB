package aplicacion;

import java.awt.*;

public class Canica extends Elemento {
    private Agujero agujeroOcupado;

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
            puedeMoverse = realizarMovimiento(elementoEnFrente, nuevaFila, nuevaColumna);
            nuevaFila = fila + sumandoFila;
            nuevaColumna = columna + sumandoColumna;
            puedeMoverse = puedeMoverse && tablero.existePosicion(nuevaFila, nuevaColumna);
        }
    }

    private boolean realizarMovimiento(Elemento elementoEnFrente, int nuevaFila, int nuevaColumna) {
        boolean puedeMoverse = true;
        if (elementoEnFrente instanceof Agujero) {
            Agujero agujero = (Agujero)elementoEnFrente;
            if (!agujero.estaOcupado()) {
                ocuparAgujero(agujero);
                puedeMoverse = false;
            } else {
                realizarMovimiento(nuevaFila, nuevaColumna, elementoEnFrente);
            }
        } else if (elementoEnFrente instanceof Barrera || elementoEnFrente instanceof Canica) {
            puedeMoverse = false;
        } else if (elementoEnFrente == null) {
            realizarMovimiento(nuevaFila, nuevaColumna, null);
        }
        return puedeMoverse;
    }

    private void ocuparAgujero(Agujero agujero) {
        agujero.ocuparAgujero(color);
        tablero.eliminarElemento(fila, columna);
        tablero.agregarUbicada(agujero.getColor() == color);
        if (agujeroOcupado != null) {
            tablero.agregarElemento(fila, columna, agujeroOcupado);
        }
    }

    private void realizarMovimiento(int nuevaFila, int nuevaColumna, Elemento elementoEnFrente) {
        Agujero nuevoAgujeroOcupado = null;
        if (elementoEnFrente != null){
            nuevoAgujeroOcupado = (Agujero)elementoEnFrente;
        }
        tablero.moverElemento(fila, columna, nuevaFila, nuevaColumna);
        if (agujeroOcupado != null) {
            tablero.agregarElemento(fila, columna, agujeroOcupado);
        }
        fila = nuevaFila;
        columna = nuevaColumna;
        agujeroOcupado = nuevoAgujeroOcupado;
    }
}

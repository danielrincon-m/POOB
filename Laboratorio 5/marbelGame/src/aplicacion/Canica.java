package aplicacion;

import java.awt.*;

public class Canica extends Elemento {
    private Agujero agujeroReemplazado;

    /**
     * Constructor de la clase Canica
     * @param tablero El tablero de juego
     * @param color El color de la Canica
     */
    public Canica(MarbelGameBoard tablero, Color color) {
        super(tablero);
        this.color = color;
        this.tipo = "canica";
    }

    /**
     * Mover la canica en la dirección dada hasta que no pueda hacerlo más
     * @param direccion La dirección de movimiento (norte, sur, este, oeste)
     */
    public void mover(String direccion) {
        int sumandoFila = direccion.equals("norte") ? -1 : (direccion.equals("sur") ? 1 : 0);
        int sumandoColumna = direccion.equals("este") ? 1 : (direccion.equals("oeste") ? -1 : 0);
        int nuevaFila = fila + sumandoFila;
        int nuevaColumna = columna + sumandoColumna;
        boolean puedeMoverse = tablero.existePosicion(nuevaFila, nuevaColumna);

        while (puedeMoverse) {
            Elemento elementoEnFrente = tablero.elementoEn(nuevaFila, nuevaColumna);
            puedeMoverse = verificarMovimientos(elementoEnFrente, nuevaFila, nuevaColumna);
            nuevaFila = fila + sumandoFila;
            nuevaColumna = columna + sumandoColumna;
            puedeMoverse = puedeMoverse && tablero.existePosicion(nuevaFila, nuevaColumna);
        }
    }

    /**
     * Evalúa las posibilidades de movimiento
     * @param elementoEnFrente El elemento que está en la posición a la cual se desea mover, o null si nop hay ninguno
     * @param nuevaFila La fila a la que se pretende mover
     * @param nuevaColumna La columna ala que se pretende mover
     * @return Si luego de está verificación se puede mover más en la dirección dada
     */
    private boolean verificarMovimientos(Elemento elementoEnFrente, int nuevaFila, int nuevaColumna) {
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

    /**
     * Si se encuentra un agujero lo ocupa, y elimina su referencia del tablero, convierte el agujero en un agujero ocupado
     * @param agujero El agujero que se va a ocupar
     */
    private void ocuparAgujero(Agujero agujero) {
        agujero.ocuparAgujero(color);
        tablero.eliminarElemento(fila, columna);
        tablero.agregarUbicada(agujero.getColor() == color);
        if (agujeroReemplazado != null) {
            tablero.agregarElemento(fila, columna, agujeroReemplazado);
        }
    }

    /**
     * Realizar un movimiento a la posición indicada
     * @param nuevaFila La nueva fila
     * @param nuevaColumna La nueva columna
     * @param elementoEnFrente Cual es el elemento que tiene en frente
     */
    private void realizarMovimiento(int nuevaFila, int nuevaColumna, Elemento elementoEnFrente) {
        Agujero nuevoAgujeroReemplazado = null;
        if (elementoEnFrente != null){
            nuevoAgujeroReemplazado = (Agujero)elementoEnFrente;
        }
        tablero.moverElemento(fila, columna, nuevaFila, nuevaColumna);
        if (agujeroReemplazado != null) {
            tablero.agregarElemento(fila, columna, agujeroReemplazado);
        }
        fila = nuevaFila;
        columna = nuevaColumna;
        agujeroReemplazado = nuevoAgujeroReemplazado;
    }
}

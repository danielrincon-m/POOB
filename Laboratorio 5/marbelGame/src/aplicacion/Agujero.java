package aplicacion;

import java.awt.*;

public class Agujero extends Elemento {
    private boolean ocupado = false;

    private Color colorRelleno = null;

    /**
     * Constructor del agujero
     * @param tablero El tablero de juego
     * @param color Color del agujero
     */
    public Agujero(MarbelGameBoard tablero, Color color) {
        super(tablero);
        this.color = color;
        this.tipo = "agujero";
    }

    /**
     * Convierte el agujero en un agujero ocupado
     * @param colorRelleno El color de la canica que lo ocupó
     */
    public void ocuparAgujero(Color colorRelleno) {
        ocupado = true;
        this.colorRelleno = colorRelleno;
    }

    /**
     * El agujero está ocupado?
     * @return Si el agujero está ocupado
     */
    public boolean estaOcupado() {
        return ocupado;
    }

    /**
     * Obtener el color del relleno del agujero
     * @return El color de la canica dentro del agujero
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " - " + ocupado;
    }
}

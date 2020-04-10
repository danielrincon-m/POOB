package aplicacion;

import java.awt.*;

public class Agujero extends Elemento {
    private boolean ocupado = false;

    private Color colorRelleno = null;

    public Agujero(MarbelGameBoard tablero, Color color) {
        super(tablero);
        this.color = color;
        this.tipo = "agujero";
    }

    public void ocuparAgujero(Color colorRelleno) {
        ocupado = true;
        this.colorRelleno = colorRelleno;
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public Color getColorRelleno() {
        return colorRelleno;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " - " + ocupado;
    }
}

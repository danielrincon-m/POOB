package aplicacion;

import java.awt.*;

public class Barrera extends Elemento {

    /**
     * Constructor de la barrera
     * @param tablero El tablero de juego
     */
    public Barrera(MarbelGameBoard tablero) {
        super(tablero);
        this.color = Color.BLACK;
        this.tipo = "barrera";
    }
}

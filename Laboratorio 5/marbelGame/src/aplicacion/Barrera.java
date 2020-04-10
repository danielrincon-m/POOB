package aplicacion;

import java.awt.*;

public class Barrera extends Elemento {
    public Barrera(MarbelGameBoard tablero) {
        super(tablero);
        this.color = Color.BLACK;
        this.tipo = "barrera";
    }
}

package presentacion;

import aplicacion.*;

import javax.swing.*;
import java.awt.*;

public class Casillas extends JPanel {
    private Elemento elemento;
    public Casillas(Elemento elemento){
        this.elemento = elemento;
    }

    private Image imagen;

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        int x = this.getWidth() / 2;
        int y = this.getHeight() / 2;
        int dx = x / 2;
        int dy = y / 2;
        g.setColor(elemento.getColor());
        if(elemento instanceof Barrera){
            g.fillRect(dx , dy, x, y);
        }
        else if (elemento instanceof  Agujero){
            g.drawOval(dx, dy, x, y);
            if(((Agujero)elemento).estaOcupado()){
                g.fillOval(dx+5,dy+5, x-10, y-10);
            }
        }
        else if (elemento instanceof  Canica){
            g.fillOval( dx, dy, x, y);
        }




    }


}




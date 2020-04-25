 package presentacion;

import aplicacion.*;

import javax.swing.*;
import java.awt.*;

public class Casilla extends JPanel {
    private Elemento elemento;

    public Casilla(Elemento elemento){
        this.elemento = elemento;
    }

    public void actualizar(Elemento elemento) {
        this.elemento = elemento;
        repaint();
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        int x = this.getWidth() / 2;
        int y = this.getHeight() / 2;
        int dx = x / 2;
        int dy = y / 2;

        if (elemento != null) {
            g.setColor(elemento.getColor());
        }
        if(elemento instanceof Barrera){
            g.fillRect(dx , dy, x, y);
        }
        else if (elemento instanceof  Agujero){
            g.drawOval(dx, dy, x, y);
            if(((Agujero)elemento).estaOcupado()){
                g.setColor(((Agujero)elemento).getColorRelleno());
                g.fillOval(dx+5,dy+5, x-10, y-10);
            }
        }
        else if (elemento instanceof  Canica){
            g.fillOval( dx, dy, x, y);
        }
    }
}




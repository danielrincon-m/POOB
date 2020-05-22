package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase abstracta base de todas las pantallas
 */
public abstract class Screen extends JPanel {

    protected String fondoInicial = "resources/fondo3.png";
    protected BufferedImage fondo;
    protected Application application;

    /**
     * @param application la instancia de la clase principal Application
     */
    public Screen(Application application) {
        setPreferredSize(new Dimension(Application.WIDTH, Application.HEIGHT));
        this.application = application;
        prepareElementos();
        prepareAccionesElemento();
    }

    /**
     * Establece el fondo de la pantalla
     */
    protected void setFondo() {
        fondo = application.getApplicationManager().getResourceManager().getSprite(fondoInicial);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Genera todos los elementos de la pantalla
     */
    protected abstract void prepareElementos();

    /**
     * Establece las acciones para todos los elementos que las necesiten
     */
    protected abstract void prepareAccionesElemento();
}

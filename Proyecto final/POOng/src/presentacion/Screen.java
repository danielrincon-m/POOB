package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Screen extends JPanel {

    protected String fondoInicial = "resources/fondo3.png";
    protected BufferedImage fondo;
    protected Application application;

    public Screen(Application application) {
        setPreferredSize(new Dimension(Application.WIDTH, Application.HEIGHT));
        this.application = application;
        prepareElements();
        prepareAccionesElemento();
    }

    protected void setFondo() {
        fondo = application.getApplicationManager().getResourceManager().getSprite(fondoInicial);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    protected abstract void prepareElements();

    protected abstract void prepareAccionesElemento();
}

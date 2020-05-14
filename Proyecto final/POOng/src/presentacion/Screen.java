package presentacion;

import aplicacion.ApplicationManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Screen extends JPanel {

    protected String fondoInicial = "/resources/fondo3.png";
    protected BufferedImage fondo;
    protected Application application;

    public Screen(Application application) {
        setPreferredSize(new Dimension(Application.WIDTH, Application.HEIGHT));
        this.application = application;
        prepareElements();
        prepareAccionesElemento();
    }

    protected void setFondo(String inical) {
        try {
            fondo = ImageIO.read(getClass().getResource(fondoInicial));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    protected abstract void prepareElements();

    protected abstract void prepareAccionesElemento();
}

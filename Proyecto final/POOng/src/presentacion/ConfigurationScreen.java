package presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConfigurationScreen extends Screen{
    public static final String fondoInicial = "recursos/fondo2.png";
    private BufferedImage fondo;

    public ConfigurationScreen(){
        super();
    }

    private void setFondo(String inical) {
        try {
            fondo = ImageIO.read(new File(inical));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareElements() {
        setFondo(ConfigurationScreen.fondoInicial);
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(300, 150, 250, 150), new TitledBorder("configuración")));
        JPanel datos = new JPanel();
        datos.setLayout(new GridLayout(2, 2, 10, 30));
        JLabel etiquetaPuntaje = new JLabel("Puntaje máximo:");
        //datos.setBackground();
        //datos.setOpaque(true);
        SpinnerNumberModel model = new SpinnerNumberModel(1,1,10,1);
        JSpinner opcionesPuntaje = new JSpinner(model);
        JLabel etiquetabola = new JLabel("Tipo de pelóta:");
        JComboBox opcionesBola = new JComboBox();
        opcionesBola.addItem("Normal");
        opcionesBola.addItem("Lenta");
        opcionesBola.addItem("Rápida");
        opcionesBola.addItem("Incremental");
        datos.add(etiquetaPuntaje);
        datos.add(opcionesPuntaje);
        datos.add(etiquetabola);
        datos.add(opcionesBola);
        add(datos);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0,getWidth(),getHeight(),this);
    }
}

package presentacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterProperties;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConfigurationScreen extends Screen {
    private BallType ballType;
    public static final String fondoInicial = "resources/fondo2.png";
    private BufferedImage fondo;
    private JButton atras;
    private JPanel datos1, datos2;
    private SpinnerNumberModel model;
    private JSpinner opcionesPuntaje;
    private JLabel etiquetabola, etiquetaPuntaje;
    private JComboBox opcionesBola;

    public ConfigurationScreen(Application application) {
        super(application);
    }

    private void setFondo(String inical) {
        try {
            fondo = ImageIO.read(new File(inical));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareElements() {
        setFondo(ConfigurationScreen.fondoInicial);
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(350, 250, 280, 250), new TitledBorder("configuración")));

        setLayout(new GridLayout(3, 2, 10, 10));
        etiquetaPuntaje = new JLabel("Puntaje máximo:");
        datos1 = new JPanel(new GridLayout(1, 2, 10, 40));
        datos1.setBorder(new EmptyBorder(5, 40, 5, 40));
        datos2 = new JPanel(new GridLayout(1, 2, 10, 30));
        datos2.setBorder(new EmptyBorder(5, 40, 5, 40));
        //datos.setBackground();
        //datos.setOpaque(true);
        model = new SpinnerNumberModel(1, 1, 10, 1);
        opcionesPuntaje = new JSpinner(model);
        etiquetabola = new JLabel("Tipo de pelóta:");
        atras = new JButton("Atrás");
        opcionesBola = new JComboBox();
        //opcionesBola.addItem("Normal");
        //opcionesBola.addItem("Lenta");
        //opcionesBola.addItem("Rápida");
        //opcionesBola.addItem("Incremental");
        tipoBola();
        datos1.add(etiquetaPuntaje);
        datos1.add(opcionesPuntaje);
        datos2.add(etiquetabola);
        datos2.add(opcionesBola);
        add(datos1);
        add(datos2);
        add(atras);

    }

    private void tipoBola(){
        for (BallType ball: BallType.values()) {
            opcionesBola.addItem(ball);
            }
        }

    private void acciones(BallType nombre){
        System.out.println(nombre);

    }
    @Override
    protected void prepareAccionesElemento() {
        atras.addActionListener(e -> application.pantallaPrincipal());

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

}

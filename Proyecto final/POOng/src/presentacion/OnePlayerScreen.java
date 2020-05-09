package presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OnePlayerScreen extends Screen {
    public static final String fondoInicial = "resources/fondo2.png";
    private BufferedImage fondo;
    private JButton jugador1, jugador2,jugar,atras;
    private JLayeredPane panelJugarUno;
    private JPanel jugadores,maquina;
    private JLabel etiquetaMaquina;
    private JComboBox opcionesMaquina;

    public OnePlayerScreen(Application application) {
        super(application);
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
        setBorder(new EmptyBorder(350, 250, 280, 250));
        setLayout(new GridLayout(3, 1, 10, 10));
        jugadores =new JPanel(new GridLayout(1, 2, 10, 10));
        jugador1 = new JButton("Jugador");
        maquina =new JPanel(new GridLayout(1, 2, 10, 10));
        maquina.setBorder(new EmptyBorder(0, 10, 0, 0));
        etiquetaMaquina = new JLabel("Máquina");
        opcionesMaquina = new JComboBox();
        opcionesMaquina.addItem("Extreme");
        opcionesMaquina.addItem("Sniper");
        opcionesMaquina.addItem("Greedy");
        opcionesMaquina.addItem("Lazy");
        //jugador2 = new JButton();
        jugar =new JButton("Jugar");
        atras = new JButton("Atrás");
        maquina.add(etiquetaMaquina);
        maquina.add(opcionesMaquina);
        //jugador2.add(maquina);
        jugadores.add(jugador1);
        jugadores.add(maquina);
        add(jugadores);
        add(jugar);
        add(atras);

    }

    @Override
    protected void  prepareAccionesElemento() {
        jugar.addActionListener(e -> application.irAlaSiguientePantalla("game"));
        atras.addActionListener(e -> application.pantallaPrincipal());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0,getWidth(),getHeight(),this);
    }
}

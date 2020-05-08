package presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayersScreen extends Screen{
    private  Application application;
    private JButton jugador1, jugador2,jugar,atras;
    private JPanel jugadores;
    public static final String fondoInicial = "resources/fondo2.png";
    private BufferedImage fondo;
    public   PlayersScreen(Application application){
        super();
        this.application= application;
        prepareAccionesElemento();
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
        jugador1 = new JButton("Jugador 1");
        jugador2 = new JButton("Jugador 2");
        jugar =new JButton("Jugar");
        atras = new JButton("Atrás");
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        add(jugadores);
        add(jugar);
        add(atras);


    }
    private void  prepareAccionesElemento() {
        jugar.addActionListener(e -> application.irAlaSiguientePantalla("game"));
        atras.addActionListener(e -> application.pantallaPrincipal());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0,getWidth(),getHeight(),this);
    }
}

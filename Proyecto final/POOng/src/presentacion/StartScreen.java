package presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class StartScreen extends Screen {
    private  Application application;
    public static final String fondoInicial = "resources/fondo1.png";
    private BufferedImage fondo;
    private JButton dosJugadores, unJugador,pc,opciones,cerrar,atras;
    private JPanel terceraFila,cuartaFila;

    public StartScreen(Application application) {
        super();
        this.application= application;
        prepareAccionesElemto();

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
        setFondo(StartScreen.fondoInicial);

        setBorder(new EmptyBorder(350, 230, 230, 230));
        setLayout(new GridLayout(4, 1, 10, 10));
        dosJugadores = new JButton("Jugador VS Jugador");
        unJugador = new JButton("Jugador VS Maquina");
        pc = new JButton("Máquina VS Máquina");
        opciones = new JButton("Configuración de juego");
        cerrar = new JButton("Cerrar POOng");

        terceraFila = new JPanel(new GridLayout(1, 1, 10, 10));
        cuartaFila = new JPanel(new GridLayout(1, 2, 10, 10));
        terceraFila.add(pc);
        cuartaFila.add(opciones);
        cuartaFila.add(cerrar);

        add(dosJugadores);
        add(unJugador);
        add(terceraFila);
        add(cuartaFila);
    }


    private void prepareAccionesElemto(){
        cerrar.addActionListener(e -> cerra());
        opciones.addActionListener(e -> application.irAlaSiguientePantalla("cc"));
        dosJugadores.addActionListener(e -> application.irAlaSiguientePantalla("jj"));
        unJugador.addActionListener(e -> application.irAlaSiguientePantalla("jm"));
        pc.addActionListener(e -> application.irAlaSiguientePantalla("mm"));
    }


    public void cerra(){
        int option = JOptionPane.showConfirmDialog(null, "Desea cerrar Poong");
        if (option == 0) {
            System.exit(0);
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0,getWidth(),getHeight(),this);
    }

}

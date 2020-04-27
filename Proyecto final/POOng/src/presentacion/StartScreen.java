package presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class StartScreen extends Screen {
    public static final String fondoInicial = "recursos/fondo2.png";
    private BufferedImage fondo;
    JButton dosJugadores;
    JButton unJugador;
    JButton pc;
    JButton opciones;
    JButton cerrar;
    public StartScreen() {
        super();
        //prepareElemnts();
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
    protected void prepareElemnts() {
        setFondo(StartScreen.fondoInicial);
        setBorder(new EmptyBorder(300, 120, 200, 120));
        setLayout(new GridLayout(4, 1, 10, 10));
        dosJugadores = new JButton("Jugador VS Jugador");
        unJugador = new JButton("Jugador VS Maquina");
        pc = new JButton("Máquina VS Máquina");
        opciones = new JButton("Configuración de juego");
        cerrar = new JButton("Cerrar POOng");
        JPanel terceraFila = new JPanel(new GridLayout(1, 1, 10, 10));
        JPanel cuartaFila = new JPanel(new GridLayout(1, 2, 10, 10));

        terceraFila.add(pc);
        cuartaFila.add(opciones);
        cuartaFila.add(cerrar);

        add(dosJugadores);
        add(unJugador);
        add(terceraFila);
        add(cuartaFila);
    }
    private void prepareAccionesElemto(){
        opciones.addActionListener(e -> configuracion());
        cerrar.addActionListener(e -> cerra());
    }

    private void configuracion(){
        //setLayout(new CardLayout(10, 10));
        System.out.println("ffffff");
        add(new ConfigurationScreen());
    }
    public void cerra(){
        System.out.println("sww");
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

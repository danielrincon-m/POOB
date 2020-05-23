package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Clase encargada de la pantalla principal del juego
 */
public class StartScreen extends Screen {

    private JButton dosJugadores, unJugador, pc, opciones, cerrar, atras;
    private JPanel terceraFila, cuartaFila;


    /**
     * @param application la instancia de la clase principal Application
     */
    public StartScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElementos() {
        fondoInicial = "resources/fondo1.png";
        setFondo();
        setBorder(new EmptyBorder(320, 230, 230, 230));
        setLayout(new GridLayout(4, 1, 10, 10));
        dosJugadores = new JButton("Jugador VS Jugador ");
        unJugador = new JButton("Jugador VS Maquina");
        pc = new JButton("Máquina VS Máquina");
        opciones = new JButton("Configuración de juego");
        cerrar = new JButton("Cerrar POOng");

        terceraFila = new JPanel(new GridLayout(1, 1, 10, 10));
        cuartaFila = new JPanel(new GridLayout(1, 2, 10, 10));
        terceraFila.add(pc);
        cuartaFila.add(opciones);
        cuartaFila.add(cerrar);
        cuartaFila.setOpaque(false);

        add(dosJugadores);
        add(unJugador);
        add(terceraFila);
        add(cuartaFila);
    }

    @Override
    protected void prepareAccionesElemento() {
        cerrar.addActionListener(e -> application.cerrar());
        opciones.addActionListener(e -> application.irAlaPantalla("Configuracion"));
        dosJugadores.addActionListener(e -> application.irAlaPantalla("Jugador vs Jugador"));
        unJugador.addActionListener(e -> application.prepareJugadorVsMaquina());
        pc.addActionListener(e -> application.prepareMaquinaVsMaquina());
    }
}

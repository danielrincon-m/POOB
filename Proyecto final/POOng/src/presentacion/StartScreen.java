package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartScreen extends Screen {
    public StartScreen() {
        super();
    }

    @Override
    protected void prepareElemnts() {
        setBorder(new EmptyBorder(50, 100, 50, 100));
        setLayout(new GridLayout(4, 1, 30, 30));
        JButton unJugador = new JButton("Un jugador");
        JButton multijugador = new JButton("Multijugador");
        JButton mods = new JButton("Mods");
        JButton realms = new JButton("Minecraft Realms");
        JButton opciones = new JButton("Opciones...");
        JButton cerrar = new JButton("Cerrar Minecraft");
        JPanel terceraFila = new JPanel(new GridLayout(1, 2, 30, 30));
        JPanel cuartaFila = new JPanel(new GridLayout(1, 2, 30, 30));

        terceraFila.add(mods);
        terceraFila.add(realms);
        cuartaFila.add(opciones);
        cuartaFila.add(cerrar);

        add(unJugador);
        add(multijugador);
        add(terceraFila);
        add(cuartaFila);
    }
}

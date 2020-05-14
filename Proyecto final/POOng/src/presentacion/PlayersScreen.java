package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayersScreen extends Screen{
    private JButton jugador1, jugador2,jugar,atras;
    private JPanel jugadores;


    public PlayersScreen(Application application){
        super(application);
    }


    @Override
    protected void prepareElements() {
        fondoInicial = "resources/fondo2.png";
        setFondo();
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

    @Override
    protected void  prepareAccionesElemento() {
        //jugar.addActionListener(e -> application.irAlaSiguientePantalla("game"));
        jugador1.addActionListener(e -> application.prepareJugador(0,"jvsj"));
        jugador2.addActionListener(e -> application.prepareJugador(1,"jvsj"));
        atras.addActionListener(e -> application.pantallaPrincipal());
        jugar.addActionListener(e -> application.iniciarjuego());
    }


}

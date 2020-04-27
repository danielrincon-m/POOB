package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayersScreen extends Screen{
    public   PlayersScreen(){super();}
    @Override
    protected void prepareElemnts() {
        setBorder(new EmptyBorder(310, 120, 300, 120));
        setLayout(new GridLayout(1, 2, 10, 10));
        JButton jugador1 = new JButton("Jugador 1");
        JButton jugador2 = new JButton("Jugador 2");
        add(jugador1);
        add(jugador2);
    }


}

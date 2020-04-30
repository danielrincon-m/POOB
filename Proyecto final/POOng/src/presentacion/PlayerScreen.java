package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayerScreen extends Screen {
    private ImageIcon score;
    private JLabel labelHit;
    private JLayeredPane panelJugarUno;

    public PlayerScreen() {
        super();
    }

    @Override
    protected void prepareElemnts() {
        setBorder(new EmptyBorder(300, 150, 200, 120));
        setLayout(new GridLayout(1, 1, 10, 10));
        JPanel primeraFila = new JPanel(new GridLayout(1, 3, 10, 10));
        JLabel superHeroe = new JLabel("1");
        JLabel terro = new JLabel("2");
        JLabel otro = new JLabel("3");
        primeraFila.add(superHeroe);
        primeraFila.add(terro);
        primeraFila.add(otro);
        add(primeraFila);

    }

    public void prepareLetreroScore() {
        score = new ImageIcon("recursos/fondo1.png");
        ImageIcon fin = new ImageIcon(score.getImage().getScaledInstance(100, 15, Image.SCALE_SMOOTH));
        labelHit = new JLabel(fin);
        labelHit.setBounds(650, 495, 100, 15);
        panelJugarUno.add(labelHit,2);
        add(panelJugarUno);
    }
}

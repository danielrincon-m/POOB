package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CharactersScreen extends Screen {
    private JRadioButton opciones;
    private JLabel personajes;
    private ImageIcon imagen;
    public  CharactersScreen(){
        super();
    }

    @Override
    protected void prepareElements() {
        setBorder(new EmptyBorder(350, 250, 280, 250));
        setLayout(new GridLayout(1, 2, 10, 10));
        //personajes = new JLabel("harry");
        imagen = new ImageIcon("resources/fondo2.png");
        opciones = new JRadioButton("harry",imagen);

    }


}

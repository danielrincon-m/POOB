package presentacion;

import aplicacion.game.enums.CharacterPersonality;

import aplicacion.game.enums.CharacterType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class CharactersScreen extends Screen {
    public static final String fondoInicial = "resources/fondo3.png";
    private BufferedImage fondo;
    private CharacterPersonality characterProperties;
    private ButtonGroup botones;
    private JRadioButton opciones,q,e,r,b;
    private JLabel personajes;
    private ImageIcon imagen;
    private JPanel seleccion, imagenes;
    private String name1;
    private Button aceptar,atras;

    public CharactersScreen(Application application) {
        super(application);
        //acciones();
    }

    private void setFondo(String inical) {
        try {
            fondo = ImageIO.read(new File(inical));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareElements() {
        setFondo(CharactersScreen.fondoInicial);
        setBorder(new EmptyBorder(200, 200, 280, 100));
        setLayout(new GridLayout(1, 2, 10, 10));
        seleccion = new JPanel();
        seleccion.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 0, 0),new TitledBorder("Selección de personaje")));
        seleccion.setLayout(new GridLayout(0, 1, 5, 5));
        seleccion.setBackground(new Color(238,255,254));
        imagenes = new JPanel();
        imagenes.setOpaque(false);
        imagenes.setBorder(new EmptyBorder(40, 5, 0, 5));
        botones = new ButtonGroup();
        aceptar = new Button("Aceptar");
        atras = new Button("atrás");
        jugador();
        add(seleccion);
        add(imagenes);
        //add(aceptar);
        //add(atras);
    }

    private  void  acciones(CharacterPersonality playerCharacter){

                imagen = new ImageIcon(playerCharacter.spritePath());
                Image captura = imagen.getImage();
                Image newimg = captura.getScaledInstance(220,220,Image.SCALE_DEFAULT);
                imagen = new ImageIcon(newimg);
                personajes = new JLabel (imagen);
                imagenes.removeAll();
                imagenes.add(personajes);
                add(imagenes);
                revalidate();
                repaint();

    }



    private void  jugador(){
        for (CharacterPersonality playerCharacter : CharacterPersonality.values()) {
            if (playerCharacter.getType().equals(CharacterType.HUMAN)) {
                JRadioButton name = new JRadioButton (playerCharacter.getName());
                name.setBackground(new Color(91,183,197));
                name.addActionListener(e1 -> acciones(playerCharacter));
                botones.add(name);
                seleccion.add(name);
            }
        }
    }

    @Override
    protected void prepareAccionesElemento() {

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

}

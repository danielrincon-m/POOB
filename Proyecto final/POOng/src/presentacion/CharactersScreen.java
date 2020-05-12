package presentacion;

import aplicacion.ApplicationManager;
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
    private TitledBorder titulo;

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
        titulo = new TitledBorder("Selección de personaje");
        titulo.setTitleColor(Color.white);
        seleccion.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),titulo));
        seleccion.setLayout(new GridLayout(0, 1, 5, 5));
        seleccion.setBackground(new Color(238,255,254));
        seleccion.setOpaque(false);
        imagenes = new JPanel();
        imagenes.setOpaque(false);
        imagenes.setBorder(new EmptyBorder(40, 5, 0, 5));
        botones = new ButtonGroup();
        aceptar = new Button("Aceptar");
        aceptar.setBackground(new Color(130,218,245));
        atras = new Button("atrás");
        atras.setBackground(new Color(150,200,245));
        jugador();
        seleccion.add(aceptar);
        seleccion.add(atras);
        add(seleccion);
        add(imagenes);
}

    private  void acciones(CharacterPersonality playerCharacter){
                characterProperties = playerCharacter;
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
                name.setBackground(new Color(150,162,255));
                name.addActionListener(e1 -> acciones(playerCharacter));
                //name.setOpaque(false);
                botones.add(name);
                seleccion.add(name);
            }
        }
    }


    private void test(){
        System.out.println(characterProperties.getName());
        //application.accionJugador(characterProperties);
    }


    @Override
    protected void prepareAccionesElemento() {
        aceptar.addActionListener(e1 -> test());

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

}

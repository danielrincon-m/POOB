package presentacion;

import aplicacion.game.enums.CharacterProperties;
import aplicacion.game.enums.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Enumeration;

public class CharactersScreen extends Screen {
    private CharacterProperties characterProperties;
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

    @Override
    protected void prepareElements() {
        setBorder(new EmptyBorder(200, 200, 280, 250));
        setLayout(new GridLayout(1, 2, 10, 10));
        seleccion = new JPanel();
        seleccion.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 0, 0),new TitledBorder("Selección de personaje")));
        seleccion.setLayout(new GridLayout(0, 1, 5, 5));
        imagenes = new JPanel();
        imagenes.setBorder(new EmptyBorder(10, 60, 0, 20));
        //imagen = new ImageIcon("resources/fondo1");
        //personajes = new JLabel(imagen);
        //imagenes.add(personajes);
        botones = new ButtonGroup();
        aceptar = new Button("Aceptar");
        atras = new Button("atrás");
        jugador();
        add(seleccion);
        add(imagenes);
        //add(aceptar);
        //add(atras);
        //System.out.println(botones.getButtonCount());
        //System.out.println(botones.getElements());
    }

    private  void  acciones(CharacterProperties playerCharacter){

                imagen = new ImageIcon(playerCharacter.spritePath());
                personajes = new JLabel (imagen);
                imagenes.removeAll();
                imagenes.add(personajes);
                add(imagenes);
                revalidate();

    }
    @Override
    protected  void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawOval(20,20,40,40);
    }

    private void  jugador(){
        for (CharacterProperties playerCharacter : CharacterProperties.values()) {
            if (playerCharacter.getType().equals(CharacterType.HUMAN)) {
                JRadioButton name = new JRadioButton (playerCharacter.getName());
                name.addActionListener(e1 -> acciones(playerCharacter));
                botones.add(name);
                seleccion.add(name);
            }
        }
    }

    /**
    public static  JRadioButton getSelection(ButtonGroup group){
        System.out.println("si");
        for (Enumeration  e = group.getElements(); e.hasMoreElements(); )
        {
            JRadioButton b = (JRadioButton)e.nextElement();
            if (b.getModel() == group.getSelection())
            {
                System.out.println("1");
                System.out.println(b);
                return b;
            }
        }
        System.out.println("2");
        return null;

    }
     */
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getName();
            }
        }

        return null;
    }
    @Override
    protected void prepareAccionesElemento() {

    }
}

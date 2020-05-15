package presentacion;

import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CharactersScreen extends Screen {
    private CharacterPersonality characterProperties;
    private ButtonGroup botones;
    private JLabel personajes;
    private ImageIcon imagen;
    private JPanel seleccion, imagenes;
    private String name1;
    private Button aceptar, atras;
    private TitledBorder titulo;
    private int idJugador;
    private String tipoDeJuego;

    public CharactersScreen(Application application) {
        super(application);
        //idJugador=id;
        //acciones();
    }

    public void setId(int id) {
        idJugador = id;
    }

    public void setTipoDeJuego(String tipo) {
        tipoDeJuego = tipo;
    }


    @Override
    protected void prepareElements() {
        setFondo();
        setBorder(new EmptyBorder(200, 200, 280, 100));
        setLayout(new GridLayout(1, 2, 10, 10));
        seleccion = new JPanel();
        titulo = new TitledBorder("Selección de personaje");
        titulo.setTitleColor(Color.white);
        seleccion.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10), titulo));
        seleccion.setLayout(new GridLayout(0, 1, 5, 5));
        seleccion.setBackground(new Color(238, 255, 254));
        seleccion.setOpaque(false);
        imagenes = new JPanel();
        imagenes.setOpaque(false);
        imagenes.setBorder(new EmptyBorder(40, 5, 0, 5));
        botones = new ButtonGroup();
        aceptar = new Button("Aceptar");
        aceptar.setBackground(new Color(130, 218, 245));
        atras = new Button("atrás");
        atras.setBackground(new Color(150, 200, 245));
        prepareJugador();
        seleccion.add(aceptar);
        seleccion.add(atras);
        add(seleccion);
        add(imagenes);
    }

    private int getRival(){
        if(idJugador==0){
            return 2;
        }
        else{
            return 1;
        }
    }

    private void jugadorSeleccionado(CharacterPersonality playerCharacter) {
        characterProperties = playerCharacter;
        BufferedImage captura = application.getApplicationManager().getResourceManager().getPlayerImage(playerCharacter);
        Image newimg = captura.getScaledInstance(220, 220, Image.SCALE_DEFAULT);
        imagen = new ImageIcon(newimg);
        personajes = new JLabel(imagen);
        imagenes.removeAll();
        imagenes.add(personajes);
        add(imagenes);
        revalidate();
        repaint();
    }


    private void prepareJugador() {
        for (CharacterPersonality playerCharacter : CharacterPersonality.values()) {
            if (playerCharacter.getType().equals(CharacterType.HUMAN)) {
                JRadioButton name = new JRadioButton(playerCharacter.getName());
                name.setBackground(new Color(150, 162, 255));
                name.addActionListener(e1 -> jugadorSeleccionado(playerCharacter));
                //name.setOpaque(false);
                botones.add(name);
                seleccion.add(name);
            }
        }
    }


    private void prepareAccionesPersonaje() {
        if (application.verificarSiExisteJugador(characterProperties)) {
            application.getApplicationManager().getGameProperties().setCharacter(idJugador, characterProperties);
            application.irAlaSiguientePantalla("Jugador vs Jugador");


        }
        else{
            JOptionPane.showMessageDialog(this,"Este personaje ya fue selecionado por el jugador  "+getRival()+"\n       Seleccione otro porfavor!");
        }
    }


    @Override
    protected void prepareAccionesElemento() {
        aceptar.addActionListener(e -> prepareAccionAceptar());
        atras.addActionListener(e1 -> prepareAccionAtras());
    }



    private void prepareAccionAceptar() {
        if (tipoDeJuego == "jvsj") {
            prepareAccionesPersonaje();
        } else {
            application.getApplicationManager().getGameProperties().setCharacter(idJugador, characterProperties);
            application.irAlaSiguientePantalla("Jugador vs Maquina");
        }
    }

    private void prepareAccionAtras(){
        if(tipoDeJuego=="jvsj"){
            application.irAlaSiguientePantalla("Jugador vs Jugador");
        } else {
            application.irAlaSiguientePantalla("Jugador vs Maquina");
        }
    }


}

package presentacion;

import aplicacion.GameProperties;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.EnumSet;

public class CharactersScreen extends Screen {
    private CharacterPersonality characterProperties;
    private ButtonGroup botones;
    private JPanel seleccion, imagenes;
    private Button aceptar, atras;
    private int idJugador;
    private String tipoDeJuego;

    public CharactersScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElements() {
        setFondo();
        setBorder(new EmptyBorder(200, 200, 280, 100));
        setLayout(new GridLayout(1, 2, 10, 10));
        seleccion = new JPanel();
        TitledBorder titulo = new TitledBorder("Selección de personaje");
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
        add(seleccion);
        add(imagenes);
    }

    public void setId(int id) {
        idJugador = id;
    }

    public void setTipoDeJuego(String tipo) {
        tipoDeJuego = tipo;
    }

    public void calcularValoresPantalla() {
        seleccion.removeAll();
        imagenes.removeAll();
        botones = new ButtonGroup();
        characterProperties = null;
        prepareJugadores();
        seleccion.add(aceptar);
        seleccion.add(atras);
    }

    private void jugadorSeleccionado(CharacterPersonality playerCharacter) {
        imagenes.removeAll();
        if (playerCharacter != null) {
            characterProperties = playerCharacter;
            Image characterSprite = application.getApplicationManager().getResourceManager().
                    getPlayerImage(playerCharacter).
                    getScaledInstance(220, 220, Image.SCALE_DEFAULT);
            JLabel personajes = new JLabel(new ImageIcon(characterSprite));
            imagenes.add(personajes);
        }
        revalidate();
        repaint();
    }

    private JRadioButton addCharacterButton(CharacterPersonality playerCharacter) {
        JRadioButton name = new JRadioButton(playerCharacter.getName());
        name.setBackground(new Color(150, 162, 255));
        name.addActionListener(e1 -> jugadorSeleccionado(playerCharacter));
        botones.add(name);
        seleccion.add(name);
        return name;
    }

    private void prepareJugadores() {
        GameProperties gp = application.getApplicationManager().getGameProperties();
        CharacterPersonality[] selectedCharacters = gp.getSelectedCharacters();
        EnumSet<CharacterPersonality> availableCharacters = gp.getAvailablePlayerCharacters();

        if (selectedCharacters[idJugador] != null && selectedCharacters[idJugador].getType().equals(CharacterType.HUMAN)) {
            JRadioButton characterButton = addCharacterButton(selectedCharacters[idJugador]);
            characterButton.setSelected(true);
            jugadorSeleccionado(selectedCharacters[idJugador]);
        }
        for (CharacterPersonality playerCharacter : availableCharacters) {
            addCharacterButton(playerCharacter);
        }
    }

    @Override
    protected void prepareAccionesElemento() {
        aceptar.addActionListener(e -> accionAceptar());
        atras.addActionListener(e1 -> accionAtras());
    }

    private void accionAceptar() {
        if (tipoDeJuego.equals("jvsj")) {
            application.getApplicationManager().getGameProperties().setCharacter(idJugador, characterProperties);
            application.irAlaSiguientePantalla("Jugador vs Jugador");
        } else {
            application.getApplicationManager().getGameProperties().setCharacter(idJugador, characterProperties);
            application.irAlaSiguientePantalla("Jugador vs Maquina");
        }
    }

    private void accionAtras(){
        if(tipoDeJuego.equals("jvsj")){
            application.irAlaSiguientePantalla("Jugador vs Jugador");
        } else {
            application.irAlaSiguientePantalla("Jugador vs Maquina");
        }
    }


}

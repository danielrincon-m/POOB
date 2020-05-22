package presentacion;

import aplicacion.GameProperties;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.EnumSet;

/**
 * Pantalla en donde el jugador selecciona personaje
 */
public class CharactersScreen extends Screen {
    private CharacterPersonality characterProperties;
    private ButtonGroup botones;
    private JPanel seleccion, imagenes;
    private Button aceptar, atras;
    private int idJugador;
    private String tipoDeJuego;

    /**
     * @param application la instancia de la clase principal Application
     */
    public CharactersScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElementos() {
        setFondo();
        setBorder(new EmptyBorder(300, 90, 250, 100));
        setLayout(new GridLayout(1, 2, 60, 10));
        seleccion = new JPanel();
        TitledBorder titulo = new TitledBorder("Selección de personaje");
        titulo.setTitleColor(Color.white);
        seleccion.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10), titulo));
        seleccion.setLayout(new GridLayout(0, 1, 5, 5));
        seleccion.setBackground(new Color(238, 255, 254));
        seleccion.setBackground(new Color(219,254,204));
        seleccion.setOpaque(false);
        imagenes = new JPanel();
        imagenes.setOpaque(false);
        imagenes.setBorder(new EmptyBorder(40, 5, 0, 5));
        botones = new ButtonGroup();
        aceptar = new Button("Aceptar");
        aceptar.setBackground(new Color(164,222,167));
        atras = new Button("Atrás");
        atras.setBackground(new Color(165, 227, 212));
        add(seleccion);
        add(imagenes);
    }

    /**
     * Establece el id del jugador del cual se elegirá personaje
     *
     * @param id El id del jugador
     */
    public void setId(int id) {
        idJugador = id;
    }

    /**
     * Establece el tipo de juego para conocer a que pantalla se regresará
     *
     * @param tipo El tipo de juego (jvsj, jvsm)
     */
    public void setTipoDeJuego(String tipo) {
        tipoDeJuego = tipo;
    }

    /**
     * Reinicia todas las propiedades de la pantalla
     */
    public void reiniciarValoresPantalla() {
        seleccion.removeAll();
        imagenes.removeAll();
        botones = new ButtonGroup();
        characterProperties = null;
        prepareJugadores();
        seleccion.add(aceptar);
        seleccion.add(atras);
    }

    /**
     * Establece la imagen del jugador para que el usuario pueda visualizarla
     *
     * @param playerCharacter El personaje seleccionado
     */
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

    /**
     * Agrega un nuevo boton que corresponde a cierto personaje
     *
     * @param playerCharacter El personaje a establecer
     * @return El RadioButton
     */
    private JRadioButton addCharacterButton(CharacterPersonality playerCharacter) {
        JRadioButton name = new JRadioButton(playerCharacter.getName());
        //name.setBackground(new Color(219,254,204));
        name.addActionListener(e1 -> jugadorSeleccionado(playerCharacter));
        botones.add(name);
        seleccion.add(name);
        return name;
    }

    /**
     * Establece la selección de los botones para que correspondan a los jugadores seleccionados
     * en la parte de aplicación
     */
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

    /**
     * Cuando el usuario presiona el boton aceptar, se guarda la seleccion del jugador en aplicación,
     * y se regresa a la pantalla anterior
     */
    private void accionAceptar() {
        if (tipoDeJuego.equals("jvsj")) {
            application.getApplicationManager().getGameProperties().setCharacter(idJugador, characterProperties);
            application.irAlaPantalla("Jugador vs Jugador");
        } else {
            application.getApplicationManager().getGameProperties().setCharacter(idJugador, characterProperties);
            application.irAlaPantalla("Jugador vs Maquina");
        }
    }

    /**
     * Cuando el usuario presiona el boton salir, se regresa a la pantalla anterior
     * sin guardar la selección del jugador
     */
    private void accionAtras() {
        if (tipoDeJuego.equals("jvsj")) {
            application.irAlaPantalla("Jugador vs Jugador");
        } else {
            application.irAlaPantalla("Jugador vs Maquina");
        }
    }
}

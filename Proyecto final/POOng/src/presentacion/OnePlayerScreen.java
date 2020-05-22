package presentacion;

import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Clase encargada de la pantalla de Jugador vs Máquina
 */
public class OnePlayerScreen extends Screen implements ItemListener {
    private JButton jugador1, jugar, atras;
    private JPanel jugadores, maquina;
    private JLabel etiquetaMaquina;
    private JComboBox<CharacterPersonality> opcionesMaquina2;

    /**
     * @param application la instancia de la clase principal Application
     */
    public OnePlayerScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElementos() {
        fondoInicial = "resources/fondo2.png";
        setFondo();
        setBorder(new EmptyBorder(350, 250, 280, 250));
        setLayout(new GridLayout(3, 1, 10, 10));
        jugadores = new JPanel(new GridLayout(1, 2, 10, 10));
        maquina = new JPanel(new GridLayout(1, 2, 10, 10));
        maquina.setBorder(new EmptyBorder(0, 10, 0, 0));
        jugador1 = new JButton("Jugador");
        etiquetaMaquina = new JLabel("Máquina");
        ;
        opcionesMaquina2 = new JComboBox<>();
        prepareMaquina();
        jugar = new JButton("Jugar");
        atras = new JButton("Atrás");
        maquina.add(etiquetaMaquina);
        maquina.add(opcionesMaquina2);
        jugadores.add(jugador1);
        jugadores.add(maquina);
        add(jugadores);
        add(jugar);
        add(atras);
    }

    /**
     * Prepara el comboBox de la máquina con todas las máquinas disponibles en aplicación
     */
    public void prepareMaquina() {
        for (CharacterPersonality machine : CharacterPersonality.values()) {
            if (machine.getType().equals(CharacterType.MACHINE)) {
                opcionesMaquina2.addItem(machine);
            }
        }
        opcionesMaquina2.addItemListener(this);
        //seleccionarMaquinaEnComboBox();
    }

    @Override
    protected void prepareAccionesElemento() {
        jugador1.addActionListener(e -> application.prepareJugador(1, "jvsm"));
        atras.addActionListener(e -> application.irAlaPantalla("inicio"));
        jugar.addActionListener(e -> application.iniciarjuego());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == opcionesMaquina2) {
            seleccionarMaquinaEnComboBox();
        }
    }

    /**
     * Selecciona la máquina en el combobox y la registra en aplicación
     */
    public void seleccionarMaquinaEnComboBox() {
        application.getApplicationManager().getGameProperties().setCharacter(0,
                (CharacterPersonality) opcionesMaquina2.getSelectedItem());
    }
}

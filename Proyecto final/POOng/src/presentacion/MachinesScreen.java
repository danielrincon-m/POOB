package presentacion;

import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Clase encargada de la pantalla de Máquina vs Máquina
 */
public class MachinesScreen extends Screen implements ItemListener {
    private JButton jugar, atras;
    private JPanel jugadores, maquina1, maquina2;
    private JLabel etiquetaMaquina1, etiquetaMaquina2;
    private JComboBox<CharacterPersonality> opcionesMaquina1, opcionesMaquina2;

    /**
     * @param application la instancia de la clase principal Application
     */
    public MachinesScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElementos() {
        fondoInicial = "resources/fondo2.png";
        setFondo();
        setBorder(new EmptyBorder(350, 250, 280, 250));
        setLayout(new GridLayout(3, 1, 10, 10));
        jugadores = new JPanel(new GridLayout(1, 2, 10, 10));
        maquina1 = new JPanel(new GridLayout(1, 2, 10, 10));
        maquina2 = new JPanel(new GridLayout(1, 2, 10, 10));
        etiquetaMaquina1 = new JLabel("Máquina 1");
        etiquetaMaquina2 = new JLabel("Máquina 2");
        opcionesMaquina1 = new JComboBox<>();
        opcionesMaquina2 = new JComboBox<>();
        prepareMaquina(opcionesMaquina1);
        prepareMaquina(opcionesMaquina2);
        jugar = new JButton("Jugar");
        atras = new JButton("Atrás");
        maquina1.add(etiquetaMaquina1);
        maquina1.add(opcionesMaquina1);
        maquina2.add(etiquetaMaquina2);
        maquina2.add(opcionesMaquina2);
        jugadores.add(maquina1);
        jugadores.add(maquina2);
        add(jugadores);
        add(jugar);
        add(atras);
        opcionesMaquina1.addItemListener(this);
        opcionesMaquina2.addItemListener(this);
    }

    /**
     * Prepara la lista de máquinas con las máquinas disponibles para ser seleccionada
     *
     * @param comboMaquina El combobox de las máquinas
     */
    private void prepareMaquina(JComboBox<CharacterPersonality> comboMaquina) {
        for (CharacterPersonality machine : CharacterPersonality.values()) {
            if (machine.getType().equals(CharacterType.MACHINE)) {
                comboMaquina.addItem(machine);
            }
        }
        //seleccionarMaquinasEnComboBox();
    }

    @Override
    protected void prepareAccionesElemento() {
        jugar.addActionListener(e -> application.iniciarjuego());
        atras.addActionListener(e -> application.irAlaPantalla("inicio"));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == opcionesMaquina1 || e.getSource() == opcionesMaquina2) {
            seleccionarMaquinasEnComboBox();
        }
    }

    /**
     * Selecciona en aplicacion las máquinas que se encuentran actualmente en el comboBox
     */
    public void seleccionarMaquinasEnComboBox() {
        application.getApplicationManager().getGameProperties().setCharacter(0,
                (CharacterPersonality) opcionesMaquina1.getSelectedItem());
        application.getApplicationManager().getGameProperties().setCharacter(1,
                (CharacterPersonality) opcionesMaquina2.getSelectedItem());
    }
}


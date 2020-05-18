package presentacion;

import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OnePlayerScreen extends Screen implements ItemListener {
    private JButton jugador1, jugador2,jugar,atras;
    private JLayeredPane panelJugarUno;
    private JPanel jugadores,maquina;
    private JLabel etiquetaMaquina;
    private JComboBox opcionesMaquina2;

    public OnePlayerScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElements() {
        fondoInicial = "resources/fondo2.png";
        setFondo();
        setBorder(new EmptyBorder(350, 250, 280, 250));
        setLayout(new GridLayout(3, 1, 10, 10));
        jugadores =new JPanel(new GridLayout(1, 2, 10, 10));
        jugador1 = new JButton("Jugador");
        maquina =new JPanel(new GridLayout(1, 2, 10, 10));
        maquina.setBorder(new EmptyBorder(0, 10, 0, 0));
        etiquetaMaquina = new JLabel("Máquina");
        opcionesMaquina2 = new JComboBox();
        prepareMaquina(opcionesMaquina2);
        jugar =new JButton("Jugar");
        atras = new JButton("Atrás");
        maquina.add(etiquetaMaquina);
        maquina.add(opcionesMaquina2);
        jugadores.add(jugador1);
        jugadores.add(maquina);
        add(jugadores);
        add(jugar);
        add(atras);
        opcionesMaquina2.addItemListener(this);

    }

    public void prepareMaquina(JComboBox comboMaquina){
        for (CharacterPersonality machine : CharacterPersonality.values()) {
            if (machine.getType().equals(CharacterType.MACHINE)) {
                comboMaquina.addItem(machine);
            }
        }

    }
    @Override
    protected void  prepareAccionesElemento() {
        jugador1.addActionListener(e -> application.prepareJugador(0,"jvsm"));
        atras.addActionListener(e -> application.irAlaSiguientePantalla("inicio"));
        jugar.addActionListener(e -> application.iniciarjuego());
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource()==opcionesMaquina2) {
            application.getApplicationManager().getGameProperties().setCharacter(1,(CharacterPersonality) opcionesMaquina2.getSelectedItem());

        }
    }
}

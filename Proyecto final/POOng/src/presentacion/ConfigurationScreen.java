package presentacion;

import aplicacion.game.enums.BallType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Pantalla en donde se configuran las propiedades básicas del juego
 */
public class ConfigurationScreen extends Screen implements ItemListener {
    private JButton atras;
    private JPanel datos1, datos2;
    private SpinnerNumberModel model;
    private JSpinner opcionesPuntaje;
    private JLabel etiquetabola, etiquetaPuntaje;
    private JComboBox<BallType> opcionesBola;

    /**
     * @param application la instancia de la clase principal Application
     */
    public ConfigurationScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElementos() {
        fondoInicial = "resources/fondo2.png";
        setFondo();
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(350, 250, 280, 250), new TitledBorder("configuración")));

        setLayout(new GridLayout(3, 2, 10, 10));
        etiquetaPuntaje = new JLabel("Puntaje máximo:");
        datos1 = new JPanel(new GridLayout(1, 2, 10, 40));
        datos1.setBorder(new EmptyBorder(5, 40, 5, 40));
        datos2 = new JPanel(new GridLayout(1, 2, 10, 30));
        datos2.setBorder(new EmptyBorder(5, 40, 5, 40));
        model = new SpinnerNumberModel(10, 5, 100, 1);
        opcionesPuntaje = new JSpinner(model);
        etiquetabola = new JLabel("Tipo de pelóta:");
        atras = new JButton("Aceptar");
        opcionesBola = new JComboBox<>();
        agregarBolas();
        datos1.add(etiquetaPuntaje);
        datos1.add(opcionesPuntaje);
        datos2.add(etiquetabola);
        datos2.add(opcionesBola);
        add(datos1);
        add(datos2);
        add(atras);
        opcionesBola.addItemListener(this);
        model.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                application.getApplicationManager().getGameProperties().setMaxScore((Integer) model.getValue());
            }
        });

    }

    /**
     * Agrega las posibilidades a los botones de seleccion de bolas y selecciona la opcion actual seleccionada en aplicación
     */
    private void agregarBolas() {
        for (BallType ball : BallType.values()) {
            opcionesBola.addItem(ball);
        }
        opcionesBola.setSelectedItem(BallType.FAST);
    }

    @Override
    protected void prepareAccionesElemento() {
        atras.addActionListener(e -> application.irAlaPantalla("inicio"));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == opcionesBola) {
            application.getApplicationManager().getGameProperties().setBall((BallType) opcionesBola.getSelectedItem());
        }
    }
}

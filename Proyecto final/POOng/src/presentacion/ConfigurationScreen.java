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

public class ConfigurationScreen extends Screen implements ItemListener {
    private BallType ballType;
    private JButton atras;
    private JPanel datos1, datos2;
    private SpinnerNumberModel model;
    private JSpinner opcionesPuntaje;
    private JLabel etiquetabola, etiquetaPuntaje;
    private JComboBox opcionesBola;

    public ConfigurationScreen(Application application) {
        super(application);
    }


    @Override
    protected void prepareElements() {
        fondoInicial = "resources/fondo2.png";
        setFondo();
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(350, 250, 280, 250), new TitledBorder("configuración")));

        setLayout(new GridLayout(3, 2, 10, 10));
        etiquetaPuntaje = new JLabel("Puntaje máximo:");
        datos1 = new JPanel(new GridLayout(1, 2, 10, 40));
        datos1.setBorder(new EmptyBorder(5, 40, 5, 40));
        datos2 = new JPanel(new GridLayout(1, 2, 10, 30));
        datos2.setBorder(new EmptyBorder(5, 40, 5, 40));
        model = new SpinnerNumberModel(1, 1, 10, 1);
        opcionesPuntaje = new JSpinner(model);
        etiquetabola = new JLabel("Tipo de pelóta:");
        atras = new JButton("Atrás");
        opcionesBola = new JComboBox();
        tipoBola();
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

    private void tipoBola() {
        for (BallType ball : BallType.values()) {
            opcionesBola.addItem(ball);
        }
    }


    @Override
    protected void prepareAccionesElemento() {
        atras.addActionListener(e -> application.pantallaPrincipal());

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == opcionesBola) {
            //String seleccionado1=(String)opcionesMaquina1.getSelectedItem();
            application.getApplicationManager().getGameProperties().setBall((BallType) opcionesBola.getSelectedItem());

        }

    }
}

package presentacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MarbelGameGUI extends JFrame {

    private int numCeldas = 4;

    //Panel principal
    private JPanel mainPanel;

    //elementos del menu
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem guardarComo;
    private JMenuItem salir;

    private JMenuItem cambiarColorFondo;
    private JMenuItem cambiarColorBorde;
    private JMenuItem cambiarNumCeldas;
    private JMenuItem cambiarNumAgujeros;
    private JMenuItem cambiarNumBarreras;

    //Propiedades tablero
    private Color backgroundColor = Color.WHITE;
    private Color borderColor = Color.BLACK;

    //Elementos tablero
    private JPanel tablero;
    private JPanel[][] casillitas;

    //Elementos informativos
    private JLabel infoMovimientos;
    private JLabel infoBienUbicadas;
    private JLabel infoMalUbicadas;

    private MarbelGameGUI() {
        setTitle("Marbel Game");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width / 2, dimension.height / 2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        prepareElementos();
        prepareAcciones();
    }

    private void prepareElementos() {
        prepareElementoMenu();
        mainPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(prepareAreaTablero());
        mainPanel.add(prepareAreaDatos());
        add(mainPanel);
    }

    private void prepareElementoMenu() {
        JMenuBar menuBarra = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        guardarComo = new JMenuItem("Guardar como");
        salir = new JMenuItem("Salir");

        JMenu configuracion = new JMenu("Configuracion");
        JMenu configColor = new JMenu("Configuracion de color");
        JMenu configJugabilidad = new JMenu("Configuracion de jugabilidad");
        cambiarColorBorde = new JMenuItem("Configurar color de borde...");
        cambiarColorFondo = new JMenuItem("Configurar color de fondo...");
        cambiarNumCeldas = new JMenuItem("Configurar número de celdas...");
        cambiarNumAgujeros = new JMenuItem("Configurar número de agujeros...");
        cambiarNumCeldas = new JMenuItem("Configurar número de Celdas...");
        cambiarNumBarreras = new JMenuItem("Configurar número de Barreras...");

        menuBarra.add(archivo);
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(guardarComo);
        archivo.add(salir);

        menuBarra.add(configuracion);
        configuracion.add(configColor);
        configColor.add(cambiarColorFondo);
        configColor.add(cambiarColorBorde);
        configuracion.add(configJugabilidad);
        configJugabilidad.add(cambiarNumCeldas);
        configJugabilidad.add(cambiarNumAgujeros);
        configJugabilidad.add(cambiarNumBarreras);

        setJMenuBar(menuBarra);
    }

    //private  JPanel prepareAreaImagen(){
    //return  null;
    //}
    private JPanel prepareAreaTablero() {
        casillitas = new JPanel[numCeldas][numCeldas];
        tablero = new JPanel();
        refresque();
        return tablero;
    }

    private JPanel prepareAreaDatos() {
        JPanel seccionDatos = new JPanel(new GridLayout(3, 2));
        JLabel movimientos = new JLabel("Movimientos", SwingConstants.CENTER);
        JLabel bienUbicadas = new JLabel("Bien Ubicadas", SwingConstants.CENTER);
        JLabel malUbicadas = new JLabel("Mal Ubicadas", SwingConstants.CENTER);
        infoMovimientos = new JLabel("-", SwingConstants.CENTER);
        infoBienUbicadas = new JLabel("-", SwingConstants.CENTER);
        infoMalUbicadas = new JLabel("-", SwingConstants.CENTER);
        int verticalPadding = (int) (getSize().height * 0.3);
        int horizontalPadding = (int) (getSize().width * 0.05);
        seccionDatos.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(verticalPadding, horizontalPadding, verticalPadding, horizontalPadding),
                new TitledBorder("Estadisticas")));
        seccionDatos.add(movimientos);
        seccionDatos.add(infoMovimientos);
        seccionDatos.add(bienUbicadas);
        seccionDatos.add(infoBienUbicadas);
        seccionDatos.add(malUbicadas);
        seccionDatos.add(infoMalUbicadas);
        return seccionDatos;
    }

    private void prepareAcciones() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                preguntaSalir();
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preguntaSalir();
            }
        });
        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionAbrir();
            }
        });
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionGuardar();
            }
        });

        cambiarColorFondo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                accionCambiarColor(true);
            }
        });

        cambiarColorBorde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                accionCambiarColor(false);
            }
        });
    }

    private void accionGuardar() {
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showSaveDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String name = archivo.getName();
            JOptionPane.showMessageDialog(this, "Esta funcionalidad está en construcción," +
                    " usted intentando guardar el archivo " + name);
        }

    }

    private void accionAbrir() {
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            String name = archivo.getName();
            JOptionPane.showMessageDialog(this, "Esta funcionalidad está en construcción," +
                    " usted intententó abrir el archivo " + name);
        }
    }

    private void accionCambiarColor(boolean fondo) {
        Color color;
        if(fondo) {
            color = JColorChooser.showDialog(this, "Elija el color de fondo", backgroundColor);
        }else {
            color = JColorChooser.showDialog(this, "Elija el color de borde", borderColor);
        }
        if (color != null) {
            if (fondo) {
                backgroundColor = color;
            }else {
                borderColor = color;
            }
            colorear();
        }
    }

    private void preguntaSalir() {
        int option;
        option = JOptionPane.showConfirmDialog(null, "desea cerrar la aplicación");
        if (option == 0) {
            System.exit(0);
        }
    }

    private void colorear() {
        for (int i = 0; i < numCeldas; i++) {
            for (int j = 0; j < numCeldas; j++) {
                casillitas[i][j].setBackground(backgroundColor);
                casillitas[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
            }
        }
    }

    private void refresque() {
        tablero.setLayout(new GridLayout(numCeldas, numCeldas));
        for (int i = 0; i < numCeldas; i++) {
            for (int j = 0; j < numCeldas; j++) {
                JPanel cell = new JPanel();
                cell.setBackground(backgroundColor);
                cell.setBorder(BorderFactory.createLineBorder(borderColor));
                tablero.add(cell);
                casillitas[i][j] = cell;
            }
        }
    }

    public static void main(String[] args) {
        MarbelGameGUI gui = new MarbelGameGUI();
        gui.setVisible(true);
    }
}


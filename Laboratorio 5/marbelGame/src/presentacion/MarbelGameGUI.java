package presentacion;

import aplicacion.MarbelGameBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class MarbelGameGUI extends JFrame implements KeyListener {

    private int numCeldas = 4;
    private int numBarreras = 1;
    private int numAgujeros = 3;

    //Tablero de juego
    MarbelGameBoard tableroLogico;

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
    private Casilla[][] casillitas;

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

        tableroLogico = new MarbelGameBoard(numCeldas, numBarreras, numAgujeros);
        prepareElementos();
        prepareAcciones();
    }

    private void prepareElementos() {
        prepareElementoMenu();
        //Panel principal
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 20));
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
        tablero = new JPanel();
        construirTablero();
        return tablero;
    }

    private JPanel prepareAreaDatos() {
        JPanel seccionDatos = new JPanel(new GridLayout(3, 2));
        JLabel movimientos = new JLabel("Movimientos", SwingConstants.CENTER);
        JLabel bienUbicadas = new JLabel("Bien Ubicadas", SwingConstants.CENTER);
        JLabel malUbicadas = new JLabel("Mal Ubicadas", SwingConstants.CENTER);
        infoMovimientos = new JLabel("0", SwingConstants.CENTER);
        infoBienUbicadas = new JLabel("0", SwingConstants.CENTER);
        infoMalUbicadas = new JLabel("0", SwingConstants.CENTER);
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
        addKeyListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                preguntaSalir();
            }
        });

        nuevo.addActionListener(actionEvent -> accionReiniciar());
        salir.addActionListener(e -> preguntaSalir());
        abrir.addActionListener(e -> accionAbrir());
        guardar.addActionListener(e -> accionGuardar());
        guardarComo.addActionListener(actionEvent -> accionGuardar());

        cambiarColorFondo.addActionListener(actionEvent -> accionCambiarColor(true));
        cambiarColorBorde.addActionListener(actionEvent -> accionCambiarColor(false));
        cambiarNumCeldas.addActionListener(actionEvent -> accionCambiarNumCeldas());
        cambiarNumAgujeros.addActionListener(actionEvent -> accionCambiarNumAgujeros());
        cambiarNumBarreras.addActionListener(actionEvent -> accionCambiarNumBarreras());
    }

    private void accionReiniciar() {
        tableroLogico.reiniciar();
        refresque();
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

    private void accionCambiarNumCeldas() {
        String nCeldas = JOptionPane.showInputDialog(this, "Digite el nuevo número de celdas");
        if (nCeldas != null) {
            try {
                numCeldas = Integer.parseInt(nCeldas);
                tableroLogico.setnCeldas(numCeldas);
                tablero.removeAll();
                tablero.revalidate();
                tablero.repaint();
                construirTablero();
                refresque();
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El número de agujeros debe ser un número :p");
            }
        }
    }

    private void accionCambiarNumAgujeros() {
        String nAgujeros = JOptionPane.showInputDialog(this, "Digite el nuevo número de agujeros");
        if (nAgujeros != null) {
            try {
                int n = Integer.parseInt(nAgujeros);
                tableroLogico.setnAgujeros(n);
                refresque();
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El número de agujeros debe ser un número :p");
            }catch (UnsupportedOperationException e) {
                JOptionPane.showMessageDialog(this, "La cantidad de elementos es mayor al número de casillas");
            }
        }
    }

    private void accionCambiarNumBarreras() {
        String nBarreras = JOptionPane.showInputDialog(this, "Digite el nuevo número de barreras");
        if (nBarreras != null) {
            try {
                int n = Integer.parseInt(nBarreras);
                tableroLogico.setnBarreras(n);
                refresque();
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El número de barreras debe ser un número :p");
            }catch (UnsupportedOperationException e) {
                JOptionPane.showMessageDialog(this, "La cantidad de elementos es mayor al número de casillas");
            }
        }
    }

    private void preguntaSalir() {
        int option;
        option = JOptionPane.showConfirmDialog(null, "desea cerrar la aplicación");
        if (option == 0) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT:
                tableroLogico.realizarMovimiento("este");
                break;
            case KeyEvent.VK_LEFT:
                tableroLogico.realizarMovimiento("oeste");
                break;
            case KeyEvent.VK_UP:
                tableroLogico.realizarMovimiento("norte");
                break;
            case KeyEvent.VK_DOWN:
                tableroLogico.realizarMovimiento("sur");
                break;
        }
        refresque();
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
        for (int i = 0; i < numCeldas; i++) {
            for (int j = 0; j < numCeldas; j++) {
                casillitas[i][j].actualizar(tableroLogico.elementoEn(i, j));
            }
        }
        infoMovimientos.setText(Integer.toString(tableroLogico.getnMovimientos()));
        infoBienUbicadas.setText(Integer.toString(tableroLogico.getUbicada(true)));
        infoMalUbicadas.setText(Integer.toString(tableroLogico.getUbicada(false)));
    }

    private void construirTablero() {
        casillitas = new Casilla[numCeldas][numCeldas];
        tablero.setLayout(new GridLayout(numCeldas, numCeldas));
        for (int i = 0; i < numCeldas; i++) {
            for (int j = 0; j < numCeldas; j++) {
                Casilla casilla = new Casilla(tableroLogico.elementoEn(i, j));
                casilla.setBackground(backgroundColor);
                casilla.setBorder(BorderFactory.createLineBorder(borderColor));
                tablero.add(casilla);
                casillitas[i][j] = casilla;
            }
        }
    }

    public static void main(String[] args) {
        MarbelGameGUI gui = new MarbelGameGUI();
        gui.setVisible(true);
    }
}


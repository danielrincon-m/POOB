package presentacion;

import aplicacion.game.engine.Input;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    private StartScreen startScreen;
    private ConfigurationScreen configurationScreen;
    private OnePlayerScreen playerScreen;
    private PlayersScreen playersScreen;
    private GameScreen gameScreen;
    private MachinesScreen machinesScreen;
    public static int WIDTH;
    public static int HEIGHT;
    private JPanel pantalla;
    private Screen screen;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;
    private CardLayout cardLayout;


    public Application() {
        initFrame();
    }

    private void initFrame() {
        //Hagamoslo de 800x800 :-)

        WIDTH = 800;
        HEIGHT = 800;
        setSize(WIDTH, HEIGHT);
        setTitle("POOng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
        addKeyListener(Input.getInstance());

        prepareElementosMenu();
        prepareAccionesMenu();
        ventanas();
    }

    public void ventanas() {
        cardLayout = new CardLayout(10, 10);
        setLayout(cardLayout);

        startScreen = new StartScreen(this);
        cardLayout.addLayoutComponent(startScreen, "inicio");
        add(startScreen);

        configurationScreen = new ConfigurationScreen(this);
        cardLayout.addLayoutComponent(configurationScreen, "cc");
        add(configurationScreen);

        playersScreen = new PlayersScreen(this);
        cardLayout.addLayoutComponent(playersScreen, "jj");
        add(playersScreen);

        playerScreen = new OnePlayerScreen(this);
        cardLayout.addLayoutComponent(playerScreen, "jm");
        add(playerScreen);

        machinesScreen = new MachinesScreen(this);
        cardLayout.addLayoutComponent(machinesScreen, "mm");
        add(machinesScreen);

        gameScreen = new GameScreen(this);
        cardLayout.addLayoutComponent(gameScreen, "game");
        add(gameScreen);
        pack();
    }

    public void pantallaPrincipal() {
        cardLayout.first(getContentPane());
    }

    public void irAlaSiguientePantalla(String nombre) {
        cardLayout.show(getContentPane(), nombre);
    }

    private void prepareElementosMenu() {
        JMenuBar barraMenu = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        salir = new JMenuItem("Salir");
        menu.add(nuevo);
        menu.add(abrir);
        menu.add(guardar);
        menu.add(salir);
        barraMenu.add(menu);
        setJMenuBar(barraMenu);
    }

    private void prepareElementosPrincipal() {
        cardLayout = new CardLayout();
        setSize(new Dimension(WIDTH, HEIGHT));
        pantalla = new JPanel(cardLayout);
        startScreen = new StartScreen(this);
        add(startScreen);
        pantalla.add(startScreen);
        cardLayout.show(startScreen, "n");

    }

    private void prepareAccionesMenu() {
        salir.addActionListener(e -> cerra());
        nuevo.addActionListener(e -> this.pantallaPrincipal());
    }


    public void cerra() {
        int option = JOptionPane.showConfirmDialog(null, "Desea cerrar Poong");
        if (option == 0) {
            System.exit(0);
        }

    }


    public static void main(String[] args) {
        Application app = new Application();
        app.setVisible(true);
    }
}

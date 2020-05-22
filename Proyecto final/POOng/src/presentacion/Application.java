package presentacion;

import aplicacion.ApplicationManager;
import aplicacion.game.engine.input.Input;
import aplicacion.game.enums.GameState;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Clase principal de la aplicación, inicia todos los componentes
 */
public class Application extends JFrame {

    private final ApplicationManager applicationManager;
    private StartScreen startScreen;
    private ConfigurationScreen configurationScreen;
    private OnePlayerScreen onePlayerScreen;
    private PlayersScreen playersScreen;
    private GameScreen gameScreen;
    private MachinesScreen machinesScreen;
    private CharactersScreen charactersScreen;

    public static int WIDTH;
    public static int HEIGHT;

    private JMenuItem nuevo, abrir, guardar, salir;
    private CardLayout cardLayout;

    public Application() {
        applicationManager = new ApplicationManager();
        initFrame();
    }

    /**
     * Inicializa los valores de la ventana principal
     */
    private void initFrame() {
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

    /**
     * Genera todas las pantallas del juego
     */
    public void ventanas() {
        cardLayout = new CardLayout(10, 10);
        setLayout(cardLayout);

        startScreen = new StartScreen(this);
        cardLayout.addLayoutComponent(startScreen, "inicio");
        add(startScreen);

        configurationScreen = new ConfigurationScreen(this);
        cardLayout.addLayoutComponent(configurationScreen, "Configuracion");
        add(configurationScreen);

        playersScreen = new PlayersScreen(this);
        cardLayout.addLayoutComponent(playersScreen, "Jugador vs Jugador");
        add(playersScreen);

        onePlayerScreen = new OnePlayerScreen(this);
        cardLayout.addLayoutComponent(onePlayerScreen, "Jugador vs Maquina");
        add(onePlayerScreen);

        machinesScreen = new MachinesScreen(this);
        cardLayout.addLayoutComponent(machinesScreen, "Maquina vs Maquina");
        add(machinesScreen);

        gameScreen = new GameScreen(this);
        cardLayout.addLayoutComponent(gameScreen, "game");
        add(gameScreen);

        charactersScreen = new CharactersScreen(this);
        cardLayout.addLayoutComponent(charactersScreen, "personajes");
        add(charactersScreen);

        pack();
    }

    /**
     * Realiza una transición a otra pantalla
     *
     * @param nombre El nombre de la pantalla a la que se quiere ir
     */
    public void irAlaPantalla(String nombre) {
        cardLayout.show(getContentPane(), nombre);
    }

    /**
     * Inicia el juego en la parte de aplicacion y se mueve hacia la pantalla de juego
     */
    public void iniciarjuego() {
        if (applicationManager.getGameProperties().areValid()) {
            gameScreen.registerTimeListener();
            applicationManager.startGame();
            irAlaPantalla("game");
        } else {
            JOptionPane.showMessageDialog(this, "Las propiedades del juego no están completas." +
                    "\nVerifique que ha seleccionado los personajes.");
        }
    }

    /**
     * Inicializa las propiedades de la pantalla de selección de personaje y se va a esa pantalla
     *
     * @param id          El id del jugador que se va a seleccionar
     * @param tipoDeJuego El tipo de juego que se va a jugar, para saber a cual pantalla retornar
     */
    public void prepareJugador(int id, String tipoDeJuego) {
        irAlaPantalla("personajes");
        charactersScreen.setId(id);
        charactersScreen.setTipoDeJuego(tipoDeJuego);
        charactersScreen.reiniciarValoresPantalla();
    }

    /**
     * Perpara la pantalla inicial de jugador vs máquina y va a esa pantalla
     */
    public void prepareJugadorVsMaquina() {
        onePlayerScreen.seleccionarMaquinaEnComboBox();
        irAlaPantalla("Jugador vs Maquina");
    }

    /**
     * Prepara la pantalla inicial de máquina vs máquina y va a esa pantalla
     */
    public void prepareMaquinaVsMaquina() {
        machinesScreen.seleccionarMaquinasEnComboBox();
        irAlaPantalla("Maquina vs Maquina");
    }

    /**
     * @return El ApplicationManager
     */
    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }

    /**
     * Genera y establece los elementos del menú
     */
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

    /**
     * Prepara las acciones de todos los botones del menú
     */
    private void prepareAccionesMenu() {
        nuevo.addActionListener(e -> nuevo());
        guardar.addActionListener(e -> guardar());
        abrir.addActionListener(e -> abrir());
        salir.addActionListener(e -> cerrar());
    }

    /**
     * Termina el juego y se va a la pantalla inicial
     */
    public void nuevo() {
        applicationManager.endGame();
        cardLayout.first(getContentPane());
    }

    /**
     * Abre un juego guardado i lo inicia
     */
    private void abrir() {
        if (applicationManager.getGameManager().getGameState() == GameState.ENDED) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setDialogTitle("Especifique el archivo a abrir");
            FileNameExtensionFilter datFilter = new FileNameExtensionFilter("Savegames de POOng (.poong)", "poong");
            fileChooser.setFileFilter(datFilter);
            int seleccion = fileChooser.showOpenDialog(this.getContentPane());

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                getApplicationManager().getGameManager().load(file);
                gameScreen.registerTimeListener();
                irAlaPantalla("game");
            }
        } else {
            JOptionPane.showMessageDialog(this.getContentPane(),
                    "No puedes abrir si ha iniciado el juego!");
        }
    }

    /**
     * Guarda el estado de un juego en un archivo
     */
    private void guardar() {
        if (applicationManager.getGameManager().getGameState() != GameState.ENDED) {
            applicationManager.getGameManager().pauseGame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setDialogTitle("Especifique el archivo a guardar");
            FileNameExtensionFilter datFilter = new FileNameExtensionFilter("Savegames de POOng (.poong)", "poong");
            fileChooser.setFileFilter(datFilter);
            int seleccion = fileChooser.showSaveDialog(this.getContentPane());

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".poong")) {
                    file = new File(file.getName() + ".poong");
                }
                getApplicationManager().getGameManager().save(file);
            }
            applicationManager.getGameManager().resumeGame();
        } else {
            JOptionPane.showMessageDialog(this.getContentPane(),
                    "No puedes guardar si no ha iniciado el juego!");
        }
    }

    /**
     * Cierra la aplicación
     */
    public void cerrar() {
        int option = JOptionPane.showConfirmDialog(null, "Desea cerrar Poong");
        if (option == 0) {
            System.exit(0);
        }
    }

    /**
     * Punto de entrada de la aplicación, genera una nueva aplicación
     *
     * @param args .
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.setVisible(true);
    }
}

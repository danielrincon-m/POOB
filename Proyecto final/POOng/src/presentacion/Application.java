package presentacion;

import aplicacion.ApplicationManager;
import aplicacion.game.engine.Input;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class Application extends JFrame {
    private ApplicationManager applicationManager;
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

    public void irAlaSiguientePantalla(String nombre) {
        cardLayout.show(getContentPane(), nombre);
    }

    public void iniciarjuego() {
        if(applicationManager.getGameProperties().areValid()){
            gameScreen.registerTimeListener();
            applicationManager.startGame();
            irAlaSiguientePantalla("game");
        }
        else{
            JOptionPane.showMessageDialog(this,"Las propiedades del juego no están completas." +
                    "\nVerifique que ha seleccionado los personajes." );
        }
    }

    public void prepareJugador(int id, String tipoDeJuego) {
        irAlaSiguientePantalla("personajes");
        charactersScreen.setId(id);
        charactersScreen.setTipoDeJuego(tipoDeJuego);
        charactersScreen.calcularValoresPantalla();
    }

    public ApplicationManager getApplicationManager() {
        return applicationManager;
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

    private void prepareAccionesMenu() {
        nuevo.addActionListener(e -> nuevo());
        guardar.addActionListener(e -> guardar());
        abrir.addActionListener(e -> abrir());
        salir.addActionListener(e -> cerrar());
    }

    public void nuevo() {
        applicationManager.endGame();
        cardLayout.first(getContentPane());
    }

    private void abrir() {

        if (!applicationManager.getGameManager().gameStarted()) {
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
                irAlaSiguientePantalla("game");
            }
        } else {
            JOptionPane.showMessageDialog(this.getContentPane(),
                    "No puedes abrir si ha iniciado el juego!");
        }
    }

    private void guardar() {
        if (applicationManager.getGameManager().gameStarted()) {
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

    public void cerrar() {
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

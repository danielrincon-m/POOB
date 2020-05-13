package presentacion;

import aplicacion.ApplicationManager;
import aplicacion.GameProperties;
import aplicacion.game.engine.Input;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;


import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private GameProperties gameProperties;
    public ApplicationManager applicationManager;
    private CharacterPersonality characterPersonality;
    private StartScreen startScreen;
    private ConfigurationScreen configurationScreen;
    private OnePlayerScreen playerScreen;
    private PlayersScreen playersScreen;
    private GameScreen gameScreen;
    private MachinesScreen machinesScreen;
    private CharactersScreen charactersScreen;

    public static int WIDTH;
    public static int HEIGHT;

    private  int idJugador;
    private JPanel pantalla;
    private Screen screen;
    private JMenuItem nuevo, abrir, guardar,salir;
    private CardLayout cardLayout;


    public Application() {
        initFrame();
        applicationManager = new ApplicationManager(gameScreen);
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

        playerScreen = new OnePlayerScreen(this);
        cardLayout.addLayoutComponent(playerScreen, "Jugador vs Maquina");
        add(playerScreen);

        machinesScreen = new MachinesScreen(this);
        cardLayout.addLayoutComponent(machinesScreen, "Maquina vs Maquina");
        add(machinesScreen);

        gameScreen = new GameScreen(this);
        cardLayout.addLayoutComponent(gameScreen, "game");
        add(gameScreen);
        /**
        charactersScreen = new CharactersScreen(this,idJugador);
        cardLayout.addLayoutComponent(charactersScreen,"personajes");
        add(charactersScreen);
         */

        pack();
    }

    public void pantallaPrincipal() {
        cardLayout.first(getContentPane());
    }

    public void irAlaSiguientePantalla(String nombre) {
        cardLayout.show(getContentPane(),nombre);
    }

/**
    public   void accionJugador( int posicion, CharacterPersonality jugador){
        //System.out.println(jugador);
        applicationManager.getGameProperties().setCharacter(posicion,jugador);
    }
*/
    public void iniciarjuego(){
        applicationManager.startGame();
        irAlaSiguientePantalla("game");
    }

    public void prepareJugador(int id,String tipoDeJuego){
        //verificar tipo de enum
        charactersScreen = new CharactersScreen(this,id,tipoDeJuego);
        cardLayout.addLayoutComponent(charactersScreen,"personajes");
        add(charactersScreen);
        irAlaSiguientePantalla("personajes");

    }


    public Boolean verificarSiExisteJugador(CharacterPersonality personaje){
        boolean verificacion =applicationManager.getResourceManager().getAvailablePlayerImages().containsKey(personaje);
        return  verificacion;

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

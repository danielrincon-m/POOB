package presentacion;

import aplicacion.game.engine.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application extends JFrame {
    public static int WIDTH;
    public static int HEIGHT;
    private  JPanel pantalla;
    private  StartScreen startScreen;
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
        prepareElementosMenu();
        prepareAccionesMenu();



        CardLayout cl = new CardLayout(10, 10);
        setLayout(cl);


        //prepareElementosPrincipal();
        //setLayout(new CardLayout(10, 10));

        //add(new StartScreen());
        //add(new PlayersScreen());
        //add(new ConfigurationScreen());
        //add(new PlayerScreen());
        //add(new GameScreen());
        //add(new Screen();

        pack();
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
    private  void prepareElementosPrincipal(){
        cardLayout = new CardLayout();
        setSize(new Dimension(WIDTH, HEIGHT));
        pantalla = new JPanel(cardLayout);
        startScreen = new StartScreen();
        add(startScreen);
        pantalla.add(startScreen);
        cardLayout.show(startScreen,"n");

    }

    private void prepareAccionesMenu(){
        salir.addActionListener(e -> cerra());

    }


    public void cerra(){
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

package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application extends JFrame {
    public static int WIDTH;
    public static int HEIGHT;
    private Screen inicial;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;
    private CardLayout layout;

    public Application() {
        initFrame();
    }

    private void initFrame() {
        WIDTH = 600;
        HEIGHT = 700;

        setSize(WIDTH, HEIGHT);
        setTitle("POOng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        prepareElementosMenu();
        prepareAccionesMenu();

        setLayout(new CardLayout(10, 10));


        //add(new StartScreen());
        //add(new PlayersScreen());
        add(new ConfigurationScreen());
        //add(new PlayerScreen());

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

package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MarbelGameGUI extends JFrame {
    //elemntos del menu
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem guardarComo;
    private JMenuItem salir;

    private MarbelGameGUI() {
        prepareElementos();
        prepareAcciones();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(dimension.width/2, dimension.height/2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    private void prepareElementos() {
        setTitle("Marbel Game");
        setLayout(new FlowLayout());
        prepareElementoMenu();
        add(prepareAreaTablero());
    }

    private void prepareElementoMenu(){
        JMenuBar menuBarra= new JMenuBar();
        JMenu archivo= new JMenu("Archivo");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        guardarComo = new JMenuItem("Guardar como");
        salir = new JMenuItem("Salir");
        menuBarra.add(archivo);
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(guardarComo);
        archivo.add(salir);
        setJMenuBar(menuBarra);
    }
    private  JPanel prepareAreaImagen(){
        return  null;
    }
    private  JPanel prepareAreaTablero(){
        JPanel seccionTablero = new JPanel(new BorderLayout());
        JPanel tablero = new JPanel(new GridLayout());
        JButton norte = new JButton();
        JButton sur = new JButton();
        JButton este = new JButton();
        JButton oeste = new JButton();
        seccionTablero.add(tablero, BorderLayout.CENTER);
        seccionTablero.add(norte, BorderLayout.NORTH);
        seccionTablero.add(sur, BorderLayout.SOUTH);
        seccionTablero.add(este, BorderLayout.EAST);
        seccionTablero.add(oeste, BorderLayout.WEST);
        seccionTablero.setPreferredSize(new Dimension(200,200));
        return  seccionTablero;
    }

    private void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev ){
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
    }

    private  void accionGuardar(){
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showSaveDialog(this);
        if(selection == JFileChooser.APPROVE_OPTION){
            File archivo = fileChooser.getSelectedFile();
            String name = archivo.getName();
            JOptionPane.showMessageDialog(this,"Esta funcionalidad está en construcción," +
                    " usted intentando guardar el archivo "+ name);
        }

    }

    private  void accionAbrir(){
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showOpenDialog(this);
        if(selection == JFileChooser.APPROVE_OPTION){
            File archivo = fileChooser.getSelectedFile();
            String name = archivo.getName();
            JOptionPane.showMessageDialog(this,"Esta funcionalidad está en construcción," +
                    " usted intententó abrir el archivo "+ name);
        }
    }

    private void preguntaSalir(){
        int option;
        option=JOptionPane.showConfirmDialog(null,"desea cerrar la aplicación");
        if (option==0){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        MarbelGameGUI gui = new MarbelGameGUI();
        gui.setVisible(true);
    }
}


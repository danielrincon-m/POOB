package presentacion;
import aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutomataGUI extends JFrame{

    private JButton botonReloj;
    private JLabel lFila;
    private JLabel lColumna;
    private JTextField tFila;
    private JTextField tColumna;
    private JPanel panelControl;
    private JPanel panelNueva;
    private JPanel panelBNueva;
    private JButton botonViva;
    private JButton botonLatente;

    private JMenuItem[] items;
    private JMenuBar barraMenu;
    private JMenu menu;

    private FotoAutomata foto;
    private AutomataCelular automata=null;

    public AutomataGUI(AutomataCelular ac) {
        super("Automata celular");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        automata=ac;
        foto=new FotoAutomata(automata);
        setSize(new Dimension(815,890));
        prepareElementos();
        prepareAcciones();

    }

    private void prepareElementos() {
        setResizable(false);

        botonReloj=new JButton("Tic-tac");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(foto,BorderLayout.NORTH);
        getContentPane().add(botonReloj,BorderLayout.SOUTH);
        foto.repaint();

        prepareElementosMenu();
    }

    private  void prepareElementosMenu(){
        items = new JMenuItem[7];
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        menu = new JMenu("Archivo");
        barraMenu.add(menu);

        items[0] = new JMenuItem("Nuevo");
        items[1] = new JMenuItem("Abrir");
        items[2] = new JMenuItem("Guardar");
        items[3] = new JMenuItem("Importar");
        items[4] = new JMenuItem("Exportar");
        items[5] = new JMenuItem("Reiniciar");
        items[6] = new JMenuItem("Salir");

        menu.add(items[0]);
        menu.addSeparator();
        menu.add(items[1]);
        menu.add(items[2]);
        menu.addSeparator();
        menu.add(items[3]);
        menu.add(items[4]);
        menu.addSeparator();
        menu.add(items[5]);
        menu.addSeparator();
        menu.add(items[6]);
        menu.addSeparator();
    }

    private void prepareAcciones(){

        botonReloj.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    botonRelojAccion();
                }
            });

    }

    private void botonRelojAccion() {
        automata.ticTac();
        foto.repaint();
    }
    /**
    private void prepareAccionesMenu(){
        items[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                opcionNuevo();
            }
        });
    }
    */


    public static void main(String[] args){
        AutomataCelular ac = new AutomataCelular();
        AutomataGUI ca = new AutomataGUI(ac);
        ca.setVisible(true);
    }  
}

class FotoAutomata extends JPanel{
    public static int TAMANO=40;
    private AutomataCelular automata;

    public FotoAutomata(AutomataCelular ac) {
        setBackground(Color.white);
        automata=ac;
        setPreferredSize(new Dimension(800,800));
    }


    public void setAutomata(AutomataCelular automata){
        this.automata=automata;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (int f=0;f<=automata.getLongitud();f++){
            g.drawLine(f*TAMANO,0,f*TAMANO,automata.getLongitud()*TAMANO);
        }
        for (int c=0;c<=automata.getLongitud();c++){
            g.drawLine(0,c*TAMANO,automata.getLongitud()*TAMANO,c*TAMANO);
        }		
        for (int f=0;f<automata.getLongitud();f++){
            for(int c=0;c<automata.getLongitud();c++){
                if (automata.getElemento(f,c)!=null){
                    g.setColor(automata.getElemento(f,c).getColor());
                    if (automata.getElemento(f,c).getForma()==Elemento.CUADRADA){                  
                        if (automata.getElemento(f,c).isVivo()){
                            g.drawRoundRect(TAMANO*c+3,TAMANO*f+3,35,35,5,5);
                        }else{
                            g.fillRoundRect(TAMANO*c+3,TAMANO*f+3,35,35,5,5);    

                        }
                    }else {
                        if (automata.getElemento(f,c).isVivo()){
                            g.fillOval(TAMANO*c+10,TAMANO*f+10,20,20);
                        } else {
                            g.drawOval(TAMANO*c+10,TAMANO*f+10,20,20);
                        }
                    }
                }
            }
        }
    }
}
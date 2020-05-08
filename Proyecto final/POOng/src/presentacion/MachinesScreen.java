package presentacion;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MachinesScreen extends Screen {
        public static final String fondoInicial = "resources/fondo2.png";
        private BufferedImage fondo;
        private  Application application;
        private JButton jugar,atras;
        private JLayeredPane panelJugarUno;
        private  JPanel jugadores,maquina1,maquina2;
        private JLabel etiquetaMaquina1,etiquetaMaquina2;
        private  JComboBox opcionesMaquina1,opcionesMaquina2;

        public MachinesScreen(Application application) {
            super();
            this.application= application;
            prepareAccionesElemento();
        }

        private void setFondo(String inical) {
            try {
            fondo = ImageIO.read(new File(inical));
            }
            catch(IOException e){
            e.printStackTrace();
            }
        }
        @Override
        protected void prepareElements() {
            setFondo(ConfigurationScreen.fondoInicial);
            setBorder(new EmptyBorder(350, 250, 280, 250));
            setLayout(new GridLayout(3, 1, 10, 10));
            jugadores =new JPanel(new GridLayout(1, 2, 10, 10));
            maquina1= new JPanel(new GridLayout(1, 2, 10, 10));
            maquina2= new JPanel(new GridLayout(1, 2, 10, 10));
            etiquetaMaquina1 = new JLabel("Máquina 1");
            etiquetaMaquina2 = new JLabel("Máquina 2");
            opcionesMaquina1 = new JComboBox();
            opcionesMaquina1.addItem("Greedy");
            opcionesMaquina1.addItem("Normal");
            opcionesMaquina1.addItem("Extreme");
            opcionesMaquina1.addItem("Sniper");
            opcionesMaquina1.addItem("Lazy");
            opcionesMaquina2 = new JComboBox();
            opcionesMaquina2.addItem("Extreme");
            opcionesMaquina2.addItem("Normal");
            opcionesMaquina2.addItem("Sniper");
            opcionesMaquina2.addItem("Greedy");
            opcionesMaquina2.addItem("Lazy");
            jugar =new JButton("Jugar");
            atras = new JButton("Atrás");
            jugar =new JButton("Jugar");
            atras = new JButton("Atrás");
            maquina1.add(etiquetaMaquina1);
            maquina1.add(opcionesMaquina1);
            maquina2.add(etiquetaMaquina2);
            maquina2.add(opcionesMaquina2);
            jugadores.add(maquina1);
            jugadores.add(maquina2);
            add(jugadores);
            add(jugar);
            add(atras);

        }
        private void  prepareAccionesElemento() {
            jugar.addActionListener(e -> application.irAlaSiguientePantalla("game"));
            atras.addActionListener(e -> application.pantallaPrincipal());
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0,getWidth(),getHeight(),this);
        }
}


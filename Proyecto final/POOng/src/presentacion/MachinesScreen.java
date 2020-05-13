package presentacion;

import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MachinesScreen extends Screen implements ItemListener {

        public static final String fondoInicial = "resources/fondo2.png";
        private BufferedImage fondo;
        private JButton jugar,atras;
        private JLayeredPane panelJugarUno;
        private  JPanel jugadores,maquina1,maquina2;
        private JLabel etiquetaMaquina1,etiquetaMaquina2;
        private  JComboBox opcionesMaquina1,opcionesMaquina2;
        private String seleccion;
        private CharacterPersonality seleccionMaquina;
        //private String seleccion;

        public MachinesScreen(Application application) {
            super(application);
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
            opcionesMaquina2 = new JComboBox();
            prepareMaquina(opcionesMaquina1);
            prepareMaquina(opcionesMaquina2);
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
            opcionesMaquina1.addItemListener(this);
            opcionesMaquina2.addItemListener(this);



        }
        private void prepareMaquina(JComboBox comboMaquina){
            for (CharacterPersonality machine : CharacterPersonality.values()) {
                if (machine.getType().equals(CharacterType.MACHINE)) {
                    comboMaquina.addItem(machine);
                }
            }

        }


        @Override
        protected void  prepareAccionesElemento() {
            //jugar.addActionListener(e -> application.irAlaSiguientePantalla("game"));
            jugar.addActionListener(e -> application.iniciarjuego());
            atras.addActionListener(e -> application.pantallaPrincipal());
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0,getWidth(),getHeight(),this);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource()==opcionesMaquina1) {
                //String seleccionado1=(String)opcionesMaquina1.getSelectedItem();
                application.applicationManager.getGameProperties().setCharacter(0,(CharacterPersonality) opcionesMaquina1.getSelectedItem());
                //application.accionJugador(0,(CharacterPersonality) opcionesMaquina1.getSelectedItem());

            }
            else if(e.getSource()==opcionesMaquina2){
                application.applicationManager.getGameProperties().setCharacter(1,(CharacterPersonality) opcionesMaquina2.getSelectedItem());
                //application.accionJugador(1,(CharacterPersonality) opcionesMaquina2.getSelectedItem());
            }

        }
}


package presentacion;

import aplicacion.game.enums.CharacterPersonality;

import aplicacion.game.enums.CharacterType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CharactersScreen extends Screen {
    private CharacterPersonality characterProperties;
    private ButtonGroup botones;
    private JLabel personajes;
    private ImageIcon imagen;
    private JPanel seleccion, imagenes;
    private String name1;
    private Button aceptar,atras;
    private TitledBorder titulo;
    private int idJugador;
    private String tipoDeJuego;

    public CharactersScreen(Application application) {
        super(application);
        //idJugador=id;
        //acciones();
    }

    public void setId(int id){
        idJugador=id;

    }

    public void setTipoDeJuego(String tipo){
        tipoDeJuego=tipo;

    }


    @Override
    protected void prepareElements() {

        setFondo(fondoInicial);
        setBorder(new EmptyBorder(200, 200, 280, 100));
        setLayout(new GridLayout(1, 2, 10, 10));
        seleccion = new JPanel();
        titulo = new TitledBorder("Selección de personaje");
        titulo.setTitleColor(Color.white);
        seleccion.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 10, 10, 10),titulo));
        seleccion.setLayout(new GridLayout(0, 1, 5, 5));
        seleccion.setBackground(new Color(238,255,254));
        seleccion.setOpaque(false);
        imagenes = new JPanel();
        imagenes.setOpaque(false);
        imagenes.setBorder(new EmptyBorder(40, 5, 0, 5));
        botones = new ButtonGroup();
        aceptar = new Button("Aceptar");
        aceptar.setBackground(new Color(130,218,245));
        atras = new Button("atrás");
        atras.setBackground(new Color(150,200,245));
        prepareJugador();
        seleccion.add(aceptar);
        seleccion.add(atras);
        add(seleccion);
        add(imagenes);
}

    private  void jugadorSeleccionado(CharacterPersonality playerCharacter){
                characterProperties = playerCharacter;;
                imagen = new ImageIcon(playerCharacter.spritePath());
                Image captura = imagen.getImage();
                Image newimg = captura.getScaledInstance(220,220,Image.SCALE_DEFAULT);
                imagen = new ImageIcon(newimg);
                personajes = new JLabel (imagen);
                imagenes.removeAll();
                imagenes.add(personajes);
                add(imagenes);
                revalidate();
                repaint();
    }


    private void  prepareJugador(){
        for (CharacterPersonality playerCharacter : CharacterPersonality.values()) {
            if (playerCharacter.getType().equals(CharacterType.HUMAN)) {
                    JRadioButton name = new JRadioButton (playerCharacter.getName());
                    name.setBackground(new Color(150,162,255));
                    name.addActionListener(e1 -> jugadorSeleccionado(playerCharacter));
                    //name.setOpaque(false);
                    botones.add(name);
                    seleccion.add(name);


            }
        }
    }


    private void prepareAccionesPersonaje(){
        /**
        if(idJugador==0){
            idRival=2;
        }
         */
        if(application.verificarSiExisteJugador(characterProperties)){
            //application.accionJugador(idJugador,characterProperties);
            application.applicationManager.getGameProperties().setCharacter(idJugador,characterProperties);
            //application.irAlaSiguientePantalla("Jugador vs Jugador");
            /**
            if(tipoDeJuego=="jvsj"){
                application.irAlaSiguientePantalla("Jugador vs Jugador");
            }
            else{
                application.irAlaSiguientePantalla("Jugador vs Maquina");
            }
             */
        }
        else{
            JOptionPane.showMessageDialog(this,"Este personaje ya fue selecionado");
        }
    }


    @Override
    protected void prepareAccionesElemento() {
        aceptar.addActionListener(e1 -> prepareAccionesPersonaje());
        //aceptar.addActionListener(e -> prepareAccionAceptar());
        atras.addActionListener(e1 -> prepareAccionAtras());

    }
    private void prepareAccionAceptar(){
        if(tipoDeJuego=="jvsj"){
            aceptar.addActionListener(e -> application.irAlaSiguientePantalla("Jugador vs Jugador"));
        }
        else{
            aceptar.addActionListener(e -> application.irAlaSiguientePantalla("Jugador vs Maquina"));
        }

    }
    private void prepareAccionAtras(){
        //System.out.println("ENTROOOO");
        if(tipoDeJuego=="jvsj"){
            atras.addActionListener(e -> application.irAlaSiguientePantalla("Jugador vs Jugador"));
        }
        else{
            atras.addActionListener(e -> application.irAlaSiguientePantalla("Jugador vs Maquina"));
        }

    }


}

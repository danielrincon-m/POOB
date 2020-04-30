package aplicacion;

import aplicacion.game.engine.input.Input;
import aplicacion.game.managers.GameManager;

public class ApplicationManager {

    public ApplicationManager() {
    }
    /*
    TODO: Realizar un sistema que transforme "unidades del juego" en coordenadas en pixeles
        ya que si la pantalla cambia de tamaño, los objetos se mueven más lento, deberían estar en un sistema de
        coordenadas constante, y luego mapear esos valores a las coordenadas reales, igual que la escala.
     */

    public static void main(String[] args) {
        GameManager gm = new GameManager();
        gm.startGame(600, 800);
        gm.setVisible(true);
        gm.addKeyListener(Input.getInstance());
    }
}

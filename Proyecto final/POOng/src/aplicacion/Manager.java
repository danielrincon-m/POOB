package aplicacion;

import aplicacion.game.GameManager;
import aplicacion.game.engine.input.Input;

public class Manager {

    public Manager() {
    }

    public static void main(String[] args) {
        GameManager gm = GameManager.getInstance();
        gm.initialize(1280, 720);
        try {
            gm.startGame();
        } catch (POOngGameException e) {
            System.out.println(e.getMessage());
        }
        gm.setVisible(true);
        gm.addKeyListener(Input.getInstance());
    }
}

package aplicacion;

import aplicacion.game.GameManager;

public class Manager {

    public Manager() {
    }

    public static void main(String[] args) {
        GameManager gm = GameManager.getInstance();
        gm.setDimensions(1280, 720);
        try {
            gm.startGame();
        } catch (POOngException e) {
            System.out.println(e.getMessage());
        }
    }
}

package presentacion;

import aplicacion.game.components.Transform;
import aplicacion.game.engine.Input;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.EntityName;
import aplicacion.game.managers.GameManager;

import java.awt.*;
import java.util.HashMap;

public class GameScreen extends Screen {

    private GameManager gameManager;

    public GameScreen() {
        super();
        startGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        HashMap<EntityName, Entity> entities = Entity.getEntities();
        for (EntityName name : entities.keySet()) {
            Transform entityTransform = entities.get(name).getComponent(Transform.class);
            int x = (int)entityTransform.getPosition().x;
            int y = (int)entityTransform.getPosition().y;
            int width = (int)entityTransform.getSize().x;
            int height = (int)entityTransform.getSize().y;
            g2.drawRect(x, y, width, height);
        }
    }

    @Override
    protected void prepareElements() {
        setFocusable(true);
        addKeyListener(Input.getInstance());
        gameManager = new GameManager(this);
    }

    private void startGame() {
        gameManager.startGame();
    }
}

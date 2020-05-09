package presentacion;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Sprite;
import aplicacion.game.components.Transform;
import aplicacion.game.engine.Input;
import aplicacion.game.entities.Entity;
import aplicacion.GameManager;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterType;
import aplicacion.game.enums.Characters;

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
        super.paintComponent(g);
        HashMap<String, Entity> entities = Entity.getEntities();
        for (String name : entities.keySet()) {
            Transform entityTransform = entities.get(name).getComponent(Transform.class);
            int x = (int)entityTransform.getPosition().x;
            int y = (int)entityTransform.getPosition().y;
            int width = (int)entityTransform.getSize().x;
            int height = (int)entityTransform.getSize().y;
            try {
                Sprite entitySprite = entities.get(name).getComponent(Sprite.class);
                g.drawImage(entitySprite.getImage(), x, y, width, height, null);
            } catch (EntityException e) {
                g.drawRect(x, y, width, height);
            }
        }
    }

    @Override
    protected void prepareElements() {
        setFocusable(true);
        addKeyListener(Input.getInstance());
        //gameManager = new GameManager(this);
    }

    private void startGame() {
        gameManager.startGame();
    }
}

package presentacion;

import aplicacion.ApplicationManager;
import aplicacion.exception.EntityException;
import aplicacion.game.components.Sprite;
import aplicacion.game.components.Transform;
import aplicacion.game.entities.Entity;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameScreen extends Screen {

    private ApplicationManager applicationManager;

    public GameScreen(Application application) {
        super(application);
        startGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        LinkedHashMap<String, Entity> entities = Entity.getEntities();
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
        applicationManager = new ApplicationManager(this);
    }

    @Override
    protected void prepareAccionesElemento() {

    }

    private void startGame() {
        applicationManager.startGame();
    }
}

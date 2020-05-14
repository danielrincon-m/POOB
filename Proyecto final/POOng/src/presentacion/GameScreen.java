package presentacion;

import aplicacion.GameManager;
import aplicacion.ResourceManager;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.engine.Timer.TimerListener;
import aplicacion.game.entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GameScreen extends Screen implements TimerListener {

    GameManager gameManager;
    ResourceManager resourceManager;

    public GameScreen(Application application) {
        super(application);
        application.getApplicationManager().setGameScreen(this);
        gameManager = application.getApplicationManager().getGameManager();
        resourceManager = application.getApplicationManager().getResourceManager();
        application.getApplicationManager().getGameManager().getGameTimer().addTimerListener(this, 0);
        //startGame();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameManager.update();
        LinkedHashMap<String, Entity> entities = Entity.getEntities();
        for (String name : entities.keySet()) {
            Transform entityTransform = entities.get(name).getComponent(Transform.class);
            int x = (int) entityTransform.getPosition().x;
            int y = (int) entityTransform.getPosition().y;
            int width = (int) entityTransform.getSize().x;
            int height = (int) entityTransform.getSize().y;
            if (entities.get(name).hasComponent(Sprite.class)) {
                Sprite entitySprite = entities.get(name).getComponent(Sprite.class);
                BufferedImage image = resourceManager.getSprite(entitySprite.getImageName());
                g.drawImage(image, x, y, width, height, null);
            } else {
                BufferedImage image = resourceManager.getSprite("resources/sprites/not_implemented.png");
                g.drawImage(image, x, y, width, height, null);
            }
        }
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void prepareElements() {
        fondoInicial = "resources/fondodejuego.png";
        setFondo();
    }

    @Override
    protected void prepareAccionesElemento() {

    }

/*    private void startGame() {
        applicationManager.startGame();
    }*/
}

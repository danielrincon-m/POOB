package presentacion;

import aplicacion.ApplicationManager;
import aplicacion.ResourceManager;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

public class GameScreen extends Screen {

    public GameScreen(Application application) {
        super(application);
        //startGame();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        LinkedHashMap<String, Entity> entities = Entity.getEntities();
        for (String name : entities.keySet()) {
            Transform entityTransform = entities.get(name).getComponent(Transform.class);
            int x = (int) entityTransform.getPosition().x;
            int y = (int) entityTransform.getPosition().y;
            int width = (int) entityTransform.getSize().x;
            int height = (int) entityTransform.getSize().y;
            if (entities.get(name).hasComponent(Sprite.class)) {
                Sprite entitySprite = entities.get(name).getComponent(Sprite.class);
                ResourceManager rm = application.getApplicationManager().getResourceManager();
                BufferedImage image = rm.getSprite(entitySprite.getImageName());
                g.drawImage(image, x, y, width, height, null);
            } else {
                g.drawRect(x, y, width, height);
            }
        }
    }

    @Override
    protected void prepareElements() {
        fondoInicial = "/resources/fondodejuego.png";
        setFondo(fondoInicial);
    }

    @Override
    protected void prepareAccionesElemento() {

    }

/*    private void startGame() {
        applicationManager.startGame();
    }*/
}

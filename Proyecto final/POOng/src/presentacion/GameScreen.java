package presentacion;

import aplicacion.ApplicationManager;
import aplicacion.exception.EntityException;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.entities.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class GameScreen extends Screen {

    private ApplicationManager applicationManager;
    public final String fondoInicial = "resources/fondodejuego.png";
    private BufferedImage fondo;

    public GameScreen(Application application) {
        super(application);
        //startGame();
    }
    private void setFondo() {
        try {
            fondo = ImageIO.read(new File(fondoInicial));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        LinkedHashMap<String, Entity> entities = Entity.getEntities();
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
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
        setFondo();
    }

    @Override
    protected void prepareAccionesElemento() {

    }

    private void startGame() {
        applicationManager.startGame();
    }
}

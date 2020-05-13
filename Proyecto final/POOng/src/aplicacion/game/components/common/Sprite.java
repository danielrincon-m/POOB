package aplicacion.game.components.common;

import aplicacion.game.components.Component;
import aplicacion.game.entities.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite extends Component {

    private int zIndex;
    private String imagePath;

    private BufferedImage image;

    public Sprite(Entity parent, String imagePath, int zIndex) {
        super(parent);
        this.zIndex = zIndex;
        loadImage(imagePath);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    public void setImage(String imagePath) {
        loadImage(imagePath);
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getzIndex() {
        return zIndex;
    }

    public String getImagePath() {
        return imagePath;
    }

    private void loadImage(String imagePath) {
        try {
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

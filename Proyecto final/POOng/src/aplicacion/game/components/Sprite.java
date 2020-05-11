package aplicacion.game.components;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite implements Component {

    private int zIndex;
    private String imagePath;

    private BufferedImage image;

    public Sprite(String imagePath, int zIndex) {
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
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

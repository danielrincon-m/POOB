package aplicacion.game.components;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite implements Component {

    private String imagePath;

    private BufferedImage image;

    public Sprite(String imageName) {
        loadImage(imageName);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    public void setImage(String imageName) {
        loadImage(imageName);
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getImagePath() {
        return imagePath;
    }

    private void loadImage(String imageName) {
        imagePath = "resources/sprites/" + imageName + ".png";
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

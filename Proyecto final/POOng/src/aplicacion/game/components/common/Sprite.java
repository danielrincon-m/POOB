package aplicacion.game.components.common;

import aplicacion.ResourceManager;
import aplicacion.game.components.Component;
import aplicacion.game.entities.Entity;

public class Sprite extends Component {

    private int zIndex;
    private String imageName;

    private ResourceManager resourceManager;
//    private BufferedImage image;

    public Sprite(Entity parent, String imageName, int zIndex) {
        super(parent);
        this.zIndex = zIndex;
        this.imageName = imageName;
        //loadImage(imagePath);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    public void setImage(String imagePath) {
        this.imageName = imagePath;
    }

/*    public BufferedImage getImage() {
        return image;
    }*/

    public int getzIndex() {
        return zIndex;
    }

    public String getImageName() {
        return imageName;
    }

/*    private void loadImage(String imagePath) {
        try {
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

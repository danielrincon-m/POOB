package aplicacion.game.components.common;

import aplicacion.ResourceManager;
import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;

public class Sprite extends Component {

    private final int zIndex;
    private String imageName;

    private ResourceManager resourceManager;

    public Sprite(Entity parent, String imageName, int zIndex) {
        super(parent);
        this.zIndex = zIndex;
        this.imageName = imageName;
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

    public int getzIndex() {
        return zIndex;
    }

    public String getImageName() {
        return imageName;
    }
}

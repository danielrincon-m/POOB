package aplicacion.game.components.common;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;

/**
 * Componente que almacena la ruta de la imagen ligada a su padre
 */
public class Sprite extends Component {

    private final int zIndex;
    private String imagePath;

    /**
     * Constructor del Sprite
     * @param parent La entidad padre del componente
     * @param imagePath La ruta de la ubicación de la imagen
     * @param zIndex La altura de la imagen con respecto al eje z
     */
    public Sprite(Entity parent, String imagePath, int zIndex) {
        super(parent);
        this.zIndex = zIndex;
        this.imagePath = imagePath;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    /**
     * Establecer la imagen relacionada a este sprite
     * @param imagePath La nueva ruta de la imagen
     */
    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return El zIndex de la imagen
     */
    public int getzIndex() {
        return zIndex;
    }

    /**
     * @return La ruta de la imagen
     */
    public String getImagePath() {
        return imagePath;
    }
}

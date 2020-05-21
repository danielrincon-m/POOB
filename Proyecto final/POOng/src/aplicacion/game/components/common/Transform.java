package aplicacion.game.components.common;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.utils.Vector2;

/**
 * Componente que almacena la posici+on y eltamaño de la Entidad
 */
public class Transform extends Component {
    private Vector2 centerPosition = new Vector2();
    private Vector2 position = new Vector2();
    private Vector2 size = new Vector2();

    /**
     * @param parent La Entidad que contiene este Componente
     * @param position La posición de la Entidad
     * @param size El tamaño en pixeles de la Entidad
     */
    public Transform(Entity parent, Vector2 position, Vector2 size){
        super(parent, parent);
        setPosition(position);
        setSize(size);
    }

    /**
     * @param parent La entidad que contiene este Componente
     */
    public Transform(Entity parent) {
        this(parent, new Vector2(), new Vector2());
    }

    @Override
    public void start() {}

    @Override
    public void update() {}

    /**
     * Establecer la posición de la Entidad
     * @param position La nueva posición de la Entidad
     */
    public void setPosition(Vector2 position) {
        this.position = position;
        centerPosition = new Vector2(position.x + size.x / 2f, position.y + size.y / 2f);
    }

    /**
     * Establece el tamaño de la Entidad
     * @param size El nuevo tamaño de la Entidad
     */
    public void setSize(Vector2 size) {
        this.size = size;
        centerPosition = new Vector2(position.x + size.x / 2f, position.y + size.y / 2f);
    }

    /**
     * Traslada la entidad en cierta cantidad
     * @param translation La cantidad que se moverá en cada eje
     */
    public void translate (Vector2 translation) {
        position.add(translation.x, translation.y);
        centerPosition.add(translation.x, translation.y);
    }

    /**
     * @return La posición de la Entidad
     */
    public Vector2 getPosition() {
        return new Vector2(position);
    }

    /**
     * @return El tamaño de la Entidad
     */
    public Vector2 getSize() {
        return new Vector2(size);
    }

    /**
     * @return La pocición del centro de la entidad
     */
    public Vector2 getCenterPosition() {
        return new Vector2(position.x + (size.x / 2f), position.y + (size.y / 2f));
    }

    /**
     * @return El ancho de la Entidad
     */
    public float getWidth() {
        return size.x;
    }

    /**
     * @return El alto de la Entidad
     */
    public float getHeight() {
        return size.y;
    }
}

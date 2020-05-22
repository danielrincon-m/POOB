package aplicacion.game.components.common;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.utils.Vector2;

/**
 * Componente abstracto encargado de las colisiones
 */
public abstract class Collider extends Component {
    protected float x;
    protected float y;

    protected Transform parentTransform;
    protected Vector2 offset = new Vector2();

    /**
     * Constructor del collider, correspondiente a la posición de su padre
     * @param parent La Entidad padre del componente
     */
    public Collider(Entity parent) {
        super(parent);
        parentTransform = this.parent.getComponent(Transform.class);
    }

    /**
     * Constructior del collider, correspondiente a la posición del padre con un desfase
     * @param parent La entidad padre del componente
     * @param offset El desfase con respecto a la posición del padre
     */
    public Collider(Entity parent, Vector2 offset) {
        this(parent);
        this.offset = offset;
    }

    @Override
    public void start() {
        getParentAttributes();
    }

    @Override
    public void update() {
        getParentAttributes();
    }

    /**
     * @return La posicion del collider
     */
    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    /**
     * Verifica la colisión con otro collider
     * @param other El otro collider
     * @return Si los Colliders colisionan
     */
    public abstract boolean collidesWith(Collider other);

    /**
     * Obtiene la posición de la entidad padre, y calcula la posición
     * teniendo en cuenta el offset definido
     */
    protected void getParentAttributes() {
        this.x = parentTransform.getPosition().x + offset.x;
        this.y = parentTransform.getPosition().y + offset.y;
    }
}

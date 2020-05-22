package aplicacion.game.components.common;

import aplicacion.game.entitiy.Entity;
import aplicacion.game.utils.Vector2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Componente Rectacgular que detecta colisiones
 */
public class RectangleCollider extends Collider {
    private boolean customSize = false;
    private float width;
    private float height;

    public RectangleCollider(Entity parent) {
        super(parent);
    }

    public RectangleCollider(Entity parent, Vector2 offset) {
        super(parent, offset);
    }

    /**
     * Constructor del collider, con cierto desfase y un tamaño personalizado
     * @param parent La Entidad padre del componente
     * @param offset El desfase con respeto a la posición de la entidad padre
     * @param size El tamaño del collider
     */
    public RectangleCollider(Entity parent, Vector2 offset, Vector2 size) {
        this(parent, offset);
        customSize = true;
        width = size.x;
        height = size.y;
    }

    /**
     * @return El tamaño del Collider
     */
    public Vector2 getSize() {
        return new Vector2(width, height);
    }

    @Override
    public boolean collidesWith(Collider other) {
        if (other instanceof RectangleCollider) {
            RectangleCollider otherCollider = (RectangleCollider) other;
            return x < otherCollider.x + otherCollider.width &&
                    x + width > otherCollider.x &&
                    y < otherCollider.y + otherCollider.height &&
                    this.height + this.y > otherCollider.y;
        }
        throw new NotImplementedException();
    }

    @Override
    protected void getParentAttributes() {
        super.getParentAttributes();
        if (!customSize) {
            width = parentTransform.getWidth();
            height = parentTransform.getHeight();
        }
    }
}

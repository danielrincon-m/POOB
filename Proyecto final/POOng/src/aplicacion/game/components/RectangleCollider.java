package aplicacion.game.components;

import aplicacion.game.entities.Entity;
import aplicacion.game.utils.Vector2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public RectangleCollider(Entity parent, Vector2 offset, Vector2 size) {
        this(parent, offset);
        customSize = true;
        width = size.x;
        height = size.y;
    }

    @Override
    public boolean collidesWith(Collider other) {
        if (other instanceof RectangleCollider) {
            RectangleCollider otherCollider = (RectangleCollider) other;
            if (x < otherCollider.x + otherCollider.width &&
                    x + width > otherCollider.x &&
                    y < otherCollider.y + otherCollider.height &&
                    this.height + this.y > otherCollider.y) {
                return true;
            }
            return false;
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

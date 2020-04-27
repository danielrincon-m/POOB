package aplicacion.game.engine.collision;

import aplicacion.game.entities.Entity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RectangleCollider extends Collider {
    public float width;
    public float height;

    public RectangleCollider(Entity parent) {
        super(parent);
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
    protected void getAttributes() {
        super.getAttributes();
        width = parent.getWidth();
        height = parent.getHeight();
    }
}

package aplicacion.game.engine.collision;

import aplicacion.game.gameObject.GameObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RectangleCollider extends Collider {
    public float width;
    public float height;

    public RectangleCollider(GameObject parent) {
        super(parent);
    }

    @Override
    public void checkIntersection(Collider other) {
        if (other instanceof RectangleCollider) {
            RectangleCollider otherCollider = (RectangleCollider) other;
            if (x < otherCollider.x + otherCollider.width &&
                    x + width > otherCollider.x &&
                    y < otherCollider.y + otherCollider.height &&
                    this.height + this.y > otherCollider.y) {
                collisions.add(other);
            } else {
                collisions.remove(other);
            }
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

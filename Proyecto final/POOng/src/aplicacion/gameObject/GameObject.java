package aplicacion.gameObject;

import aplicacion.collision.Collider;
import aplicacion.collision.RectangleCollider;

public abstract class GameObject {

    protected Collider collider;
    protected float xPosition;
    protected float yPosition;
    protected float width;
    protected float height;

    public GameObject(float xPosition, float yPosition, float width, float height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    public void addCollider(Collider collider) {
        collider.setParent(this);
        collider.update();
        this.collider = collider;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    protected void createCollider() {
        collider = new RectangleCollider(this);
    }
}

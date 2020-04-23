package aplicacion.collision;

import aplicacion.GameManager;
import aplicacion.gameObject.GameObject;

import java.util.HashSet;

public abstract class Collider {
    protected HashSet<Collider> collisions;
    protected float x;
    protected float y;

    protected GameObject parent;

    public Collider(GameObject parent) {
        this.parent = parent;
        getAttributes();
        registerCollider();
    }

    public void update() {
        getAttributes();
    }

    public abstract void checkIntersection(Collider other);

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    protected void getAttributes() {
        this.x = parent.getxPosition();
        this.y = parent.getyPosition();
    }

    private void registerCollider() {
        GameManager.getCollisionSystem().addCollider(this);
    }
}

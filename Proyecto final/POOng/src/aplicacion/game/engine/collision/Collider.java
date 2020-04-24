package aplicacion.game.engine.collision;

import aplicacion.game.gameObject.GameObject;

import java.util.HashSet;

public abstract class Collider {
    protected HashSet<Collider> collisions;
    protected float x;
    protected float y;

    protected GameObject parent;

    public Collider(GameObject parent) {
        setParent(parent);
        getAttributes();
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
}

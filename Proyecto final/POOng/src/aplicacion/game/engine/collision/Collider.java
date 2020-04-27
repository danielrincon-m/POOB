package aplicacion.game.engine.collision;

import aplicacion.game.entities.Entity;

public abstract class Collider {
    protected float x;
    protected float y;

    protected Entity parent;

    public Collider(Entity parent) {
        setParent(parent);
        getAttributes();
    }

    public void update() {
        getAttributes();
    }

    public abstract boolean collidesWith(Collider other);

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    protected void getAttributes() {
        this.x = parent.getxPosition();
        this.y = parent.getyPosition();
    }
}

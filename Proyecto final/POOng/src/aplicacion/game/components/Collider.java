package aplicacion.game.components;

import aplicacion.game.entities.Entity;
import aplicacion.game.utils.Vector2;

public abstract class Collider implements Component {
    protected float x;
    protected float y;

    protected Entity parent;
    protected Transform parentTransform;
    protected Vector2 offset = new Vector2();

    public Collider(Entity parent) {
        setParent(parent);
    }

    public Collider(Entity parent, Vector2 offset) {
        this(parent);
        this.offset = offset;
    }

    public void start() {
        getParentAttributes();
    }

    public void update() {
        getParentAttributes();
    }

    public abstract boolean collidesWith(Collider other);

    public void setParent(Entity parent) {
        this.parent = parent;
        parentTransform = this.parent.getComponent(Transform.class);
    }

    protected void getParentAttributes() {
        this.x = parentTransform.getPosition().x + offset.x;
        this.y = parentTransform.getPosition().y + offset.y;
    }
}

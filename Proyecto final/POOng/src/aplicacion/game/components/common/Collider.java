package aplicacion.game.components.common;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.utils.Vector2;

public abstract class Collider extends Component {
    protected float x;
    protected float y;

    protected Transform parentTransform;
    protected Vector2 offset = new Vector2();

    public Collider(Entity parent) {
        super(parent);
        parentTransform = this.parent.getComponent(Transform.class);
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

//    public void setParent(Entity parent) {
//        this.parent = parent;
//        parentTransform = this.parent.getComponent(Transform.class);
//    }

    protected void getParentAttributes() {
        this.x = parentTransform.getPosition().x + offset.x;
        this.y = parentTransform.getPosition().y + offset.y;
    }
}

package aplicacion.game.components;

import aplicacion.game.components.common.Transform;
import aplicacion.game.entities.Entity;

public abstract class Component {

    protected Entity parent;
    protected Transform transform;

    public Component() {}

    public Component(Entity parent) {
        this.parent = parent;
        transform = parent.getComponent(Transform.class);
    }

    public abstract void start();
    public abstract void update();
}

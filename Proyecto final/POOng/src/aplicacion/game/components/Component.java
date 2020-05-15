package aplicacion.game.components;

import aplicacion.ApplicationManager;
import aplicacion.game.components.common.Transform;
import aplicacion.game.entitiy.Entity;

public abstract class Component {

    protected Entity parent;
    protected Transform transform;

    protected ApplicationManager applicationManager;

    public Component(Entity parent) {
        this(parent, parent);
        transform = parent.getComponent(Transform.class);
    }

    public Component(Entity parent, Entity Dont_use_this) {
        this.parent = parent;
        applicationManager = parent.applicationManager;
    }

    public abstract void start();

    public abstract void update();
}

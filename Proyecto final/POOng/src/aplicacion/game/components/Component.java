package aplicacion.game.components;

import aplicacion.game.components.common.Transform;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;

import java.io.Serializable;

public abstract class Component implements Serializable {

    protected Entity parent;
    protected Transform transform;

    protected EntityManager entityManager;

    public Component(Entity parent) {
        this(parent, parent);
        transform = parent.getComponent(Transform.class);
    }

    public Component(Entity parent, Entity Dont_use_this) {
        this.parent = parent;
        entityManager = parent.getEntityManager();
    }

    public void onSave(){}

    public void onLoad(){}

    public abstract void start();

    public abstract void update();
}

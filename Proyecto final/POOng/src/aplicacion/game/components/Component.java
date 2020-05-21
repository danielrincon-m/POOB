package aplicacion.game.components;

import aplicacion.game.components.common.Transform;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;

import java.io.Serializable;

/**
 * Clase abstracata de la que se derivan todos los componentes
 */
public abstract class Component implements Serializable {

    protected Entity parent;
    protected Transform transform;

    protected EntityManager entityManager;

    /**
     * @param parent La Entidad que contiene este componente
     */
    public Component(Entity parent) {
        this(parent, parent);
        transform = parent.getComponent(Transform.class);
    }

    public Component(Entity parent, Entity Dont_use_this) {
        this.parent = parent;
        entityManager = parent.getEntityManager();
    }

    /**
     * Esta función es llamada una sola vez, antes del primer llamado a update del componente
     */
    public abstract void start();

    /**
     * Esta funcion es llamada en cada frame del juego
     */
    public abstract void update();
}

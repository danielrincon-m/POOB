package aplicacion.game.entitiy;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Component;
import aplicacion.game.components.common.Transform;

import java.io.Serializable;
import java.util.ArrayList;

public class Entity implements Serializable {

    protected String name;

    protected ArrayList<Component> components = new ArrayList<>();

    private final EntityManager entityManager;
    protected Transform transform;

    public Entity(String name, EntityManager entityManager) {
        this.name = name;
        this.entityManager = entityManager;
    }

    public void startAllComponents() {
        for (Component component : components) {
            component.start();
        }
    }

    public void updateAllComponents() {
        for (Component component : components) {
            component.update();
        }
    }

    public void onSaveAllComponents() {
        for (Component component : components) {
            component.onSave();
        }
    }

    public void onLoadAllComponents() {
        for (Component component : components) {
            component.onLoad();
        }
    }


    //Entity
    public String getName() {
        return name;
    }


    //Components
    public void addComponent(Component c) {
        for (Component component : components) {
            if (component.getClass().equals(c.getClass())) {
                throw new EntityException(EntityException.COMPONENT_ALREADY_ADDED);
            }
        }
        components.add(c);
        if (entityManager.isRunning()) {
            c.start();
            entityManager.sortEntities();
        }
    }

    public <T extends Component> T getComponent(Class<T> c) throws EntityException {
        for (Component component : components) {
            if (component.getClass().equals(c)) {
                return c.cast(component);
            }
        }
        throw new EntityException(EntityException.COMPONENT_NOT_FOUND);
    }

    public <T extends Component> boolean hasComponent(Class<T> c) {
        try {
            getComponent(c);
            return true;
        } catch (EntityException e) {
            return false;
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}

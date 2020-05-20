package aplicacion.game.entitiy;

import aplicacion.ApplicationManager;
import aplicacion.exception.EntityException;
import aplicacion.game.components.Component;
import aplicacion.game.components.common.Transform;
import aplicacion.game.utils.Vector2;

import java.io.Serializable;
import java.util.ArrayList;

public class Entity implements Serializable {

    protected String name;

    protected ArrayList<Component> components = new ArrayList<>();

    private final ApplicationManager applicationManager;
    private final EntityManager entityManager;
    protected Transform transform;

    public Entity(ApplicationManager applicationManager, String name) {
        this(applicationManager, name, 0, 0, 0, 0);
    }

    public Entity(ApplicationManager applicationManager, String name, float xPosition, float yPosition, float width, float height) {
        this.applicationManager = applicationManager;
        this.name = name;
        entityManager = applicationManager.getGameManager().getEntityManager();
        createTransform(xPosition, yPosition, width, height);
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

    public ApplicationManager getApplicationManager() {
        return applicationManager;
    }

    private void createTransform(float xPosition, float yPosition, float width, float height) {
        transform = new Transform(this, new Vector2(xPosition, yPosition), new Vector2(width, height));
        addComponent(transform);
    }
}

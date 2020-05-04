package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Collider;
import aplicacion.game.components.Component;
import aplicacion.game.components.RectangleCollider;
import aplicacion.game.components.Transform;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.Vector2;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Entity {

    private static final HashMap<EntityName, Entity> entities = new HashMap<>();

    protected ArrayList<Component> components = new ArrayList<>();
    protected EntityName name;
    protected Transform transform;

    public Entity(EntityName name, float xPosition, float yPosition, float width, float height) {
        this.name = name;
        createTransform(xPosition, yPosition, width, height);
        registerEntity(name);
    }

    //Entities
    public static HashMap<EntityName, Entity> getEntities() {
        return entities;
    }

    public static Entity find(EntityName name) {
        if (!entities.containsKey(name)) {
            throw new EntityException(EntityException.ENTITY_NOT_FOUND);
        }
        return entities.get(name);
    }

    public static void startAll() {
        for (EntityName name : entities.keySet()) {
            Entity e = entities.get(name);
            e.start();
            e.startAllComponents();
        }
    }

    public static void updateAll() {
        for (EntityName name : entities.keySet()) {
            Entity e = entities.get(name);
            e.update();
            e.updateAllComponents();
        }
    }

    public static void removeAll() {
        entities.clear();
    }

    private void startAllComponents() {
        for (Component component : components) {
            component.start();
        }
    }

    private void updateAllComponents() {
        for (Component component : components) {
            component.update();
        }
    }


    //entity
    protected abstract void start();

    protected abstract void update();

    private void registerEntity(EntityName name) throws EntityException {
        if (entities.containsKey(name)) {
            throw new EntityException(EntityException.DUPLICATED_NAME);
        }
        entities.put(name, this);
    }
    

    //Components
    public void addComponent(Component c) {
        for (Component component : components) {
            if (component.getClass().equals(c.getClass())) {
                throw new EntityException(EntityException.COMPONENT_ALREADY_ADDED);
            }
        }
        components.add(c);
    }

    public <T extends Component> T getComponent(Class<T> c) {
        for (Component component : components) {
            if (component.getClass().equals(c)) {
                return c.cast(component);
            }
        }
        throw new EntityException(EntityException.COMPONENT_NOT_FOUND);
    }

    /*public String toString() {
        return "" + position.x + "-" + position.y + "--" + size.x + "-" + size.y;
    }*/



    private void createTransform(float xPosition, float yPosition, float width, float height) {
        transform = new Transform(new Vector2(xPosition, yPosition), new Vector2(width, height));
        addComponent(transform);
    }
}

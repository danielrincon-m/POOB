package aplicacion.game.entities;

import aplicacion.ApplicationManager;
import aplicacion.exception.EntityException;
import aplicacion.game.components.Component;
import aplicacion.game.components.common.Transform;
import aplicacion.game.utils.Vector2;
import aplicacion.game.utils.ZIndexComparator;
import presentacion.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Entity {

    protected String name;
    private static boolean running = false;

    private static final HashMap<String, Entity> entities = new HashMap<>();
    private static final LinkedHashMap<String, Entity> zIndexSortedEntities = new LinkedHashMap<>();
    protected ArrayList<Component> components = new ArrayList<>();

    public ApplicationManager applicationManager;
    protected Transform transform;

    public Entity(ApplicationManager applicationManager, String name) {
        this(applicationManager, name, 0, 0, 0, 0);
        //registerEntity(name);
    }

    public Entity(ApplicationManager applicationManager, String name, float xPosition, float yPosition, float width, float height) {
        this.applicationManager = applicationManager;
        this.name = name;
        createTransform(xPosition, yPosition, width, height);
        //registerEntity(name);
    }

    //Entities
    public static LinkedHashMap<String, Entity> getEntities() {
        return zIndexSortedEntities;
    }

    public static Entity find(String name) throws EntityException {
        if (!entities.containsKey(name)) {
            throw new EntityException(EntityException.ENTITY_NOT_FOUND);
        }
        return entities.get(name);
    }

    public static void defineAllComponents() {
        for (String name : entities.keySet()) {
            Entity e = entities.get(name);
            e.defineComponents();
        }
        sortEntities();
    }

    public static void startAll() {
        for (String name : entities.keySet()) {
            Entity e = entities.get(name);
            e.startAllComponents();
        }
        running = true;
    }

    public static void updateAll() {
        HashMap<String, Entity> fixedEntities = new HashMap<>(entities);
        for (String name : fixedEntities.keySet()) {
            Entity e = fixedEntities.get(name);
            e.updateAllComponents();
        }
    }

    public static void remove(String name) {
        if (!entities.containsKey(name)) {
            throw new EntityException(EntityException.ENTITY_NOT_FOUND);
        }
        entities.remove(name);
        sortEntities();
    }

    public static void removeAll() {
        entities.clear();
        sortEntities();
    }

    public static void registerEntity(Entity entity) throws EntityException {
        if (entities.containsKey(entity.name)) {
            throw new EntityException(EntityException.DUPLICATED_NAME);
        }
        entities.put(entity.name, entity);
        if (running) {
            entity.defineComponents();
            entity.startAllComponents();
        }
        sortEntities();
    }

    private static void sortEntities() {
        ArrayList<Map.Entry<String, Entity>> entryList = new ArrayList<>(entities.entrySet());
        entryList.sort(new ZIndexComparator());
        zIndexSortedEntities.clear();
        for (Map.Entry<String, Entity> entry : entryList) {
            zIndexSortedEntities.put(entry.getKey(), entry.getValue());
        }
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
        if (running && entities.containsKey(name)) {
            c.start();
            sortEntities();
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

    protected abstract void defineComponents();

    private void createTransform(float xPosition, float yPosition, float width, float height) {
        transform = new Transform(this, new Vector2(xPosition, yPosition), new Vector2(width, height));
        addComponent(transform);
    }
}

package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.engine.collision.Collider;
import aplicacion.game.engine.collision.RectangleCollider;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.Vector2;

import java.util.HashMap;

public abstract class Entity {

    private static HashMap<EntityName, Entity> entities = new HashMap<>();

    protected EntityName name;
    protected Vector2 position = new Vector2();
    protected Vector2 size = new Vector2();
    protected Collider collider;

    public Entity(EntityName name, float xPosition, float yPosition, float width, float height) {
        this.name = name;
        registerEntity(name);
        this.position.x = xPosition;
        this.position.y = yPosition;
        this.size.x = width;
        this.size.y = height;
    }

    public static void startAll() {
        for (EntityName name : entities.keySet()) {
            entities.get(name).addCollider();
            entities.get(name).start();
        }
    }

    public static void updateAll() {
        for (EntityName name : entities.keySet()) {
            entities.get(name).collider.update();
            entities.get(name).update();
        }
    }

    public static void removeAll() {
        entities.clear();
    }

    public static Entity find(EntityName name) {
        if (!entities.containsKey(name)) {
            throw new EntityException(EntityException.GAMEOBJECT_NOT_FOUND);
        }
        return entities.get(name);
    }

    public float getxPosition() {
        return position.x;
    }

    public float getCenterXPosition() {
        return position.x + (size.x / 2f);
    }

    public float getyPosition() {
        return position.y;
    }

    public float getCenterYPosition() {
        return position.y + (size.y / 2f);
    }

    public float getWidth() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }

    public Collider getCollider() {
        return collider;
    }

    public String toString() {
        return "" + position.x + "-" + position.y + "--" + size.x + "-" + size.y;
    }

    protected abstract void start();

    protected abstract void update();

    private void registerEntity(EntityName name) throws EntityException {
        if (entities.containsKey(name)) {
            throw new EntityException(EntityException.DUPLICATED_NAME);
        }
        entities.put(name, this);
    }

    private void addCollider() {
        this.collider = new RectangleCollider(this);
    }
}

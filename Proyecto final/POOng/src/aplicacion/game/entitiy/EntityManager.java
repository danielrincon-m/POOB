package aplicacion.game.entitiy;

import aplicacion.exception.EntityException;
import aplicacion.game.utils.ZIndexComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EntityManager implements Serializable {

    private boolean running = false;

    private final HashMap<String, Entity> entities = new HashMap<>();
    private final ArrayList<Entity> newEntitiesQueue = new ArrayList<>();
    private final ArrayList<String> removedEntitiesQueue = new ArrayList<>();
    private final LinkedHashMap<String, Entity> zIndexSortedEntities = new LinkedHashMap<>();

    //Entities
    public LinkedHashMap<String, Entity> getEntities() {
        return new LinkedHashMap<>(zIndexSortedEntities);
    }

    public Entity find(String name) throws EntityException {
        if (!entities.containsKey(name)) {
            throw new EntityException(EntityException.ENTITY_NOT_FOUND);
        }
        return entities.get(name);
    }

    public void startAll() {
        for (String name : entities.keySet()) {
            Entity e = entities.get(name);
            e.startAllComponents();
        }
        running = true;
    }

    public void updateAll() {
        for (String name : entities.keySet()) {
            Entity e = entities.get(name);
            e.updateAllComponents();
        }
        removeQueuedEntities();
        registerQueuedEntities();
    }

    public void remove(String name) {
        if (!entities.containsKey(name)) {
            throw new EntityException(EntityException.ENTITY_NOT_FOUND);
        }
        if (running) {
            removedEntitiesQueue.add(name);
        } else {
            entities.remove(name);
            sortEntities();
        }
    }

    private void removeQueuedEntities() {
        for (String name : removedEntitiesQueue) {
            entities.remove(name);
        }
        removedEntitiesQueue.clear();
        sortEntities();
    }

    public void removeAll() {
        entities.clear();
        sortEntities();
        running = false;
    }

    public void registerEntity(Entity entity) throws EntityException {
        if (entities.containsKey(entity.name) && !removedEntitiesQueue.contains(entity.name)) {
            throw new EntityException(EntityException.DUPLICATED_NAME);
        }
        if (running) {
            newEntitiesQueue.add(entity);
        } else {
            entities.put(entity.name, entity);
            sortEntities();
        }
    }

    private void registerQueuedEntities() {
        for (Entity e : newEntitiesQueue) {
            entities.put(e.name, e);
            e.startAllComponents();
        }
        newEntitiesQueue.clear();
        sortEntities();
    }

    public void sortEntities() {
        ArrayList<Map.Entry<String, Entity>> entryList = new ArrayList<>(entities.entrySet());
        entryList.sort(new ZIndexComparator());
        zIndexSortedEntities.clear();
        for (Map.Entry<String, Entity> entry : entryList) {
            zIndexSortedEntities.put(entry.getKey(), entry.getValue());
        }
    }

    public boolean isRunning() {
        return running;
    }
}

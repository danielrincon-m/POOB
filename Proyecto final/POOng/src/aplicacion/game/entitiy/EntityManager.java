package aplicacion.game.entitiy;

import aplicacion.exception.EntityException;
import aplicacion.game.utils.ZIndexComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que contiene y maneja todas las entidades del juego
 */
public class EntityManager implements Serializable {

    private boolean running = false;

    private final HashMap<String, Entity> entities = new HashMap<>();
    private final ArrayList<Entity> newEntitiesQueue = new ArrayList<>();
    private final ArrayList<String> removedEntitiesQueue = new ArrayList<>();
    private final LinkedHashMap<String, Entity> zIndexSortedEntities = new LinkedHashMap<>();

    //Entities

    /**
     * @return Las entidades del juego ordenadas por zIndex
     */
    public LinkedHashMap<String, Entity> getEntities() {
        return zIndexSortedEntities;
    }

    /**
     * Busca una entidad en las Entidades del jugo
     * @param name El nombre de la Entidad
     * @return La entidad encontrada
     * @throws EntityException Si la entidad no fue encontrada
     */
    public Entity find(String name) throws EntityException {
        if (!entities.containsKey(name)) {
            throw new EntityException(EntityException.ENTITY_NOT_FOUND);
        }
        return entities.get(name);
    }

    /**
     * Inicializa todas las Entidades
     */
    public void startAll() {
        for (String name : entities.keySet()) {
            Entity e = entities.get(name);
            e.startAllComponents();
        }
        running = true;
    }

    /**
     * Llama a update en todas las entidades
     */
    public void updateAll() {
        for (String name : entities.keySet()) {
            Entity e = entities.get(name);
            e.updateAllComponents();
        }
        removeQueuedEntities();
        registerQueuedEntities();
    }

    /**
     * Remueve una Entidad
     * @param name El nombre de la Entidad
     */
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

    /**
     * Remueve las entidades en la cola de remoción
     */
    private void removeQueuedEntities() {
        for (String name : removedEntitiesQueue) {
            entities.remove(name);
        }
        removedEntitiesQueue.clear();
        sortEntities();
    }

    /**
     * Remueve todas las entidades
     */
    public void removeAll() {
        entities.clear();
        sortEntities();
        running = false;
    }

    /**
     * Registra una nueva Entidad
     * @param entity La Entidad a registrar
     * @throws EntityException Cuando una Etnidad con el mismo nombre ya existe
     */
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

    /**
     * Registra las entidades en la cola de registro
     */
    private void registerQueuedEntities() {
        for (Entity e : newEntitiesQueue) {
            entities.put(e.name, e);
            e.startAllComponents();
        }
        newEntitiesQueue.clear();
        sortEntities();
    }

    /**
     * Ordena las entidades por zIndex, y las almacena en un LinkedHashMap
     */
    public void sortEntities() {
        ArrayList<Map.Entry<String, Entity>> entryList = new ArrayList<>(entities.entrySet());
        entryList.sort(new ZIndexComparator());
        zIndexSortedEntities.clear();
        for (Map.Entry<String, Entity> entry : entryList) {
            zIndexSortedEntities.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * @return Si la aplicación está corriendo
     */
    public boolean isRunning() {
        return running;
    }
}

package aplicacion;

import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.entitiy.EntityManager;
import aplicacion.game.entitiy.EntitySpawner;

import java.io.Serializable;

public class GameManager implements Serializable, TimerListener {

    private EntityManager entityManager;
    private final EntitySpawner entitySpawner;

    /**
     * Constructor de la clase encargada de manejar el estado general del juego
     *
     * @param applicationManager El application manager del juego
     */
    public GameManager(ApplicationManager applicationManager) {
        entityManager = new EntityManager();
        entitySpawner = new EntitySpawner(applicationManager, entityManager);
    }

    /**
     * Iniciar un nuevo juego
     */
    public void startGame() {
        createEntities();
        entityManager.startAll();
    }

    /**
     * Actualizar el estado del juego, esta función debe ser llamada una vez cada frame
     */
    public void update() {
        entityManager.updateAll();
    }

    /**
     * @return El EntityManager del juego
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Crea todas las entidades del juego
     */
    private void createEntities() {
        entityManager.removeAll();
        entitySpawner.SpawnObjects();
    }
}

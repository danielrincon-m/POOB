package aplicacion;

import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;
import aplicacion.game.entitiy.EntitySpawner;

import java.io.Serializable;

public class GameManager implements Serializable, TimerListener {

    private final ApplicationManager applicationManager;
    private EntityManager entityManager;
    private EntitySpawner entitySpawner; //FIXME: Pasar esto a entityManager?
    private GameTimer gameTimer;

    /**
     * Constructor de la clase encargada de manejar el estado general del juego
     *
     * @param applicationManager El application manager del juego
     */
    public GameManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        gameTimer = new GameTimer();
        entityManager = new EntityManager();
        entitySpawner = new EntitySpawner(applicationManager, entityManager);
    }

    /**
     * Iniciar un nuevo juego
     */
    public void startGame() {
        intializeParameters();
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
     * Inicializa todos los objetos necesarios para poder iniciar el juego
     */
    private void intializeParameters() {
        entityManager.removeAll();
        entitySpawner = new EntitySpawner(applicationManager);
    }

    /**
     * Crea todas las entidades del juego
     */
    private void createEntities() {
        entityManager.removeAll();
        entitySpawner.SpawnObjects();
    }
}

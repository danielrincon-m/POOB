package aplicacion;

import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;
import aplicacion.game.entitiy.EntitySpawner;

import java.util.LinkedHashMap;

public class GameManager implements TimerListener {

    private final ApplicationManager applicationManager;
    private EntityManager entityManager;
    private EntitySpawner entitySpawner; //FIXME: Pasar esto a entityManager?
    private GameTimer gameTimer;

    /**
     * Constructor de la clase encargada de manejar el estado general del juego
     * @param applicationManager El application manager del juego
     */
    public GameManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        gameTimer = new GameTimer();
        entityManager = new EntityManager();
    }

    /**
     * Iniciar un nuevo juego
     */
    public void startGame() {
        intializeParameters();
        createEntities();
        entityManager.startAll();
        gameTimer.addTimerListener(this, 1);
        gameTimer.start();
    }

    /**
     * Actualizar el estado del juego, esta función debe ser llamada una vez cada frame
     */
    public void update() {
        entityManager.updateAll();
    }

    /**
     * Pausar el estado del juego
     */
    public void pauseGame() {
        if (gameTimer.isStarted()) {
            LinkedHashMap<TimerListener, Integer> timerListeners = gameTimer.getListeners();
            gameTimer.cancel();
            gameTimer.purge();
            gameTimer = new GameTimer(timerListeners);
        }
    }

    /**
     * Continuar el estado del juego si está pausado
     */
    public void resumeGame() {
        if (!gameTimer.isStarted()) {
            gameTimer.start();
        }
    }

    public boolean isPaused() {
        return !gameTimer.isStarted();
    }

    /**
     * Finalizar el juego, detiene el timer y remueve todas las entidades
     */
    public void endGame() {
        gameTimer.cancel();
        gameTimer.purge();
        gameTimer = new GameTimer();
//        entityManager.removeAll();
    }

    /**
     * @return El EntityManager del juego
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @return El objeto GameTimer del juego
     */
    public GameTimer getGameTimer() {
        return gameTimer;
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
        entitySpawner.SpawnObjects();
    }
}

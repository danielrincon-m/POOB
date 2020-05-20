package aplicacion;

import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;
import aplicacion.game.entitiy.EntitySpawner;

import java.io.*;
import java.util.LinkedHashMap;

public class GameManager implements TimerListener {

    private boolean started = false;

    private final ApplicationManager applicationManager;
    private EntityManager entityManager;
    private GameTimer gameTimer;

    /**
     * Constructor de la clase encargada de manejar el estado general del juego
     * @param applicationManager El application manager del juego
     */
    public GameManager(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        gameTimer = new GameTimer();
        entityManager = new EntityManager();
        new Pause(this);
    }

    /**
     * Iniciar un nuevo juego
     */
    public void startGame() {
        started = true;
        createEntities();
        entityManager.startAll();
        initTimer();
    }

    /**
     * Finalizar el juego, detiene el timer y remueve todas las entidades
     */
    public void endGame() {
        started = false;
        endTimer();
        entityManager.removeAll();
    }

    public boolean gameStarted() {
        return started;
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
            endTimer(timerListeners);
        }
    }

    public boolean isPaused() {
        return !gameTimer.isStarted();
    }

    /**
     * Continuar el estado del juego si está pausado
     */
    public void resumeGame() {
        if (!gameTimer.isStarted()) {
            gameTimer.start();
        }
    }

    public void save(File location) {
        entityManager.onSaveAll();
        try {
            FileOutputStream fos = new FileOutputStream(location);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entityManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entityManager.onLoadAll();
    }

    public void load(File saveGame) {
        try {
            FileInputStream fis = new FileInputStream(saveGame);
            ObjectInputStream ois = new ObjectInputStream(fis);
            entityManager = (EntityManager) ois.readObject();

            entityManager.onLoadAll();
            endTimer();
            initTimer();
            started = true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return El objeto GameTimer del juego
     */
    public GameTimer getGameTimer() {
        return gameTimer;
    }

    /**
     * Crea todas las entidades del juego
     */
    private void createEntities() {
        new EntitySpawner(applicationManager, entityManager).SpawnObjects();
    }

    private void initTimer() {
        //gameTimer.addTimerListener(this, 1);
        gameTimer.start();
    }

    private void endTimer() {
        gameTimer.cancel();
        gameTimer.purge();
        gameTimer = new GameTimer();
    }

    private void endTimer(LinkedHashMap<TimerListener, Integer> timerListeners) {
        gameTimer.cancel();
        gameTimer.purge();
        gameTimer = new GameTimer(timerListeners);
    }


    //Entity API
    public LinkedHashMap<String, Entity> getAllEntities() {
        return entityManager.getEntities();
    }

    public Entity findEntity(String name) {
        return entityManager.find(name);
    }
}

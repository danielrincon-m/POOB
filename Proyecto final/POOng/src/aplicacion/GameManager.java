package aplicacion;

import aplicacion.game.components.winner.WinNotifier;
import aplicacion.game.engine.Pause;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;
import aplicacion.game.entitiy.EntitySpawner;
import aplicacion.game.enums.GameState;

import java.io.*;
import java.util.LinkedHashMap;

/**
 * Clase que se encarga de manejar los aspectos de la jugabilidad
 */
public class GameManager {

    private GameState gameState = GameState.ENDED;

    private final ApplicationManager applicationManager;
    private EntityManager entityManager;
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
        new Pause(this);
    }

    /**
     * Iniciar un nuevo juego
     */
    public void startGame() {
        createEntities();
        entityManager.startAll();
        initTimer();
    }

    /**
     * Finalizar el juego, detiene el timer y remueve todas las entidades
     */
    public void endGame() {
        endTimer();
        entityManager.removeAll();
        gameState = GameState.ENDED;
    }

    /**
     * Verifica si el juego acabó por alguno de sus factores internos, y detiene el tiempo
     */
    private void checkGameEnd() {
        WinNotifier winNotifier = entityManager.find("WIN_NOTIFIER").getComponent(WinNotifier.class);
        if (winNotifier.someoneWined()) {
            gameState = GameState.ENDING;
            endTimer();
        }
    }

    /**
     * Actualizar el estado del juego, esta función debe ser llamada una vez cada frame
     */
    public void update() {
        if (gameState == GameState.STARTING) {
            gameState = GameState.RUNNING;
        }
        entityManager.updateAll();
        checkGameEnd();
    }

    /**
     * Pausar el estado del juego
     */
    public void pauseGame() {
        if (gameTimer.isStarted()) {
            LinkedHashMap<TimerListener, Integer> timerListeners = gameTimer.getListeners();
            endTimer(timerListeners);
            gameState = GameState.PAUSED;
        }
    }

    /**
     * Continuar el estado del juego si está pausado
     */
    public void resumeGame() {
        if (!gameTimer.isStarted()) {
            initTimer();
        }
    }

    /**
     * @return El estado actual del juego
     */
    public GameState getGameState() {
        return gameState;
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


    //Timer Control

    /**
     * Inicia el timer del juego
     */
    private void initTimer() {
        gameTimer.start();
        gameState = GameState.STARTING;
    }

    /**
     * Finaliza el timer del juego y crea una nueva instancia para su uso
     */
    private void endTimer() {
        gameTimer.cancel();
        gameTimer.purge();
        gameTimer = new GameTimer();
    }

    /**
     * Finaliza el timer del juego y crea una nueva instancia basandose en una lista de oyentes
     *
     * @param timerListeners Lista de oyentes del timer
     */
    private void endTimer(LinkedHashMap<TimerListener, Integer> timerListeners) {
        gameTimer.cancel();
        gameTimer.purge();
        gameTimer = new GameTimer(timerListeners);
    }


    //Entity API

    /**
     * @return Todas las entidades ordenadas por zIndex del EntityManager
     */
    public LinkedHashMap<String, Entity> getAllEntities() {
        return entityManager.getEntities();
    }

    /**
     * @param name El nombre de la Entidad
     * @return La Entidad con el nombre dado
     */
    public Entity findEntity(String name) {
        return entityManager.find(name);
    }


    //Persistency

    /**
     * Guarda el estado del juego en un archivo
     *
     * @param location El archivo en donde se  guardará
     */
    public void save(File location) {
        try {
            FileOutputStream fos = new FileOutputStream(location);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entityManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el juego guardado en un archivo
     *
     * @param saveGame El archivo de donde se cargará
     */
    public void load(File saveGame) {
        try {
            FileInputStream fis = new FileInputStream(saveGame);
            ObjectInputStream ois = new ObjectInputStream(fis);
            entityManager = (EntityManager) ois.readObject();

            endTimer();
            initTimer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

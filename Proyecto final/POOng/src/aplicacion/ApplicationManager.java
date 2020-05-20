package aplicacion;

import aplicacion.exception.ApplicationException;
import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.engine.timer.TimerManager;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;

import java.io.*;
import java.util.LinkedHashMap;

public class ApplicationManager implements Serializable {

    boolean gameStarted = false;

    private GameManager gameManager;
    private GameProperties gameProperties;


    /**
     * Genera un ApplicationManager con todos sus componentes por defecto
     */
    public ApplicationManager() {
        ResourceManager.getInstance().setApplicationManager(this);
        gameProperties = new GameProperties();
        gameManager = new GameManager(this);
    }

    /**
     * @return El objeto GameProperties del juego
     */
    public GameProperties getGameProperties() {
        return gameProperties;
    }

    /**
     * @return El objeto GameManager del juego
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Inicia el juego si todas las propiedades se configuraron correctamente
     */
    public void startGame() {
        if (gameProperties.areValid()) {
            gameManager.startGame();
            TimerManager.getInstance().addTimerListener(gameManager, 1);
            TimerManager.getInstance().start();
            gameStarted = true;
        } else {
            throw new ApplicationException(ApplicationException.INVALID_PROPERTIES);
        }
    }

    /**
     * Termina la ejecución del juego y reinicia las propiedades
     */
    public void endGame() {
        TimerManager.getInstance().cancel();
        gameProperties = new GameProperties();
        gameStarted = false;
    }

    public boolean isPaused() {
        return !TimerManager.getInstance().isStarted();
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Pausar el estado del juego
     */
    public void pauseGame() {
        if (TimerManager.getInstance().isStarted()) {
            LinkedHashMap<TimerListener, Integer> timerListeners = TimerManager.getInstance().getListeners();
            TimerManager.getInstance().cancel();
            TimerManager.getInstance().newFromListeners(timerListeners);
        }
    }

    /**
     * Continuar el estado del juego si está pausado
     */
    public void resumeGame() {
        if (!TimerManager.getInstance().isStarted()) {
            TimerManager.getInstance().start();
        }
    }

    public void saveGame(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameManager.getEntityManager());
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameManager.setEntityManager((EntityManager) ois.readObject());
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        TimerManager.getInstance().addTimerListener(gameManager, 1);
        gameStarted = true;
    }
}

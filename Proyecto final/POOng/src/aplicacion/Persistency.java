package aplicacion;

import aplicacion.game.engine.timer.TimerListener;

import java.io.*;
import java.util.LinkedHashMap;

public class Persistency {
    public static void save(ApplicationManager applicationManager) {
        GameManager gameManager = applicationManager.getGameManager();
        LinkedHashMap<TimerListener, Integer> listeners = gameManager.getGameTimer().getListeners();
        gameManager.getGameTimer().clearListeners();
        gameManager.pauseGame();
        gameManager.getEntityManager().onSaveAll();
        try {
            FileOutputStream fos = new FileOutputStream(new File("Test.dat"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(applicationManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameManager.getEntityManager().onLoadAll();
        gameManager.getGameTimer().setListeners(listeners);
        gameManager.resumeGame();
    }

    public static ApplicationManager load(ApplicationManager currentApplicationManager) {
        GameManager currentGameManager = currentApplicationManager.getGameManager();
        GameManager loadedGameManager;
        ApplicationManager loadedApplicationManager;
        currentGameManager.getGameTimer().cancel();
        try {
            FileInputStream fis = new FileInputStream(new File("Test.dat"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            loadedApplicationManager = (ApplicationManager) ois.readObject();
            loadedGameManager = loadedApplicationManager.getGameManager();
            loadedGameManager.getEntityManager().onLoadAll();
            loadedGameManager.listenTimer();
            loadedGameManager.resumeGame();
            return loadedApplicationManager;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null; //TODO: ERROR
    }
}

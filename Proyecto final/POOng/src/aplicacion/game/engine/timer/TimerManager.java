package aplicacion.game.engine.timer;

import java.util.LinkedHashMap;

public class TimerManager {

    private static TimerManager instance;
    private GameTimer gameTimer;

    private TimerManager() {
        gameTimer = new GameTimer();
    }

    public static TimerManager getInstance() {
        if (instance == null) {
            instance = new TimerManager();
        }
        return instance;
    }

    public void start() {
        gameTimer.start();
    }

    public void cancel() {
        gameTimer.cancel();
    }

    public boolean isStarted() {
        return gameTimer.isStarted();
    }

    public LinkedHashMap<TimerListener, Integer> getListeners() {
        return gameTimer.getListeners();
    }

    public void newFromListeners(LinkedHashMap<TimerListener, Integer> listeners) {
        gameTimer = new GameTimer(listeners);
    }

    public void addTimerListener(TimerListener listener, int priority) {
        gameTimer.addTimerListener(listener, priority);
    }
}

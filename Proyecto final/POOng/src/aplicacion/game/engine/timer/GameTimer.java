package aplicacion.game.engine.timer;

import java.util.*;

public class GameTimer extends Timer {
    private static float deltaTime;
    private static float time;

    private boolean started = false;
    private int droppedFrames;
    private float frameRate;
    private long startTime;
    private long renderedFrames;
    private long lastFrameTime;

    private LinkedHashMap<TimerListener, Integer> listeners = new LinkedHashMap<>();


    public GameTimer() {
        super();
        startTime = System.currentTimeMillis();
        lastFrameTime = System.currentTimeMillis();
    }

    public static float time() {
        return time;
    }

    public static float deltaTime() {
        return deltaTime;
    }

    public void start() {
        scheduleAtFixedRate(new Loop(this), 0, 1000 / 60);
    }

    public void updateGame() {
        calculateTime();
        calculateDeltaTime();
        calculateFrameRate();

        if (droppedFrames > 3) {
            //System.out.println("GAME FRAMES " + deltaTime());
            started = true;
            for (TimerListener listener : listeners.keySet()) {
                listener.update();
            }
        } else {
            //System.out.println("DROPPED FRAMES " + deltaTime());
            droppedFrames++;
        }
    }

    public void addTimerListener(TimerListener listener, int priority) {
        if (!listeners.containsKey(listener)) {
            listeners.put(listener, priority);
            sortListeners();
        }
    }

    public void setListeners(LinkedHashMap<TimerListener, Integer> listeners) {
        this.listeners = listeners;
    }

    public LinkedHashMap<TimerListener, Integer> getListeners() {
        return listeners;
    }

    private void sortListeners() {
        List<Map.Entry<TimerListener, Integer>> entries = new ArrayList<>(listeners.entrySet());
        entries.sort(new Comparator<Map.Entry<TimerListener, Integer>>() {
            @Override
            public int compare(Map.Entry<TimerListener, Integer> e1, Map.Entry<TimerListener, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        listeners.clear();
        for (Map.Entry<TimerListener, Integer> e : entries) {
            listeners.put(e.getKey(), e.getValue());
        }
    }

    public boolean isStarted() {
        return started;
    }

    private void calculateTime() {
        time = time + deltaTime();
    }

    private void calculateDeltaTime() {
        long currentFrameTime = System.currentTimeMillis();
        deltaTime = (float) (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;
    }

    private void calculateFrameRate() {
        frameRate = ((float) renderedFrames / (float) (System.currentTimeMillis() - startTime)) * 1000f;
        renderedFrames++;
    }

    private static class Loop extends TimerTask {

        private final GameTimer gameTimer;

        public Loop(GameTimer gameTimer) {
            super();
            this.gameTimer = gameTimer;
        }

        @Override
        public void run() {
            gameTimer.updateGame();
        }
    }
}
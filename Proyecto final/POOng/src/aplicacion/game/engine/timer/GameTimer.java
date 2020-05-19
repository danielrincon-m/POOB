package aplicacion.game.engine.timer;

import java.util.*;

/**
 * Un timer que se ejecuta 60 veces por segundo para generar el GameLoop
 */
public class GameTimer extends Timer {
    private static float deltaTime;
    private static float time;

    private boolean started = false;
    private int droppedFrames;
    private float frameRate;
    private long lastFrameTime;

    private LinkedHashMap<TimerListener, Integer> listeners = new LinkedHashMap<>();

    /**
     * Constructor del GameTimer
     */
    public GameTimer() {
        super();
        lastFrameTime = System.currentTimeMillis();
    }

    /**
     * @return El tiempo transcurrido desde que se inició el timer
     */
    public static float time() {
        return time;
    }

    /**
     * @return El tiempo transcurrido desde que se inició la renderización del frame anterior hasta que se inició
     * la renderización de este frame
     */
    public static float deltaTime() {
        return deltaTime;
    }

    /**
     * Inicia el timer
     */
    public void start() {
        scheduleAtFixedRate(new Loop(this), 0, 1000 / 60);
    }

    /**
     * Calcula los parámetros específicos de cada frame y llama a los listeners que se hayan registrado en el timer
     */
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

    /**
     * Agrega un nuevo listener para llamar a ls función update en cada frame
     * @param listener La clase que escucha
     * @param priority La prioridad de su llamada
     */
    public void addTimerListener(TimerListener listener, int priority) {
        if (!listeners.containsKey(listener)) {
            listeners.put(listener, priority);
            sortListeners();
        }
    }

    /**
     * Importa una lista de listeners a esta clase
     * @param listeners Los listeners a importar
     */
    public void setListeners(LinkedHashMap<TimerListener, Integer> listeners) {
        this.listeners = listeners;
    }

    /**
     * Exporta los listeners registrados en esta clase
     * @return Los listeners registrados
     */
    public LinkedHashMap<TimerListener, Integer> getListeners() {
        return listeners;
    }

    /**
     * Ordena los listeners por priodidad para su ejecución
     */
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

    /**
     * @return Si el timer se inició
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Calcula el tiempo transcurrido desde que se inició el timer
     */
    private void calculateTime() {
        time = time + deltaTime();
    }

    /**
     * Calcula el tiempo entre el inicio del frame anterior y el inicio de este frame
     */
    private void calculateDeltaTime() {
        long currentFrameTime = System.currentTimeMillis();
        deltaTime = (float) (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;
    }

    /**
     * Calcula el framerate del último frame
     */
    private void calculateFrameRate() {
        frameRate = 1f / deltaTime();
    }

    /**
     * Clase auxiliar del timer para realizar la ejeución cada frame
     */
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

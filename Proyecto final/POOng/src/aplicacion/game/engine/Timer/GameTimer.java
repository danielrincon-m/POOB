package aplicacion.game.engine.Timer;

import aplicacion.GameManager;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
    private static float deltaTime;
    private static float time;

    private int droppedFrames;
    private float frameRate;
    private long startTime;
    private long renderedFrames;
    private long lastFrameTime;

    private GameManager gameManager;

    public GameTimer(GameManager gameManager) {
        super();
        this.gameManager = gameManager;
        startTime = System.currentTimeMillis();
        lastFrameTime = System.currentTimeMillis();
    }

    public void start() {
        scheduleAtFixedRate(new Loop(this), 0, 1000 / 60);
    }

    public void updateGame() {
        calculateTime();
        calculateDeltaTime();
        calculateFrameRate();

        if (droppedFrames > 3) {
            gameManager.update();
        } else {
            droppedFrames++;
        }
//        System.out.println(frameRate);
//        System.out.println(deltaTime);
    }

    public static float time() {
        return time;
    }

    public static float deltaTime() {
        return deltaTime;
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

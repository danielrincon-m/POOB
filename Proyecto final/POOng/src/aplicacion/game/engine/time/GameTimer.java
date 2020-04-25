package aplicacion.game.engine.time;

import aplicacion.game.GameManager;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
    private GameManager gameManager;

    private float frameRate;
    private float deltaTime;
    private long startTime;
    private long renderedFrames;
    private long lastFrameTime;

    public GameTimer() {
        super();
        gameManager = GameManager.getInstance();
        startTime = System.currentTimeMillis() - 1;
    }

    public void start() {
        schedule(new Loop(this), 0, 1000 / 60);
    }

    public void updateGame() {
        calculateDeltaTime();
        calculateFrameRate();
        gameManager.update();
//        System.out.println(frameRate);
//        System.out.println(deltaTime);
    }

    private void calculateDeltaTime() {
        long currentFrameTime = System.currentTimeMillis();
        deltaTime = (float)(currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;
    }

    private void calculateFrameRate() {
        frameRate = ((float) renderedFrames / (float) (System.currentTimeMillis() - startTime)) * 1000f;
        renderedFrames++;
    }

    private class Loop extends TimerTask {

        GameTimer gameTimer;

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

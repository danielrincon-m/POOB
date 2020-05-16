package presentacion;

import aplicacion.GameManager;
import aplicacion.ResourceManager;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.player.PlayerEnergy;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.engine.Timer.TimerListener;
import aplicacion.game.entitiy.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

public class GameScreen extends Screen implements TimerListener {

    GameTimer gameTimer;
    GameManager gameManager;
    ResourceManager resourceManager;

    public GameScreen(Application application) {
        super(application);
        gameManager = application.getApplicationManager().getGameManager();
        resourceManager = application.getApplicationManager().getResourceManager();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameTimer.isStarted()) {
            gameManager.update();
            drawSprites(g);
            drawStats(g);
        }
    }

    @Override
    public void update() {
        repaint();
    }

    private void drawSprites(Graphics g) {
        LinkedHashMap<String, Entity> entities = Entity.getEntities();
        for (String name : entities.keySet()) {
            Transform entityTransform = entities.get(name).getComponent(Transform.class);
            int x = (int) entityTransform.getPosition().x;
            int y = (int) entityTransform.getPosition().y;
            int width = (int) entityTransform.getSize().x;
            int height = (int) entityTransform.getSize().y;
            if (entities.get(name).hasComponent(Sprite.class)) {
                Sprite entitySprite = entities.get(name).getComponent(Sprite.class);
                BufferedImage image = resourceManager.getSprite(entitySprite.getImageName());
                g.drawImage(image, x, y, width, height, null);
            } else {
                BufferedImage image = resourceManager.getSprite("resources/sprites/not_implemented.png");
                g.drawImage(image, x, y, width, height, null);
            }
        }
    }

    private void drawStats(Graphics g) {
        String energyTop = String.format("%.1f", Entity.find("PLAYER_TOP").getComponent(PlayerEnergy.class).getEnergy());
        String energyBottom = String.format("%.1f", Entity.find("PLAYER_BOTTOM").getComponent(PlayerEnergy.class).getEnergy());
        Score score = Entity.find("SCORE_BOARD").getComponent(Score.class);
        String scoreTop = String.valueOf(score.getScore(true));
        String scoreBottom = String.valueOf(score.getScore(false));

        Font font;
        g.setColor(Color.WHITE);
        font = new Font("Monospaced", Font.PLAIN, 72);
        g.setFont(font);
        g.drawString(scoreTop, 10, 250);
        g.drawString(scoreBottom, 650, 550);
        font = new Font("Monospaced", Font.PLAIN, 18);
        g.setFont(font);
        g.drawString("Energy: " + energyTop, 10, 270);
        g.drawString("Energy: " + energyBottom, 650, 570);
    }

    public void registerTimeListener() {
        gameTimer = application.getApplicationManager().getGameManager().getGameTimer();
        gameTimer.addTimerListener(this, 0);
    }

    @Override
    protected void prepareElements() {
        fondoInicial = "resources/fondodejuego.png";
        setFondo();
    }

    @Override
    protected void prepareAccionesElemento() {

    }

/*    private void startGame() {
        applicationManager.startGame();
    }*/
}

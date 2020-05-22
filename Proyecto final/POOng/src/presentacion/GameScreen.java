package presentacion;

import aplicacion.GameManager;
import aplicacion.ResourceManager;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.player.PlayerEnergy;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.engine.timer.TimerListener;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

/**
 * Clase de la pantalla en donde se desarrolla el juego
 */
public class GameScreen extends Screen implements TimerListener {

    /**
     * @param application la instancia de la clase principal Application
     */
    public GameScreen(Application application) {
        super(application);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean reaadySign = false;
        if (application.getApplicationManager().getGameManager().getGameTimer().isStarted()) {
            application.getApplicationManager().getGameManager().update();
        } else if (application.getApplicationManager().getGameManager().getGameState() == GameState.STARTING) {
            reaadySign = true;
        }
        drawSprites(g);
        drawStats(g);
        drawInfo(g);
        if (reaadySign) {
            drawReady(g);
        }
    }

    @Override
    public void update() {
        repaint();
    }

    /**
     * Dibuja un mensaje diciendole al jugador que se aliste
     *
     * @param g .
     */
    private void drawReady(Graphics g) {
        String ready = "Alistate!";
        Font font;
        g.setColor(Color.ORANGE);
        font = new Font("Serif", Font.ITALIC, 70);
        g.setFont(font);
        g.drawString(ready, 280, 370);
    }

    /**
     * Dibuja todas las Entidades que puedan ser dibujadas
     *
     * @param g .
     */
    private void drawSprites(Graphics g) {
        ResourceManager resourceManager = application.getApplicationManager().getResourceManager();
        LinkedHashMap<String, Entity> entities = application.getApplicationManager().getGameManager().getAllEntities();
        for (String name : entities.keySet()) {
            Transform entityTransform = entities.get(name).getComponent(Transform.class);
            int x = (int) entityTransform.getPosition().x;
            int y = (int) entityTransform.getPosition().y;
            int width = (int) entityTransform.getSize().x;
            int height = (int) entityTransform.getSize().y;
            if (entities.get(name).hasComponent(Sprite.class)) {
                Sprite entitySprite = entities.get(name).getComponent(Sprite.class);
                BufferedImage image = resourceManager.getSprite(entitySprite.getImagePath());
                g.drawImage(image, x, y, width, height, null);
            } else {
                BufferedImage image = resourceManager.getSprite("resources/sprites/not_implemented.png");
                g.drawImage(image, x, y, width, height, null);
            }
        }
    }

    /**
     * Dibuja las estadísticas de cada uno de los jugadores
     *
     * @param g .
     */
    private void drawStats(Graphics g) {
        GameManager gameManager = application.getApplicationManager().getGameManager();
        String energyTop = String.format("%.1f", gameManager.findEntity("PLAYER_TOP").getComponent(PlayerEnergy.class).getEnergy());
        String energyBottom = String.format("%.1f", gameManager.findEntity("PLAYER_BOTTOM").getComponent(PlayerEnergy.class).getEnergy());
        Score score = gameManager.findEntity("SCORE_BOARD").getComponent(Score.class);
        String scoreTop = String.format("%02d", score.getScore(true));
        String scoreBottom = String.format("%02d", (score.getScore(false)));

        Font font;
        g.setColor(Color.WHITE);
        font = new Font("Monospaced", Font.PLAIN, 85);
        g.setFont(font);
        g.drawString(scoreTop, 25, 230);
        g.drawString(scoreBottom, 665, 530);
        font = new Font("Monospaced", Font.PLAIN, 16);
        g.setFont(font);
        g.drawString("Energia: " + energyTop, 10, 270);
        g.drawString("Energia: " + energyBottom, 650, 570);
    }

    /**
     * Dibuja información para el usuario en pantalla
     *
     * @param g .
     */
    private void drawInfo(Graphics g) {
        String pauseInfo = "Presiona P \npara pausar";
        Font font;
        g.setColor(Color.WHITE);
        font = new Font("Monospaced", Font.PLAIN, 12);
        g.setFont(font);
        g.drawString(pauseInfo, 10, 780);
    }

    /**
     * Registra esta pantalla en el GameTimer para que la actualice
     */
    public void registerTimeListener() {
        GameTimer gameTimer = application.getApplicationManager().getGameManager().getGameTimer();
        gameTimer.addTimerListener(this, 0);
    }

    @Override
    protected void prepareElementos() {
        fondoInicial = "resources/fondodejuego.png";
        setFondo();
    }

    @Override
    protected void prepareAccionesElemento() {

    }
}

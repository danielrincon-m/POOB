package presentacion;

import aplicacion.GameManager;
import aplicacion.game.components.player.PlayerState;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.components.winner.WinNotifier;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de la pantalla final
 */
public class EndScreen extends Screen {

    JButton volver;

    /**
     * @param application la instancia de la clase principal Application
     */
    public EndScreen(Application application) {
        super(application);
    }

    @Override
    protected void prepareElementos() {
        fondoInicial = "resources/fondofinal.png";
        setFondo();
        volver = new JButton("Pantalla Principal");
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createEmptyBorder(690, 250, 30, 250));
        add(volver);
    }

    @Override
    protected void prepareAccionesElemento() {
        volver.addActionListener(e -> application.nuevo());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarPropiedades(g);
    }

    /**
     * Dibuja las propiedades en las que finalizó el juego
     *
     * @param g .
     */
    private void dibujarPropiedades(Graphics g) {
        GameManager gameManager = application.getApplicationManager().getGameManager();
        WinNotifier winNotifier = gameManager.findEntity("WIN_NOTIFIER").getComponent(WinNotifier.class);
        Score score = gameManager.findEntity("SCORE_BOARD").getComponent(Score.class);
        FieldSide winnerSide = winNotifier.getWinner();
        String playerName = GameUtils.getPlayerNameBySide(winnerSide);
        CharacterPersonality winnerPersonality = gameManager.findEntity(playerName).
                getComponent(PlayerState.class).getPersonality();

        String reason = winNotifier.getWinReason();
        String winnerString = winnerPersonality.getName();

        Font font;
        g.setColor(Color.DARK_GRAY);

        font = new Font("Serif", Font.PLAIN, 36);
        g.setFont(font);
        g.drawString(score.getScore(true) + " - " + score.getScore(false), 350, 330);
        g.drawString(winnerString, 330, 470);
        font = new Font("Serif", Font.PLAIN, 24);
        g.setFont(font);
        g.drawString(reason, 190, 615);
    }
}
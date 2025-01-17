package aplicacion.game.components.scoreBoard;

import aplicacion.game.components.Component;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.winner.WinNotifier;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;

import java.util.HashMap;

/**
 * Componente de la Entidad Score que se encarga de llevar el Score de los jugadores
 */
public class Score extends Component {

    private int maxScore;

    private HashMap<FieldSide, Integer> score;

    private BallMovement ballMovement;
    private Transform ballTransform;
    private FieldBounds fieldBounds;
    private WinNotifier winNotifier;

    /**
     * @param parent   La entidad que contiene este componente
     * @param maxScore El puntaje máximo
     */
    public Score(Entity parent, int maxScore) {
        super(parent);
        this.maxScore = maxScore;
    }

    @Override
    public void start() {
        ballMovement = entityManager.find("BALL").getComponent(BallMovement.class);
        ballTransform = entityManager.find("BALL").getComponent(Transform.class);
        fieldBounds = entityManager.find("FIELD").getComponent(FieldBounds.class);
        winNotifier = entityManager.find("WIN_NOTIFIER").getComponent(WinNotifier.class);
        initializeScore();
    }

    @Override
    public void update() {
        checkWinner();
    }

    /**
     * Calcula quién realizó el punto y lo suma a su registro
     *
     * @return El lado del ganador
     */
    public FieldSide caclulateBallScore() {
        FieldSide winner = null;
        if (!fieldBounds.insideX(ballTransform.getCenterPosition())) {
            winner = GameUtils.getOtherSide(ballMovement.getLastHitterSide());
            addScore(winner, 1);
        } else if (!fieldBounds.insideY(ballTransform.getCenterPosition())) {
            winner = ballMovement.getLastHitterSide();
            addScore(winner, 1);
        }
        return winner;
    }

    /**
     * Agrega puntos al jugador dado
     *
     * @param winner El jugador que ganó puntos
     * @param amount La cantidad de puntos ganados por el jugador
     */
    public void addScore(FieldSide winner, int amount) {
        score.put(winner, score.get(winner) + amount);
    }

    /**
     * @param top Si es del jugador en Top
     * @return La cantidad de puntos realizados por el jugador
     */
    public int getScore(boolean top) {
        return top ? score.get(FieldSide.TOP) : score.get(FieldSide.BOTTOM);
    }

    private void checkWinner() {
        for (FieldSide side : score.keySet()) {
            if (score.get(side) >= maxScore) {
                winNotifier.winGame(side, "Alcanzó el score antes que su contrincante");
            }
        }
    }

    /**
     * Inicializa el puntaje del jugador
     */
    private void initializeScore() {
        score = new HashMap<>();
        for (FieldSide side : FieldSide.values()) {
            score.put(side, 0);
        }
    }
}

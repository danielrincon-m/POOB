package aplicacion.game.components.target;

import aplicacion.game.components.Component;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

import java.util.Random;

/**
 * Componente que especifica el comportamiento de los blancos
 */
public class TargetBehaviour extends Component {

    private int scoreBonus;
    private final int maxScore;
    private float leftBound;
    private float rightBound;
    private float lifetime = 10;

    private RectangleCollider collider;
    private final TargetController targetController;
    private final FieldSide side;
    private Score scoreBoard;
    private FieldBounds fieldBounds;
    private BallMovement ballMovement;
    private RectangleCollider ballCollider;

    /**
     * @param parent           La Entidad que contiene este componente
     * @param side             El lado del campo en el que se ubica este blanco
     * @param maxScore         El puntaje máximo que tendrá este blanco
     * @param targetController El controlador de los blancos
     */
    public TargetBehaviour(Entity parent, FieldSide side, int maxScore, TargetController targetController) {
        super(parent);
        this.maxScore = maxScore;
        this.side = side;
        this.targetController = targetController;
    }

    @Override
    public void start() {
        scoreBoard = entityManager.find("SCORE_BOARD").getComponent(Score.class);
        ballMovement = entityManager.find("BALL").getComponent(BallMovement.class);
        ballCollider = entityManager.find("BALL").getComponent(RectangleCollider.class);
        fieldBounds = entityManager.find("FIELD").getComponent(FieldBounds.class);
        collider = parent.getComponent(RectangleCollider.class);
        setScoreBonus();
        getBounds();
        setPosition();
        setSize();
    }

    @Override
    public void update() {
        checkDisappear();
        checkBallHit();
    }

    /**
     * Verifica si es hora de remover el blanco por tiempo
     */
    private void checkDisappear() {
        lifetime -= GameTimer.deltaTime();
        if (lifetime <= 0) {
            remove();
        }
    }

    /**
     * Verifica si es golpeado por la pelota
     */
    private void checkBallHit() {
        if (collider.collidesWith(ballCollider)) {
            addScore();
            remove();
        }
    }

    /**
     * Agrega puntaje al jugador que haya golpeado el blanco
     */
    private void addScore() {
        FieldSide winner = side.equals(FieldSide.TOP) ? FieldSide.BOTTOM : FieldSide.TOP;
        scoreBoard.addScore(winner, scoreBonus);
        ballMovement.reset(GameUtils.getOtherSide(winner));
    }

    /**
     * Remueve el blanco y lo deja disponible para ser instanciado de nuevo
     */
    private void remove() {
        targetController.removeTarget(side, parent.getName());
    }

    /**
     * Calcula los límitos en los que puede ser instanciado el blanco
     */
    private void getBounds() {
        leftBound = fieldBounds.getLeftBound();
        rightBound = fieldBounds.getRightBound();
    }

    /**
     * Calcula a cuanto puntaje equivale este blanco
     */
    private void setScoreBonus() {
        Random r = new Random();
        int bound = ((maxScore / 2) - 2) + 1;
        if (bound > 0) {
            scoreBonus = r.nextInt(bound) + 2;
        } else {
            scoreBonus = 1;
        }
    }

    /**
     * Establece una posición aleatoria en el eje x del blanco
     * y la posición especificada en el eje y según el lado del campo en el que se encuentre
     */
    private void setPosition() {
        Vector2 position = transform.getPosition();
        Random r = new Random();
        //random xPosition
        position.x = leftBound + r.nextFloat() * (rightBound - leftBound);
        position.x -= transform.getWidth() / 2f;
        //yPosition
        if (side.equals(FieldSide.TOP)) {
            position.y = 10;
        } else {
            position.y = 740;
        }
        transform.setPosition(position);
    }

    /**
     * Establece el tamaño del blanco
     */
    private void setSize() {
        Vector2 size = new Vector2(85, 50);
        transform.setSize(size);
    }
}
